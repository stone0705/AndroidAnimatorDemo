package com.example.stone.animatedemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class listenerTestActivity extends Activity {
    Button onStart,listen,onFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener_test);
        listener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listener_test, menu);
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
        onStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartAnim(listen);
            }
        });
        onFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinishAnim(listen);
            }
        });
    }
    void onStartAnim(final View view){
        ValueAnimator anim = new ValueAnimator();
        anim = anim.ofFloat(0f,360f);
        anim.setDuration(2000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setRotationX((float) animation.getAnimatedValue());
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationRepeat(animation);
                Toast.makeText(listenerTestActivity.this,"onstart",Toast.LENGTH_SHORT).show();
            }
        });
        anim.start();
    }
    void onFinishAnim(final View view){
        ValueAnimator anim = new ValueAnimator();
        anim = anim.ofFloat(0f,360f);
        anim.setDuration(2000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setRotationY((float) animation.getAnimatedValue());
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationRepeat(animation);
                Toast.makeText(listenerTestActivity.this,"onEnd",Toast.LENGTH_SHORT).show();
            }
        });
        anim.start();
    }
}
