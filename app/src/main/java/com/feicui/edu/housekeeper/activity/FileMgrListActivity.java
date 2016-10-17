package com.feicui.edu.housekeeper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.view.ActionBarView;


public class FileMgrListActivity extends BaseActivity {

    private ActionBarView bar;
    private String title;
    private TextView tv1, tv2;
    private ListView lv;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_file_mgr_list);
        title = getIntent().getStringExtra("title");
        super.onCreate(savedInstanceState);

        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileMgrListActivity.this.finish();
            }
        };
        bar.initActionBar(title, R.id.iv_left, ActionBarView.ID_BAR, on);

    }

    @Override
    protected void initView() {
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        tv1 = (TextView) findViewById(R.id.file_mgr_list_item_tv1);
        tv2 = (TextView) findViewById(R.id.file_mgr_list_item_tv2);
        lv = (ListView) findViewById(R.id.file_mgr_list_item_lv);
        bt = (Button) findViewById(R.id.file_mgr_list_item_bt);
    }

    @Override
    protected void setListener() {

    }
}
