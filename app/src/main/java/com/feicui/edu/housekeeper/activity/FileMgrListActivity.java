package com.feicui.edu.housekeeper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.adapter.FileMgrListAdapter;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.biz.FileManager;
import com.feicui.edu.housekeeper.view.ActionBarView;


public class FileMgrListActivity extends BaseActivity {

    private FileMgrListAdapter adapter;
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

        switch (title){
            case "全部":
                adapter.addDatas(FileManager.getInstance().getAllDatas());
                break;
            case "文档":
                adapter.addDatas(FileManager.getInstance().getDocDatas());
                break;
            case "视频":
                adapter.addDatas(FileManager.getInstance().getAdDatas());
                break;
            case "音频":
                adapter.addDatas(FileManager.getInstance().getVdDatas());
                break;
            case "图片":
                adapter.addDatas(FileManager.getInstance().getPicDatas());
                break;
            case "压缩包":
                adapter.addDatas(FileManager.getInstance().getRarDatas());
                break;
            case "程序包":
                adapter.addDatas(FileManager.getInstance().getApkDatas());
                break;


        }

        lv.setAdapter(adapter);

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
        tv1 = (TextView) findViewById(R.id.file_mgr_list_tv1);
        tv2 = (TextView) findViewById(R.id.file_mgr_list_tv2);
        lv = (ListView) findViewById(R.id.file_mgr_list_lv);
        bt = (Button) findViewById(R.id.file_mgr_list_bt);
        adapter = new FileMgrListAdapter(this);
    }

    @Override
    protected void setListener() {
        //删除所选文件的监听
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
