package com.example.stepa.tetrisforandroid;

public class Current_block {
    private int type;
    private int[][] blocks;

    Current_block(int type, int color_res, int[][] field_pos_res) throws IllegalArgumentException{
        blocks = new int[4][2];
        switch(type){
            case 0:
                blocks[0][0] = 2; blocks[0][1] = 4;
                blocks[1][0] = 2; blocks[1][1] = 5;
                blocks[2][0] = 2; blocks[2][1] = 6;
                blocks[3][0] = 2; blocks[3][1] = 7;
                break;
            case 1:
                blocks[0][0] = 1; blocks[0][1] = 5;
                blocks[1][0] = 1; blocks[1][1] = 6;
                blocks[2][0] = 2; blocks[2][1] = 5;
                blocks[3][0] = 2; blocks[3][1] = 6;
                break;
            case 2:
                blocks[0][0] = 1; blocks[0][1] = 4;
                blocks[1][0] = 2; blocks[1][1] = 4;
                blocks[2][0] = 2; blocks[2][1] = 5;
                blocks[3][0] = 2; blocks[3][1] = 6;
                break;
            case 3:
                blocks[0][0] = 2; blocks[0][1] = 4;
                blocks[1][0] = 1; blocks[1][1] = 5;
                blocks[2][0] = 2; blocks[2][1] = 5;
                blocks[3][0] = 2; blocks[3][1] = 6;
                break;
            case 4:
                blocks[0][0] = 1; blocks[0][1] = 5;
                blocks[1][0] = 2; blocks[1][1] = 5;
                blocks[2][0] = 2; blocks[2][1] = 6;
                blocks[3][0] = 3; blocks[3][1] = 6;
                break;
            case 5:
                blocks[0][0] = 1; blocks[0][1] = 6;
                blocks[1][0] = 2; blocks[1][1] = 5;
                blocks[2][0] = 2; blocks[2][1] = 6;
                blocks[3][0] = 3; blocks[3][1] = 5;
                break;
            case 6:
                blocks[0][0] = 2; blocks[0][1] = 4;
                blocks[1][0] = 2; blocks[1][1] = 5;
                blocks[2][0] = 1; blocks[2][1] = 6;
                blocks[3][0] = 2; blocks[3][1] = 6;
                break;
        }

        for(int i = 0; i < 4; i++){
            GameActivity.field.set_element_color_res(blocks[i][0], blocks[i][1], color_res);
        }
    }
}
