package com.feicui.edu.housekeeper.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.feicui.edu.housekeeper.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.iv_logo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        imageView.startAnimation(animation);

        Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            //动画开始
            @Override
            public void onAnimationStart(Animation animation) {

            }
            //动画结束
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
            //动画重复
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        animation.setAnimationListener(animationListener);
        imageView.startAnimation(animation);
    }
}
