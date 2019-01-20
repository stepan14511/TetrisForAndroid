package com.example.stepa.tetrisforandroid;

public class Current_block {
    private int position;
    private int type;
    private int[][] blocks;
    private int[][][] start_positions;//1st - type, 2nd - position, 3d - line, 4th - arrow
    private int[][][][] rotate_change;
    private int color_res;

    Current_block(int type, int color_res, int position) throws IllegalArgumentException{
        this.color_res = color_res;
        this.position = 0;
        this.type = type;

        init_start_positions();
        blocks = start_positions[type];
        color_block_space();
    }

    public void make_block_space_white(){
        for(int i = 0; i < 4; i++){
            GameActivity.field.set_element_color_white(blocks[i][0], blocks[i][1]);
        }
    }

    private void color_block_space(){
        for(int i = 0; i < 4; i++){
            GameActivity.field.set_element_color_res(blocks[i][0], blocks[i][1], color_res);
        }
    }

    //Moving down. Returns true if successful and false if not
    public boolean move_down(){
        make_block_space_white();
        boolean if_clear = true;
        for(int i = 0; i < 4; i++)
            if(!GameActivity.field.check_if_clear(blocks[i][0] + 1, blocks[i][1]))
                if_clear = false;

        if(if_clear) {
            for (int i = 0; i < 4; i++)
                blocks[i][0] += 1;
            color_block_space();
            return true;
        }
        else{
            color_block_space();
            return false;
        }

    }

    public void move_left(){
        make_block_space_white();
        boolean if_clear = true;
        for(int i = 0; i < 4; i++)
            if(!GameActivity.field.check_if_clear(blocks[i][0], blocks[i][1] - 1))
                if_clear = false;

        if(if_clear)
            for(int i = 0; i < 4; i++)
                blocks[i][1] -= 1;

        color_block_space();
    }

    public void move_right(){
        make_block_space_white();
        boolean if_clear = true;
        for(int i = 0; i < 4; i++)
            if(!GameActivity.field.check_if_clear(blocks[i][0], blocks[i][1] + 1))
                if_clear = false;

        if(if_clear)
            for(int i = 0; i < 4; i++)
                blocks[i][1] += 1;

        color_block_space();
    }

    public void rotate(){
        make_block_space_white();
        this.blocks[0][0] -= this.rotate_change[this.type][this.position][0][0];
        this.blocks[1][0] -= this.rotate_change[this.type][this.position][1][0];
        this.blocks[2][0] -= this.rotate_change[this.type][this.position][2][0];
        this.blocks[3][0] -= this.rotate_change[this.type][this.position][3][0];
        this.blocks[0][1] -= this.rotate_change[this.type][this.position][0][1];
        this.blocks[1][1] -= this.rotate_change[this.type][this.position][1][1];
        this.blocks[2][1] -= this.rotate_change[this.type][this.position][2][1];
        this.blocks[3][1] -= this.rotate_change[this.type][this.position][3][1];

        this.position++;
        if(this.position == 4)
            this.position = 0;

        this.blocks[0][0] += this.rotate_change[this.type][this.position][0][0];
        this.blocks[1][0] += this.rotate_change[this.type][this.position][1][0];
        this.blocks[2][0] += this.rotate_change[this.type][this.position][2][0];
        this.blocks[3][0] += this.rotate_change[this.type][this.position][3][0];
        this.blocks[0][1] += this.rotate_change[this.type][this.position][0][1];
        this.blocks[1][1] += this.rotate_change[this.type][this.position][1][1];
        this.blocks[2][1] += this.rotate_change[this.type][this.position][2][1];
        this.blocks[3][1] += this.rotate_change[this.type][this.position][3][1];

        boolean if_clear = true;
        for(int i = 0; i < 4; i++)
            if(!GameActivity.field.check_if_clear(blocks[i][0], blocks[i][1]))
                if_clear = false;

        if(!if_clear){
            this.blocks[0][0] -= this.rotate_change[this.type][this.position][0][0];
            this.blocks[1][0] -= this.rotate_change[this.type][this.position][1][0];
            this.blocks[2][0] -= this.rotate_change[this.type][this.position][2][0];
            this.blocks[3][0] -= this.rotate_change[this.type][this.position][3][0];
            this.blocks[0][1] -= this.rotate_change[this.type][this.position][0][1];
            this.blocks[1][1] -= this.rotate_change[this.type][this.position][1][1];
            this.blocks[2][1] -= this.rotate_change[this.type][this.position][2][1];
            this.blocks[3][1] -= this.rotate_change[this.type][this.position][3][1];

            this.position--;
            if(this.position == -1)
                this.position = 3;

            this.blocks[0][0] += this.rotate_change[this.type][this.position][0][0];
            this.blocks[1][0] += this.rotate_change[this.type][this.position][1][0];
            this.blocks[2][0] += this.rotate_change[this.type][this.position][2][0];
            this.blocks[3][0] += this.rotate_change[this.type][this.position][3][0];
            this.blocks[0][1] += this.rotate_change[this.type][this.position][0][1];
            this.blocks[1][1] += this.rotate_change[this.type][this.position][1][1];
            this.blocks[2][1] += this.rotate_change[this.type][this.position][2][1];
            this.blocks[3][1] += this.rotate_change[this.type][this.position][3][1];
        }
        color_block_space();
    }

