package com.example.stepa.tetrisforandroid;

public class Block {
    private int color_res;
    private int position_res;

    Block(int color_res, int position_res) {
        this.color_res = color_res;
        this.position_res = position_res;
    }

    public void set_color_res(int color_res){
        this.color_res = color_res;
    }

    public int get_position_res(){
        return this.position_res;
    }

    public int get_color_res(){
        return this.color_res;
    }
}
