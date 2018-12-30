package com.example.stepa.tetrisforandroid;

public class Current_block {
    private int type;
    private int[][] blocks;
    private int color_res;

    Current_block(int type, int color_res, int[][] field_pos_res) throws IllegalArgumentException{
        this.color_res = color_res;
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

    private void make_block_space_white(){
        for(int i = 0; i < 4; i++){
            GameActivity.field.set_element_color_white(blocks[i][0], blocks[i][1]);
        }
    }

    private void color_block_space(){
        for(int i = 0; i < 4; i++){
            GameActivity.field.set_element_color_res(blocks[i][0], blocks[i][1], color_res);
        }
    }

    public void move_down(){
        if((blocks[0][0] + 1 < Field.LINES)&&
                (blocks[1][0] + 1 < Field.LINES)&&
                (blocks[2][0] + 1 < Field.LINES)&&
                (blocks[3][0] + 1 < Field.LINES)){
            make_block_space_white();
            for (int i = 0; i < 4; i++) {
                blocks[i][0] += 1;
            }
            color_block_space();
        }
    }

    public void move_left(){
        if((blocks[0][1] - 1 >= 0)&&
                (blocks[1][1] - 1 >= 0)&&
                (blocks[2][1] - 1 >= 0)&&
                (blocks[3][1] - 1 >= 0)){
            make_block_space_white();
            for(int i = 0; i < 4; i++){
                blocks[i][1] -= 1;
            }
            color_block_space();
        }
    }

    public void move_right(){
        if((blocks[0][1] + 1 < Field.ARROWS)&&
                (blocks[1][1] + 1 < Field.ARROWS)&&
                (blocks[2][1] + 1 < Field.ARROWS)&&
                (blocks[3][1] + 1 < Field.ARROWS)){
            make_block_space_white();
            for(int i = 0; i < 4; i++){
                blocks[i][1] += 1;
            }
            color_block_space();
        }
    }
}