    private void init_start_positions(){
        start_positions = new int[7][][];
        rotate_change = new int[7][4][][];
        start_positions[0] = new int[][]{
                {2, 4},
                {2, 5},
                {2, 6},
                {2, 7}};
        rotate_change[0][0] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        rotate_change[0][1] = new int[][]{
                {-2, 2},
                {-1, 1},
                {0, 0},
                {1, -1}};
        rotate_change[0][2] = new int[][]{
                {0, 1},
                {0, 1},
                {0, 1},
                {0, 1}};
        rotate_change[0][3] = new int[][]{
                {-1, 2},
                {0, 1},
                {1, 0},
                {2, -1}};
        start_positions[1] = new int[][]{
                {1, 5},
                {1, 6},
                {2, 5},
                {2, 6}};
        rotate_change[1][0] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        rotate_change[1][1] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        rotate_change[1][2] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        rotate_change[1][3] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        start_positions[2] = new int[][]{
                {1, 4},
                {2, 4},
                {2, 5},
                {2, 6}};
        rotate_change[2][0] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        rotate_change[2][1] = new int[][]{
                {0, 1},
                {-1, 2},
                {0, 0},
                {1, -1}};
        rotate_change[2][2] = new int[][]{
                {1, 0},
                {0, 1},
                {0, 1},
                {1, 0}};
        rotate_change[2][3] = new int[][]{
                {0, 1},
                {0, 1},
                {1, 0},
                {1, -2}};
        start_positions[3] = new int[][]{
                {2, 4},
                {1, 5},
                {2, 5},
                {2, 6}};
        rotate_change[3][0] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        rotate_change[3][1] = new int[][]{
                {0, 1},
                {0, 0},
                {0, 1},
                {1, -1}};
        rotate_change[3][2] = new int[][]{
                {0, 0},
                {2, 0},
                {0, 0},
                {0, 0}};
        rotate_change[3][3] = new int[][]{
                {-1, 1},
                {1, 0},
                {0, -1},
                {1, -1}};
        start_positions[4] = new int[][]{
                {1, 5},
                {2, 5},
                {2, 6},
                {3, 6}};
        rotate_change[4][0] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        rotate_change[4][1] = new int[][]{
                {0, 1},
                {0, 0},
                {-1, 1},
                {-1, 0}};
        rotate_change[4][2] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        rotate_change[4][3] = new int[][]{
                {0, 1},
                {0, 0},
                {-1, 1},
                {-1, 0}};
        start_positions[5] = new int[][]{
                {1, 6},
                {2, 5},
                {2, 6},
                {3, 5}};
        rotate_change[5][0] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        rotate_change[5][1] = new int[][]{
                {0, -2},
                {-1, 0},
                {0, -1},
                {-1, 1}};
        rotate_change[5][2] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        rotate_change[5][3] = new int[][]{
                {0, -2},
                {-1, 0},
                {0, -1},
                {-1, 1}};
        start_positions[6] = new int[][]{
                {2, 4},
                {2, 5},
                {1, 6},
                {2, 6}};
        rotate_change[6][0] = new int[][]{
                {0, 0},
                {0, 0},
                {0, 0},
                {0, 0}};
        rotate_change[6][1] = new int[][]{
                {1, 1},
                {0, 0},
                {0, -1},
                {1, 0}};
        rotate_change[6][2] = new int[][]{
                {0, 0},
                {0, 0},
                {2, -2},
                {0, 0}};
        rotate_change[6][3] = new int[][]{
                {1, 1},
                {0, 0},
                {0, -1},
                {-1, -2}};
        //1st - type, 2nd - position, 3d - line, 4th - arrow
    }
}
