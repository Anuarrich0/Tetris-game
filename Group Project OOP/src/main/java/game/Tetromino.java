package game;

import java.util.Random;

public class Tetromino {
    private int[][] shape;
    private int x, y, type;
    private String color;

    public static final int[][][] SHAPES = {
        {{1, 1, 1, 1}}, {{1, 0, 0}, {1, 1, 1}}, {{0, 0, 1}, {1, 1, 1}},
        {{1, 1}, {1, 1}}, {{0, 1, 1}, {1, 1, 0}}, {{0, 1, 0}, {1, 1, 1}}, {{1, 1, 0}, {0, 1, 1}}
    };
    private static final String[] COLORS = {
        ConsoleColors.CYAN, ConsoleColors.BLUE, ConsoleColors.BRIGHT_YELLOW,
        ConsoleColors.YELLOW, ConsoleColors.GREEN, ConsoleColors.PURPLE, ConsoleColors.RED
    };

    public Tetromino() {
        this(new Random().nextInt(7));
    }

    public Tetromino(int type) {
        this.type = type;
        this.shape = SHAPES[type];
        this.color = COLORS[type];
        this.x = 4; this.y = 0;
    }

    public Tetromino(Tetromino other) {
        this.type = other.type;
        this.shape = other.shape;
        this.color = other.color;
        this.x = other.x;
        this.y = other.y;
    }

    public void rotate() {
        if (type == 3) return;
        int h = shape.length, w = shape[0].length;
        int[][] newShape = new int[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                newShape[j][h - 1 - i] = shape[i][j];
            }
        }
        shape = newShape;
    }

    public void move(int dx, int dy) { x += dx; y += dy; }
    
    public int[][] getShape() { return shape; }
    public int getX() { return x; }
    public int getY() { return y; }
    public String getColor() { return color; }
    public int getType() { return type; }
}