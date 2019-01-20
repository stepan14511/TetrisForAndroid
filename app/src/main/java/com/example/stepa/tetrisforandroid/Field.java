package com.example.stepa.tetrisforandroid;

public class Field {
    public static final int LINES = 19;
    public static final int ARROWS = 11;

    private Block[][] field;
    private int white_color_res;

    Field(int white_color_res, int[][] field_pos_res){
        this.white_color_res = white_color_res;
        field = new Block[LINES][ARROWS];
        for(int line = 0; line < LINES; line++){
            for (int arrow = 0; arrow < ARROWS; arrow++){
                field[line][arrow] = new Block(this.white_color_res, field_pos_res[line][arrow]);
            }
        }
    }

    public int get_element_color_res(int line, int arrow){
        return this.field[line][arrow].get_color_res();
    }

    public int get_element_position_res(int line, int arrow){
        return this.field[line][arrow].get_position_res();
    }

    public void set_element_color_res(int line, int arrow, int color){
        this.field[line][arrow].set_color_res(color);
    }

    public void set_element_color_white(int line, int arrow){
        this.field[line][arrow].set_color_res(this.white_color_res);
    }

    public boolean check_if_clear(int line, int arrow){
        if((line < 0) || (line >= LINES) || (arrow < 0) || (arrow >= ARROWS))
            return false;

        return field[line][arrow].get_color_res() == white_color_res;
    }
}
