package com.feicui.edu.housekeeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.base.adapter.BasePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LeadActivity extends BaseActivity implements ViewPager.OnPageChangeListener,View.OnClickListener {
    private ViewPager viewPager;
    private BasePagerAdapter leadPagerAdapter;
    private List<View> views;

    private static final int[] icons = {R.drawable.adware_style_applist,
            R.drawable.adware_style_banner, R.drawable.adware_style_creditswall};
    private ImageView[] dots ;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(MainActivity.class);

        views = new ArrayList<View>();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        for (int i = 0; i < icons.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(layoutParams);
            iv.setImageResource(icons[i]);
            views.add(iv);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        leadPagerAdapter = new BasePagerAdapter(views);
        viewPager.setAdapter(leadPagerAdapter);

        viewPager.setOnPageChangeListener(this);
        initDots();



        /*viewPager = (ViewPager) findViewById(R.id.viewpager);
        icons[0] = (ImageView) findViewById(R.id.icon1);
        icons[1] = (ImageView) findViewById(R.id.icon2);
        icons[2] = (ImageView) findViewById(R.id.icon3);

        //创建适配器
        BasePagerAdapter adapter = new BasePagerAdapter(getSupportFragmentManager());
        //初始化viewpager要显示多少个页面
        adapter.setCount(3);
        //绑定适配器
        viewPager.setAdapter(adapter);

        //给ViewPager添加滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                for (int i = 0; i < icons.length; i++) {
                    icons[i].setBackgroundResource(R.drawable.adware_style_default);
                }
                icons[position].setBackgroundResource(R.drawable.adware_style_selected);
            }
        });*/

        /*String fromClassName = intent.getStringExtra("className");
        if (fromClassName != null && fromClassName.equals("SettingActivity")){
            isFromSetting = true;
        }
                //设置存储信息
        SharedPreferences preferences = getSharedPreferences("Lead_config.adn", Context.MODE_PRIVATE);
       boolean isFirstRun = preferences.getBoolean("isFirstRun", true);
       if (!isFirstRun){
            startActivity(MainActivity.class);
            finish();
        }else {//从当前引导界面开始执行
           setContentView(R.layout.activity_lead);
           //初始化引导界面图标和文字
            initLeadIcon();
            //初始化引导界面ViewPager视图控件
           initViewPager();
            //初始化引导界面ViewPager控件中的数据
            initPagerData();


        }
        finish();*/

    }

    private void initDots() {
        LinearLayout linearlayout = (LinearLayout) findViewById(R.id.linear_layout);

        dots = new ImageView[icons.length];

        //循环取得小点图片
        for (int i = 0; i < icons.length; i++) {
            dots[i] = (ImageView) linearlayout.getChildAt(i);
            dots[i].setEnabled(true);//都设为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);//设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(false);//设置为白色，即选中状态
    }

    private void setCurView(int position)
    {
        if (position < 0 || position >= icons.length) {
            return;
        }

        viewPager.setCurrentItem(position);
    }
    private void setCurDot(int positon)
    {
        if (positon < 0 || positon > icons.length - 1 || currentIndex == positon) {
            return;
        }

        dots[positon].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = positon;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        int position = (Integer)view.getTag();
        setCurView(position);
        setCurDot(position);
    }



    /*private void savePreferences(){
        SharedPreferences preferences = getSharedPreferences("Lead_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstRun", false);
        editor.commit();
    }

    //初始化图片文字
    private void initLeadIcon(){
        //初始化3个图片
        icons[0] = (ImageView) findViewById(R.id.icon1);
        icons[1] = (ImageView) findViewById(R.id.icon2);
        icons[2] = (ImageView) findViewById(R.id.icon3);
        icons[0].setImageResource(R.drawable.adware_style_selected);
        //初始化“直接跳过”的文字
        tv_skip = (TextView) findViewById(R.id.tv_skip);
        tv_skip.setVisibility(View.INVISIBLE);
        tv_skip.setOnClickListener(this);
    }

    //初始化ViewPager
    private void initViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        leadPagerAdapter = new BasePagerAdapter(this);
        viewPager.setAdapter(leadPagerAdapter);
        viewPager.setOnPageChangeListener(pageChangeListener);
    }

    //初始化页面信息
    private void initPagerData(){
        ImageView imageView = null;
        //第一张图片
        imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_lead_item, null);
        imageView.setImageResource(R.drawable.adware_style_applist);
        leadPagerAdapter.addViewToAdapter(imageView);
        //第二张图片
        imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_lead_item, null);
        imageView.setImageResource(R.drawable.adware_style_banner);
        leadPagerAdapter.addViewToAdapter(imageView);
        //第三张图片
        imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_lead_item, null);
        imageView.setImageResource(R.drawable.adware_style_creditswall);
        leadPagerAdapter.addViewToAdapter(imageView);

        leadPagerAdapter.notifyDataSetChanged();

    }
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //到达最后一个page时，显示出skip文本
            tv_skip.setVisibility(View.INVISIBLE);
            if (position >= 2){
                tv_skip.setVisibility(View.VISIBLE);
            }
            //更新下标图标
            for (int i = 0; i < icons.length; i ++){
                icons[i].setImageResource(R.drawable.adware_style_default);
            }
            icons[position].setImageResource(R.drawable.adware_style_selected);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View view) {
        //编辑保存配置信息
        savePreferences();
        //界面跳转
        if (isFromSetting){
            startActivity(SettingActivity.class);
        }else{
            startActivity(MainActivity.class);
        }
        finish();
    }*/
}
