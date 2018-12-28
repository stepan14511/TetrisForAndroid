package com.example.stepa.tetrisforandroid;

import java.util.ArrayList;

public class Field {
    private static final int LINES = 19;
    private static final int ARROWS = 11;

    private ArrayList<ArrayList<Block>> field;

    Field(){
        field = new ArrayList<ArrayList<Block>>(LINES);
        for(int i = 0; i < LINES; i++){
            for (int j = 0; j < ARROWS; j++){
                field.get(i).add(new Block());
            }
        }
    }

    public int get_element_color(int line, int arrow){
        return field.get(line).get(arrow).get_color();
    }

    public void set_element_color(int line, int arrow, int color) throws IllegalArgumentException{
        try {
            field.get(line).get(arrow).set_color(color);
        }
        catch (Exception e){
            throw e;
        }
    }
}
