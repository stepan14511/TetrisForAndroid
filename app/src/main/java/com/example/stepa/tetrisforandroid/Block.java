package com.example.stepa.tetrisforandroid;

public class Block {
    public static final int NO_COLOR = 0;
    public static final int COLOR_ORANGE = 1;
    public static final int COLOR_YELLOW = 2;
    public static final int COLOR_BLUE = 3;
    public static final int COLOR_PURPLE = 4;
    public static final int COLOR_RED = 5;
    public static final int COLOR_GREEN = 6;

    private int color;

    Block() {
        color = NO_COLOR;
    }

    public void changeColor(int color) throws IllegalArgumentException {
        if ((color < 0) || (color > 6))
            throw new IllegalArgumentException();
        this.color = color;
    }

    public boolean isClear() {
        return (color == NO_COLOR);
    }
}
