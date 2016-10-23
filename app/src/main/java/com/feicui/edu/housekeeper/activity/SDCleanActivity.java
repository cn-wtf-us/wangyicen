package com.feicui.edu.housekeeper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.adapter.SDCleanAdapter;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.base.utils.ToastUtil;
import com.feicui.edu.housekeeper.view.ActionBarView;


public class SDCleanActivity extends BaseActivity {

    private ActionBarView bar;
    private ListView listView;
    private SDCleanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sdclean);
        super.onCreate(savedInstanceState);
        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SDCleanActivity.this.finish();
            }
        };
        bar.initActionBar("垃圾清理", R.drawable.home_left, ActionBarView.ID_BAR, on);
    }

    @Override
    protected void initView() {
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        listView = (ListView) findViewById(R.id.sd_clean_lv);
        adapter = new SDCleanAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void setListener() {


    }

    public void cleanApp(View view){
        Toast.makeText(this, "我只是一个摆设...", Toast.LENGTH_LONG).show();
    }
}
