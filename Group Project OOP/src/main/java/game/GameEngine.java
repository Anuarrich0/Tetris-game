package game;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;
import database.DBManager;
import java.io.IOException;

public class GameEngine {
    private boolean isRunning = true;
    private Board board = new Board();
    private Tetromino current;
    private int score = 0, userId;
    private String mode;

    public GameEngine(String mode, int userId) {
        this.mode = mode;
        this.userId = userId;
        spawnPiece();
    }

    private void spawnPiece() {
        current = new Tetromino(); 
        if (board.hasCollision(current)) {
            isRunning = false;
        }
    }

    public void start() {
        try (Terminal terminal = TerminalBuilder.builder().system(true).jna(true).build()) {
            terminal.enterRawMode();
            NonBlockingReader reader = terminal.reader();
            
            System.out.print("\033[?1049h\033[?25l");

            long lastTick = System.currentTimeMillis();
            
            while (isRunning) {
                render();

                if (reader.peek(50) != NonBlockingReader.READ_EXPIRED) {
                    handleInput((char) reader.read());
                }

                if (System.currentTimeMillis() - lastTick > 500) {
                    if (!tryMove(0, 1)) lockPiece();
                    lastTick = System.currentTimeMillis();
                }
            }
            
            System.out.print("\033[?1049l\033[?25h");
            if (userId != -1) DBManager.saveScore(userId, mode, score, 0);
            System.out.println("GAME OVER! Score: " + score);
            Thread.sleep(2000);

        } catch (Exception e) { e.printStackTrace(); }
    }

    private boolean tryMove(int dx, int dy) {
        Tetromino next = new Tetromino(current);
        next.move(dx, dy);
        if (!board.hasCollision(next)) {
            current = next;
            return true;
        }
        return false;
    }
    
    private void tryRotate() {
        Tetromino next = new Tetromino(current);
        next.rotate();
        if (!board.hasCollision(next)) current = next;
    }

    private void lockPiece() {
        board.placePiece(current);
        score += board.clearLines() * 100;
        spawnPiece();
    }

    private void handleInput(char key) {
        if (key == 'q') isRunning = false;
        if (key == 'a') tryMove(-1, 0);
        if (key == 'd') tryMove(1, 0);
        if (key == 's') { tryMove(0, 1); score++; }
        if (key == 'w') tryRotate();
        if (key == ' ') while(tryMove(0, 1)) score += 2;
    }

    private void render() {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[H");

        String[][] view = board.getPrintableGrid(current);

        sb.append(ConsoleColors.CYAN + "SCORE: " + score + ConsoleColors.RESET + "\n");
        sb.append("╔════════════════════╗\n");
        
        for (int i = 0; i < 20; i++) {
            sb.append("║");
            for (int j = 0; j < 10; j++) {
                sb.append(view[i][j] + ConsoleColors.RESET);
            }
            sb.append("║\n");
        }
        sb.append("╚════════════════════╝\n");
        
        System.out.print(sb.toString());
        System.out.flush();
    }
}