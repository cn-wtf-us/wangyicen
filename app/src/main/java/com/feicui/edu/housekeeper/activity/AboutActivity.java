package com.feicui.edu.housekeeper.activity;

import android.os.Bundle;
import android.view.View;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.view.ActionBarView;

public class AboutActivity extends BaseActivity {
    private ActionBarView bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_about);
        super.onCreate(savedInstanceState);

        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutActivity.this.finish();
            }
        };
        bar.initActionBar("关 于", R.drawable.home_left, ActionBarView.ID_BAR, on);

    }

    @Override
    protected void initView() {
        bar = (ActionBarView) findViewById(R.id.view_action_bar);

    }

    @Override
    protected void setListener() {

    }

}
