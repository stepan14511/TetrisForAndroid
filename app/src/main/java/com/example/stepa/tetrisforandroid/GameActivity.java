package com.example.stepa.tetrisforandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class GameActivity extends Activity {
    private static int UPDATE_RATE = 1000; //milliseconds
    private static int WAITING_TIME = 400; //milliseconds
    private static int ZOOMED_RATE = 100; //milliseconds
    private static int UPDATE_RATE_COPY = 0;
    private int[][] field_pos_res;

    //Game field
    public static Field field;

    //Handler
    private Handler down_move_handler;
    private Handler left_zoom_handler;
    private Handler right_zoom_handler;
    private Runnable left_zoom_runnable = new Runnable() {
        @Override
        public void run() {
            field.current_block.move_left();
            left_zoom_handler.postDelayed(this, ZOOMED_RATE);
            update_field();
        }
    };
    private Runnable right_zoom_runnable = new Runnable() {
        @Override
        public void run() {
            field.current_block.move_right();
            right_zoom_handler.postDelayed(this, ZOOMED_RATE);
            update_field();
        }
    };
    private Runnable down_move_runnable = new Runnable() {
        @Override
        public void run() {
            if(!one_tact()) {
                down_move_handler.postDelayed(this, UPDATE_RATE);
                Log.i("Down Handler", "down move handler set to UR ("+System.currentTimeMillis()+")");
            }
        }
    };

    //System functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        left_zoom_handler = new Handler();
        right_zoom_handler = new Handler();

        buttons_init();
        field_pos_res_init();
        field = new Field(R.color.white, field_pos_res);
        field.create_new_block();
        update_field();
    }

    protected void onResume(){
        super.onResume();

        handler_start();
    }

    protected void onPause(){
        down_move_handler.removeCallbacks(down_move_runnable);
        super.onPause();
    }

    //Custom functions

    @SuppressLint("ClickableViewAccessibility")
    private void buttons_init(){
        ImageView control_left = findViewById(R.id.control_left);
        control_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    field.current_block.move_left();
                    update_field();
                    left_zoom_handler.postDelayed(left_zoom_runnable, WAITING_TIME);
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    left_zoom_handler.removeCallbacks(left_zoom_runnable);
                    return true;
                }
                return false;
            }
        });

        ImageView control_right = findViewById(R.id.control_right);
        control_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    field.current_block.move_right();
                    update_field();
                    right_zoom_handler.postDelayed(right_zoom_runnable, WAITING_TIME);
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    right_zoom_handler.removeCallbacks(right_zoom_runnable);
                    return true;
                }
                return false;
            }
        });

        ImageView control_down = findViewById(R.id.control_down);
        control_down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    one_tact();
                    down_move_handler.removeCallbacks(down_move_runnable);
                    update_field();
                    UPDATE_RATE_COPY = UPDATE_RATE;
                    Log.i("Down Handler", "URC != 0 ("+System.currentTimeMillis()+")");
                    UPDATE_RATE = ZOOMED_RATE;
                    Log.i("Down Handler", "UR = ZR ("+System.currentTimeMillis()+")");
                    down_move_handler.postDelayed(down_move_runnable, WAITING_TIME);
                    Log.i("Down Handler", "down move handler set to waiting time ("+System.currentTimeMillis()+")");
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    down_move_handler.removeCallbacks(down_move_runnable);
                    UPDATE_RATE = UPDATE_RATE_COPY;
                    Log.i("Down Handler", "UR = URC");
                    UPDATE_RATE_COPY = 0;
                    Log.i("Down Handler", "URC = 0");
                    down_move_handler.postDelayed(down_move_runnable, UPDATE_RATE);
                    Log.i("Down Handler", "down move handler set to UR ("+System.currentTimeMillis()+")");
                    return true;
                }
                return false;
            }
        });

        ImageView control_rotate = findViewById(R.id.control_rotate);
        control_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                field.current_block.rotate();
                update_field();
            }
        });
    }

    private void handler_start(){
        down_move_handler = new Handler();
        down_move_handler.postDelayed(down_move_runnable, UPDATE_RATE);
    }

    //true if handler is already set(in it)
    public boolean one_tact(){
        boolean flag = false;
        if(!field.current_block.move_down()) {
            field.create_new_block();
            Log.i("Blocks", "Created new block ("+System.currentTimeMillis()+")");
            update_field();
            if(UPDATE_RATE_COPY != 0) {
                down_move_handler.removeCallbacks(down_move_runnable);
                down_move_handler.postDelayed(down_move_runnable, WAITING_TIME);
                Log.i("Down Handler", "down move handler set to waiting time ("+System.currentTimeMillis()+")");
                flag = true;
                UPDATE_RATE_COPY -= 30 * field.check_lines();
            }
            else
                UPDATE_RATE -= 30 * field.check_lines();
        }
        update_field();
        return flag;
    }

    private void update_field(){
        for(int line = 0; line < Field.LINES; line++){
            for (int arrow = 0; arrow < Field.ARROWS; arrow++){
                ImageView view = findViewById(field.get_element_position_res(line, arrow));
                view.setBackgroundResource(field.get_element_color_res(line, arrow));
            }
        }
    }

    private void field_pos_res_init(){
        field_pos_res = new int[][]{
                {R.id.fld_0_0, R.id.fld_0_1, R.id.fld_0_2, R.id.fld_0_3, R.id.fld_0_4, R.id.fld_0_5, R.id.fld_0_6, R.id.fld_0_7, R.id.fld_0_8, R.id.fld_0_9, R.id.fld_0_10},
                {R.id.fld_1_0, R.id.fld_1_1, R.id.fld_1_2, R.id.fld_1_3, R.id.fld_1_4, R.id.fld_1_5, R.id.fld_1_6, R.id.fld_1_7, R.id.fld_1_8, R.id.fld_1_9, R.id.fld_1_10},
                {R.id.fld_2_0, R.id.fld_2_1, R.id.fld_2_2, R.id.fld_2_3, R.id.fld_2_4, R.id.fld_2_5, R.id.fld_2_6, R.id.fld_2_7, R.id.fld_2_8, R.id.fld_2_9, R.id.fld_2_10},
                {R.id.fld_3_0, R.id.fld_3_1, R.id.fld_3_2, R.id.fld_3_3, R.id.fld_3_4, R.id.fld_3_5, R.id.fld_3_6, R.id.fld_3_7, R.id.fld_3_8, R.id.fld_3_9, R.id.fld_3_10},
                {R.id.fld_4_0, R.id.fld_4_1, R.id.fld_4_2, R.id.fld_4_3, R.id.fld_4_4, R.id.fld_4_5, R.id.fld_4_6, R.id.fld_4_7, R.id.fld_4_8, R.id.fld_4_9, R.id.fld_4_10},
                {R.id.fld_5_0, R.id.fld_5_1, R.id.fld_5_2, R.id.fld_5_3, R.id.fld_5_4, R.id.fld_5_5, R.id.fld_5_6, R.id.fld_5_7, R.id.fld_5_8, R.id.fld_5_9, R.id.fld_5_10},
                {R.id.fld_6_0, R.id.fld_6_1, R.id.fld_6_2, R.id.fld_6_3, R.id.fld_6_4, R.id.fld_6_5, R.id.fld_6_6, R.id.fld_6_7, R.id.fld_6_8, R.id.fld_6_9, R.id.fld_6_10},
                {R.id.fld_7_0, R.id.fld_7_1, R.id.fld_7_2, R.id.fld_7_3, R.id.fld_7_4, R.id.fld_7_5, R.id.fld_7_6, R.id.fld_7_7, R.id.fld_7_8, R.id.fld_7_9, R.id.fld_7_10},
                {R.id.fld_8_0, R.id.fld_8_1, R.id.fld_8_2, R.id.fld_8_3, R.id.fld_8_4, R.id.fld_8_5, R.id.fld_8_6, R.id.fld_8_7, R.id.fld_8_8, R.id.fld_8_9, R.id.fld_8_10},
                {R.id.fld_9_0, R.id.fld_9_1, R.id.fld_9_2, R.id.fld_9_3, R.id.fld_9_4, R.id.fld_9_5, R.id.fld_9_6, R.id.fld_9_7, R.id.fld_9_8, R.id.fld_9_9, R.id.fld_9_10},
                {R.id.fld_10_0, R.id.fld_10_1, R.id.fld_10_2, R.id.fld_10_3, R.id.fld_10_4, R.id.fld_10_5, R.id.fld_10_6, R.id.fld_10_7, R.id.fld_10_8, R.id.fld_10_9, R.id.fld_10_10},
                {R.id.fld_11_0, R.id.fld_11_1, R.id.fld_11_2, R.id.fld_11_3, R.id.fld_11_4, R.id.fld_11_5, R.id.fld_11_6, R.id.fld_11_7, R.id.fld_11_8, R.id.fld_11_9, R.id.fld_11_10},
                {R.id.fld_12_0, R.id.fld_12_1, R.id.fld_12_2, R.id.fld_12_3, R.id.fld_12_4, R.id.fld_12_5, R.id.fld_12_6, R.id.fld_12_7, R.id.fld_12_8, R.id.fld_12_9, R.id.fld_12_10},
                {R.id.fld_13_0, R.id.fld_13_1, R.id.fld_13_2, R.id.fld_13_3, R.id.fld_13_4, R.id.fld_13_5, R.id.fld_13_6, R.id.fld_13_7, R.id.fld_13_8, R.id.fld_13_9, R.id.fld_13_10},
                {R.id.fld_14_0, R.id.fld_14_1, R.id.fld_14_2, R.id.fld_14_3, R.id.fld_14_4, R.id.fld_14_5, R.id.fld_14_6, R.id.fld_14_7, R.id.fld_14_8, R.id.fld_14_9, R.id.fld_14_10},
                {R.id.fld_15_0, R.id.fld_15_1, R.id.fld_15_2, R.id.fld_15_3, R.id.fld_15_4, R.id.fld_15_5, R.id.fld_15_6, R.id.fld_15_7, R.id.fld_15_8, R.id.fld_15_9, R.id.fld_15_10},
                {R.id.fld_16_0, R.id.fld_16_1, R.id.fld_16_2, R.id.fld_16_3, R.id.fld_16_4, R.id.fld_16_5, R.id.fld_16_6, R.id.fld_16_7, R.id.fld_16_8, R.id.fld_16_9, R.id.fld_16_10},
                {R.id.fld_17_0, R.id.fld_17_1, R.id.fld_17_2, R.id.fld_17_3, R.id.fld_17_4, R.id.fld_17_5, R.id.fld_17_6, R.id.fld_17_7, R.id.fld_17_8, R.id.fld_17_9, R.id.fld_17_10},
                {R.id.fld_18_0, R.id.fld_18_1, R.id.fld_18_2, R.id.fld_18_3, R.id.fld_18_4, R.id.fld_18_5, R.id.fld_18_6, R.id.fld_18_7, R.id.fld_18_8, R.id.fld_18_9, R.id.fld_18_10}};
    } //Do not open(there's nothing interesting in it)
}
