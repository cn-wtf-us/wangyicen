package com.feicui.edu.housekeeper.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;


public class MainActivity extends BaseActivity {
    private ImageView imageView;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        imageView.startAnimation(animation);
        //设置动画
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

    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.iv_logo);
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
    }

    @Override
    protected void setListener() {

    }
}
