package game;

import java.util.Arrays;

public class Board {
    private final int WIDTH = 10;
    private final int HEIGHT = 20;
    private int[][] grid = new int[HEIGHT][WIDTH];

    public boolean hasCollision(Tetromino t) {
        int[][] shape = t.getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    int tx = t.getX() + j;
                    int ty = t.getY() + i;
                    if (tx < 0 || tx >= WIDTH || ty >= HEIGHT) return true;
                    if (ty >= 0 && grid[ty][tx] != 0) return true;
                }
            }
        }
        return false;
    }

    public void placePiece(Tetromino t) {
        int[][] shape = t.getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    int x = t.getX() + j;
                    int y = t.getY() + i;
                    if (y >= 0 && y < HEIGHT) grid[y][x] = 1;
                }
            }
        }
    }

    public int clearLines() {
        int lines = 0;
        for (int i = HEIGHT - 1; i >= 0; i--) {
            boolean full = true;
            for (int cell : grid[i]) if (cell == 0) full = false;
            
            if (full) {
                lines++;
                for (int k = i; k > 0; k--) grid[k] = Arrays.copyOf(grid[k - 1], WIDTH);
                grid[0] = new int[WIDTH];
                i++;
            }
        }
        return lines;
    }

    public String[][] getPrintableGrid(Tetromino current) {
        String[][] view = new String[HEIGHT][WIDTH];
        
        for(int i=0; i<HEIGHT; i++) {
            for(int j=0; j<WIDTH; j++) {
                view[i][j] = (grid[i][j] == 0) ? ConsoleColors.BRIGHT_BLACK + " ." : ConsoleColors.WHITE + "██";
            }
        }
        
        if (current != null) {
            int[][] shape = current.getShape();
            for(int i=0; i<shape.length; i++) {
                for(int j=0; j<shape[0].length; j++) {
                    if (shape[i][j] != 0) {
                        int x = current.getX() + j;
                        int y = current.getY() + i;
                        if (y >= 0 && y < HEIGHT && x >= 0 && x < WIDTH) {
                            view[y][x] = current.getColor() + "██";
                        }
                    }
                }
            }
        }
        return view;
    }
}