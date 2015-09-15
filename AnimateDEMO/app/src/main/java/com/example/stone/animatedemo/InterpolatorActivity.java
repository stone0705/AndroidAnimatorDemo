package com.example.stone.animatedemo;

import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class InterpolatorActivity extends Activity {

    Button ade,a,de,cycle,linear,target,target2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        listener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_interpolator, menu);
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
        ade = (Button)findViewById(R.id.ade);
        a = (Button)findViewById(R.id.a);
        de = (Button)findViewById(R.id.de);
        cycle = (Button)findViewById(R.id.cycle);
        linear = (Button)findViewById(R.id.custom);
        target = (Button)findViewById(R.id.target);
        target2 = (Button)findViewById(R.id.target2);
        final float origin = target.getTranslationY();
        final float originx = target2.getTranslationX();
        ade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim(target,origin,new AccelerateDecelerateInterpolator());
                anim(target2,origin,new LinearInterpolator());
            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim(target,origin,new AccelerateInterpolator());
                anim(target2,origin,new LinearInterpolator());
            }
        });
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim(target,origin,new DecelerateInterpolator());
                anim(target2,origin,new LinearInterpolator());
            }
        });
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim(target,origin,new myInterpolation());
                Ganim(target2, origin, new point(originx, origin), new point(originx, origin + 500));
            }
        });
        cycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim(target,origin,new CycleInterpolator(6));
                anim(target2, origin, new LinearInterpolator());
            }
        });
        target2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(InterpolatorActivity.this, AnimationSetActivity.class);
                startActivity(intent);
            }
        });
    }
    void anim(final View view,float origin,TimeInterpolator interpolator){
        ValueAnimator anim = ValueAnimator.ofFloat(origin, origin + 500);
        anim.setTarget(view);
        anim.setDuration(1000);
        anim.setInterpolator(interpolator);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((float) animation.getAnimatedValue());
            }
        });
        anim.start();
    }

    //以evaluate方式實現自由落體
    void Ganim(final View view,float origin,point... pointa){
        ValueAnimator anim = new ValueAnimator();
        anim.setObjectValues(pointa);
        anim.setDuration(1000);
        anim.setEvaluator(new TypeEvaluator<point>() {
            @Override
            public point evaluate(float fraction, point startValue, point endValue) {
                point a = new point(0, 0);
                a.y = startValue.y + (endValue.y - startValue.y) * fraction * fraction;
                a.x = startValue.x;
                return a;
            }
        });
        anim.setInterpolator(new LinearInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                point a = (point) animation.getAnimatedValue();
                view.setTranslationY(a.y);
            }
        });
        anim.start();
    }
}
