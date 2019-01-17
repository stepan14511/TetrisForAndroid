package com.example.stepa.tetrisforandroid;

public class Current_block {
    private int position;
    private int type;
    private int[][] blocks;
    private int[][][][] start_positions;//1st - type, 2nd - position, 3d - line, 4th - arrow
    private int color_res;

    Current_block(int type, int color_res, int position) throws IllegalArgumentException{
        this.color_res = color_res;
        this.position = position;
        this.type = type;

        init_start_positions();
        blocks = start_positions[type][position];
        color_block_space();
    }

    public void make_block_space_white(){
        for(int i = 0; i < 4; i++){
            GameActivity.field.set_element_color_white(blocks[i][0], blocks[i][1]);
        }
    } //TODO: made it private

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

    private void init_start_positions(){
        start_positions = new int[7][4][][];
        start_positions[0][0] = new int[][]{
                {2, 4},
                {2, 5},
                {2, 6},
                {2, 7}};
        start_positions[0][1] = new int[][]{
                {0, 6},
                {1, 6},
                {2, 6},
                {3, 6}};
        start_positions[0][2] = new int[][]{
                {2, 4},
                {2, 5},
                {2, 6},
                {2, 7}};
        start_positions[0][3] = new int[][]{
                {0, 6},
                {1, 6},
                {2, 6},
                {3, 6}};
        start_positions[1][0] = new int[][]{
                {1, 5},
                {1, 6},
                {2, 5},
                {2, 6}};
        start_positions[1][1] = new int[][]{
                {1, 5},
                {1, 6},
                {2, 5},
                {2, 6}};
        start_positions[1][2] = new int[][]{
                {1, 5},
                {1, 6},
                {2, 5},
                {2, 6}};
        start_positions[1][3] = new int[][]{
                {1, 5},
                {1, 6},
                {2, 5},
                {2, 6}};
        start_positions[2][0] = new int[][]{
                {1, 4},
                {2, 4},
                {2, 5},
                {2, 6}};
        start_positions[2][1] = new int[][]{
                {1, 5},
                {1, 6},
                {2, 5},
                {3, 5}};
        start_positions[2][2] = new int[][]{
                {2, 4},
                {2, 5},
                {2, 6},
                {3, 6}};
        start_positions[2][3] = new int[][]{
                {1, 5},
                {2, 5},
                {3, 5},
                {3, 4}};
        start_positions[3][0] = new int[][]{
                {2, 4},
                {1, 5},
                {2, 5},
                {2, 6}};
        start_positions[3][1] = new int[][]{
                {1, 5},
                {2, 5},
                {2, 6},
                {3, 5}};
        start_positions[3][2] = new int[][]{
                {2, 4},
                {3, 5},
                {2, 5},
                {2, 6}};
        start_positions[3][3] = new int[][]{
                {1, 5},
                {2, 5},
                {2, 4},
                {3, 5}};
        start_positions[4][0] = new int[][]{
                {1, 5},
                {2, 5},
                {2, 6},
                {3, 6}};
        start_positions[4][1] = new int[][]{
                {1, 6},
                {2, 5},
                {1, 7},
                {2, 6}};
        start_positions[4][2] = new int[][]{
                {1, 5},
                {2, 5},
                {2, 6},
                {3, 6}};
        start_positions[4][3] = new int[][]{
                {1, 6},
                {2, 5},
                {1, 7},
                {2, 6}};
        start_positions[5][0] = new int[][]{
                {1, 6},
                {2, 5},
                {2, 6},
                {3, 5}};
        start_positions[5][1] = new int[][]{
                {1, 4},
                {1, 5},
                {2, 5},
                {2, 6}};
        start_positions[5][2] = new int[][]{
                {1, 6},
                {2, 5},
                {2, 6},
                {3, 5}};
        start_positions[5][3] = new int[][]{
                {1, 4},
                {1, 5},
                {2, 5},
                {2, 6}};
        start_positions[6][0] = new int[][]{
                {2, 4},
                {2, 5},
                {1, 6},
                {2, 6}};
        start_positions[6][1] = new int[][]{
                {3, 5},
                {2, 5},
                {1, 5},
                {3, 6}};
        start_positions[6][2] = new int[][]{
                {2, 4},
                {2, 5},
                {3, 4},
                {2, 6}};
        start_positions[6][3] = new int[][]{
                {3, 5},
                {2, 5},
                {1, 5},
                {1, 4}};
        //1st - type, 2nd - position, 3d - line, 4th - arrow
    }
}
