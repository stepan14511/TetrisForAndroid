package com.example.stepa.tetrisforandroid;

import java.util.Random;

public class Field {
    public static final int LINES = 19;
    public static final int ARROWS = 11;

    private Block[][] field;
    public Current_block current_block;
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

    //returns number of deleted lines
    public int check_lines(){
        //finish
        current_block.make_block_space_white();
        int counter = 0;
        for(int i = 0; i < LINES; i++) {
            boolean is_full = true;
            for (int j = 0; j < ARROWS; j++) {
                if (field[i][j].get_color_res() == white_color_res)
                    is_full = false;
            }
            if(is_full) {
                counter++;
                for (int j = i - 1; j >= 0; j--){
                    for(int l = 0; l < ARROWS; l++){
                        field[j + 1][l].set_color_res(field[j][l].get_color_res());
                    }
                }
            }
        }
        current_block.color_block_space();
        return counter;
    }

    public void create_new_block(){
        int[] colors = new int[]{R.color.orange, R.color.yellow, R.color.blue, R.color.purple, R.color.red, R.color.green};
        Random rnd = new Random();
        current_block = new Current_block(rnd.nextInt(7), colors[rnd.nextInt(6)], rnd.nextInt(4));
    }
}
