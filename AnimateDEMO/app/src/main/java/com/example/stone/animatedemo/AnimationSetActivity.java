package com.example.stone.animatedemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.widget.Button;

public class AnimationSetActivity extends Activity {
    Button onStart,listen,onFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_set);
        listener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animation_set, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    void listener(){
        onStart = (Button)findViewById(R.id.onStart);
        onFinish = (Button)findViewById(R.id.onFinish);
        listen = (Button)findViewById(R.id.listenButton);
        final float X = listen.getTranslationX();
        final float Y = listen.getTranslationY();
        onStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togetherSet(listen,X,Y);
            }
        });
    }
    void togetherSet(View view,float originX,float originY){
        view.setElevation(100);
        view.setTranslationY(originY);
        view.setTranslationX(originX - 120);
        view.setScaleX(1.3f);
        view.setScaleY(1.3f);
        view.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "Elevation", 100, 0);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "TranslationY", originY, originY + 190);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view,"TranslationX",originX-120,originX);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(view,"ScaleX",1.3f,1.0f);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(view,"ScaleY",1.3f,1.0f);
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(view,"Elevation",0,30,0,18,0,12,0,5,0);
        ObjectAnimator anim7 = ObjectAnimator.ofFloat(view, "ScaleX", 1.0f, 1.1f, 1.0f, 1.08f, 1.0f, 1.04f, 1.0f,1.01f,1.0f);
        ObjectAnimator anim8 = ObjectAnimator.ofFloat(view, "ScaleY", 1.0f, 1.1f, 1.0f, 1.08f, 1.0f, 1.04f, 1.0f,1.01f,1.0f);
        ObjectAnimator anim9 = ObjectAnimator.ofFloat(view, "TranslationY", originY + 190,originY +165,originY + 190,originY+172,originY + 190,originY+182,originY+190,originY+188,originY+190);
        ObjectAnimator anim10 = ObjectAnimator.ofFloat(view,"TranslationX", originX,originX-25,originX,originX-18,originX,originX-12,originX,originX-5,originX,originX-2,originX);
        set.playTogether(anim1, anim2, anim3, anim4, anim5);
        set.setInterpolator(new AccelerateInterpolator());
        set.setDuration(500);
        set.playTogether(anim6,anim7,anim8,anim9,anim10);
        set.play(anim6).after(anim5);
        set.start();
    }
}
