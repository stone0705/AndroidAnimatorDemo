package com.example.stone.animatedemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.Toast;

public class xmlAnimateActivity extends AppCompatActivity {
    Button onStart,listen,onFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_animate);
        listener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_xml_animate, menu);
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
                Xmlanimate(listen);
            }
        });
        onFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XmlanimateSet(listen);
            }
        });
    }
    void Xmlanimate(View view){
        Animator anim = AnimatorInflater.loadAnimator(this,R.animator.animate2);
        anim.setTarget(view);
        anim.start();
    }
    void XmlanimateSet(View view){
        Animator anim = AnimatorInflater.loadAnimator(this,R.animator.animate1);
        anim.setTarget(view);
        anim.start();
    }
}
