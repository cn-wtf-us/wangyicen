package com.feicui.edu.housekeeper.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.base.adapter.BasePagerAdapter;
import com.feicui.edu.housekeeper.base.utils.LogUtil;
import com.feicui.edu.housekeeper.service.MusicService;

import java.util.List;

public class LeadActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TextView textView;
    private ImageView[] icons = new ImageView[3];
    private String className;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_lead);
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if(bundle != null){
            //如果没有获取到数据则返回null
            className = bundle.getString("className");
        }
        SharedPreferences shared = getSharedPreferences("first", Context.MODE_PRIVATE);
        boolean isFirst = shared.getBoolean("first", true);
        //判断是否是从设置界面跳转过来的
        if(className != null && className.equals(SettingActivity.class.getSimpleName())){
            startActivity(LeadActivity.class);
            //播放音乐
            intent = new Intent(this,MusicService.class);
            startService(intent);

        }else{
            if(!isFirst){
                startActivity(MainActivity.class);
                finish();
            }else{//如果是第一次登录
                LogUtil.d("isin", "ok");
                SharedPreferences.Editor edit = shared.edit();
                edit.putBoolean("first", false);
                edit.commit();

                //播放音乐
                intent = new Intent(this,MusicService.class);
                startService(intent);
            }
        }

    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //圆点
        icons[0] = (ImageView) findViewById(R.id.icon1);
        icons[1] = (ImageView) findViewById(R.id.icon2);
        icons[2] = (ImageView) findViewById(R.id.icon3);
        //直接跳过
        textView = (TextView) findViewById(R.id.lead_skip);
    }

    @Override
    protected void setListener() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //试着接受传递过来的字符串，如果有，说明是从设置界面跳转过来的
                if (!TextUtils.isEmpty(getIntent().getStringExtra("param"))){
                    //返回设置界面
                    LeadActivity.this.finish();
                    return;
                }
                //否则就跳转到主界面
                startActivity(MainActivity.class);
                finish();
            }
        });
        //创建适配器
        BasePagerAdapter adapter = new BasePagerAdapter(getSupportFragmentManager());
        //初始化viewpager要显示多少个页面
        adapter.setCount(3);
        //绑定适配器
        viewPager.setAdapter(adapter);

        //给ViewPager添加滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                for (int i = 0; i < icons.length; i++) {
                    icons[i].setImageResource(R.drawable.adware_style_default);
                }
                icons[position].setImageResource(R.drawable.adware_style_selected);

                if (position == icons.length - 1){
                    textView.setVisibility(View.VISIBLE);
                }else {
                    textView.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        //停止服务
        Intent ServiceIntent = new Intent(this, MusicService.class);
        stopService(ServiceIntent);
        finish();
    }

}
