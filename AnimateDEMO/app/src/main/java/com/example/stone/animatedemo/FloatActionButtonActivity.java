package com.example.stone.animatedemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class FloatActionButtonActivity extends AppCompatActivity {
    FloatingActionButton fab,fab1,fab2,fab3;
    static boolean isExtend = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_action_button);
        listen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_float_action_button, menu);
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
    void listen(){
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        final float X = fab.getTranslationX();
        final float Y = fab.getTranslationY();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExtend){
                    ReturnAnimate(fab1,fab2,fab3,X,Y,210f);
                    isExtend = false;
                }else{
                    animate(fab1,fab2,fab3,X,Y,210f);
                    isExtend = true;
                }
            }
        });
    }
    void animate(final View view1, final View view2, final View view3,float X,float Y,float redius){
        //位移
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view1, "TranslationX", X, X + redius).setDuration(200);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view2,"TranslationX",X,(float)(X+(redius/Math.pow(2,0.5)))).setDuration(400);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view2,"TranslationY",Y,(float)(Y+(redius/Math.pow(2,0.5)))).setDuration(400);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(view3,"TranslationY",Y,Y+redius).setDuration(600);
        //anim1.setInterpolator(new DecelerateInterpolator());
        anim2.setInterpolator(new DecelerateInterpolator());
        anim3.setInterpolator(new DecelerateInterpolator());
        anim4.setInterpolator(new DecelerateInterpolator());
        //大小
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(view1,"ScaleX",1f,0.6f).setDuration(200);
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(view1,"ScaleY",1f,0.6f).setDuration(200);
        ObjectAnimator anim7 = ObjectAnimator.ofFloat(view2,"ScaleX",1f,0.6f).setDuration(400);
        ObjectAnimator anim8 = ObjectAnimator.ofFloat(view2,"ScaleY",1f,0.6f).setDuration(400);
        ObjectAnimator anim9 = ObjectAnimator.ofFloat(view3,"ScaleX",1f,0.6f).setDuration(600);
        ObjectAnimator anim10 = ObjectAnimator.ofFloat(view3,"ScaleY",1f,0.6f).setDuration(600);
        AnimatorSet set = new AnimatorSet();
        //set.play(anim1);
        set.playTogether(anim1, anim2, anim3, anim4,anim5,anim6,anim7,anim8,anim9,anim10);
        //set.play(anim2).with(anim1).after(200);
        //set.play(anim4).after(1050);
        //set.setDuration(500);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.VISIBLE);
            }
        });
        set.start();
    }
    void ReturnAnimate(final View view1, final View view2, final View view3,float X,float Y,float redius){
        //位移
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view1, "TranslationX",X + redius,X).setDuration(200);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view2,"TranslationX",(float)(X+(redius/Math.pow(2,0.5))),X).setDuration(400);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view2,"TranslationY",(float)(Y+(redius/Math.pow(2,0.5))),Y).setDuration(400);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(view3,"TranslationY",Y+redius,Y).setDuration(600);
        anim1.setInterpolator(new AccelerateInterpolator());
        anim2.setInterpolator(new AccelerateInterpolator());
        anim3.setInterpolator(new AccelerateInterpolator());
        anim4.setInterpolator(new AccelerateInterpolator());
        //大小
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(view1,"ScaleX",0.6f,1f).setDuration(200);
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(view1,"ScaleY",0.6f,1f).setDuration(200);
        ObjectAnimator anim7 = ObjectAnimator.ofFloat(view2,"ScaleX",0.6f,1f).setDuration(400);
        ObjectAnimator anim8 = ObjectAnimator.ofFloat(view2,"ScaleY",0.6f,1f).setDuration(400);
        ObjectAnimator anim9 = ObjectAnimator.ofFloat(view3,"ScaleX",0.6f,1f).setDuration(600);
        ObjectAnimator anim10 = ObjectAnimator.ofFloat(view3,"ScaleY",0.6f,1f).setDuration(600);
        AnimatorSet set = new AnimatorSet();
        //set.play(anim1);
        set.playTogether(anim1, anim2, anim3, anim4,anim5,anim6,anim7,anim8,anim9,anim10);
        //set.play(anim2).with(anim1).after(200);
        //set.play(anim4).after(1050);
        //set.setDuration(500);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
            }
        });
        set.start();
    }
}
