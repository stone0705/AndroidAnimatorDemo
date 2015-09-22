package com.example.stone.animatedemo;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    Button button1;
    Button b2;
    Button b3;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.text1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        final float b2origin = b2.getTranslationY();
        final float b3origin = b3.getTranslationY();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rotateyAnimRun(b2, origin);
                objectDrop(b2,b2origin);
                drop(b3,b3origin);
                propertyValuesHolder(textView);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, InterpolatorActivity.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, listenerTestActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    //一組動畫
    public void rotateyAnimRun(final View view,float origin)
    {
        view.getElevation();
        PropertyValuesHolder pvhx = PropertyValuesHolder.ofFloat("Elevation", origin, origin + 50, origin);
        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(view, pvhx).setDuration(1000);
        anim.start();
    }
    //設定多組動畫
    public void propertyValuesHolder(View view)
    {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();
    }
    public void drop(final View view,float origin){
        ValueAnimator anim = ValueAnimator.ofFloat(origin,origin+10,origin-10,origin+6,origin-6,origin+1,origin-1,origin);
        anim.setTarget(view);
        anim.setDuration(1000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((float)animation.getAnimatedValue());
            }
        });
        anim.start();
    }
    public void objectDrop(final View view,float origin){
        ObjectAnimator.ofFloat(view,"TranslationY",origin,origin+10,origin-10,origin+6,origin-6,origin+1,origin-1,origin).setDuration(1000).start();
    }
}
