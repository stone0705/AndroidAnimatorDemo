package com.example.stone.animatedemo;

import android.view.animation.LinearInterpolator;

/**
 * Created by stone on 2015/9/15.
 */
public class myInterpolation extends LinearInterpolator {
    static float lastleaner = -1;
    static float lastcustom = -1;
    @Override
    public float getInterpolation(float input) {
        float currentleaner = 0;
        float currentcustom = 0;
        if(lastleaner < 0){
            lastleaner = input;
            lastcustom = input * input;
        }else{
            currentcustom = input * input;
            currentleaner = input;
            System.out.println("input:" + input + "  input*input:"+(input * input)+
                    "    leaner差為"+(currentleaner - lastleaner)+"  custom差為"+(currentcustom - lastcustom));
            lastleaner = input;
            lastcustom = input * input;
        }
            return input * input;
    }
}
