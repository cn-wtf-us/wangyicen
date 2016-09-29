package com.feicui.edu.housekeeper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.view.ActionBarView;

public class SoftMgrListActivity extends AppCompatActivity {
    private ActionBarView bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_mgr_list);
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        bar.initActionBar("所有软件",R.drawable.home_left, ActionBarView.ID_BAR, null);
    }
}
