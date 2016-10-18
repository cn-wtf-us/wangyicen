package com.feicui.edu.housekeeper.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.adapter.FileMgrListAdapter;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.base.utils.FileTypeUtil;
import com.feicui.edu.housekeeper.biz.FileManager;
import com.feicui.edu.housekeeper.entity.FileInfo;
import com.feicui.edu.housekeeper.view.ActionBarView;

import java.io.File;
import java.util.ArrayList;


public class FileMgrListActivity extends BaseActivity {

    private FileMgrListAdapter adapter;
    private ActionBarView bar;
    private String title;
    private TextView tv1, tv2;
    private ListView lv;
    private Button bt;

    private long size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_file_mgr_list);
        title = getIntent().getStringExtra("title");
        super.onCreate(savedInstanceState);

        switch (title){
            case "全部":
                size = FileManager.getInstance().getAllSize();
                adapter.addDatas(FileManager.getInstance().getAllDatas());
                break;
            case "文档":
                size = FileManager.getInstance().getDocSize();
                adapter.addDatas(FileManager.getInstance().getDocDatas());
                break;
            case "音频":
                size = FileManager.getInstance().getAdSize();
                adapter.addDatas(FileManager.getInstance().getAdDatas());
                break;
            case "视频":
                size = FileManager.getInstance().getVdSize();
                adapter.addDatas(FileManager.getInstance().getVdDatas());
                break;
            case "图片":
                size = FileManager.getInstance().getPicSize();
                adapter.addDatas(FileManager.getInstance().getPicDatas());
                break;
            case "压缩包":
                size = FileManager.getInstance().getRarSize();
                adapter.addDatas(FileManager.getInstance().getRarDatas());
                break;
            case "程序包":
                size = FileManager.getInstance().getApkSize();
                adapter.addDatas(FileManager.getInstance().getApkDatas());
                break;
        }

        //设置文件的数量
        tv1.setText(adapter.getDatas().size() + "个");
        //设置所有文件的总大小
        tv2.setText(Formatter.formatFileSize(this, size));
        //设置适配器
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
        //列表条目的监听
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //找到所需要打开的文件
                ArrayList<FileInfo> fileInfos = adapter.getDatas();
                FileInfo fileInfo = fileInfos.get(position);
                File file = fileInfo.getFile();
                //打开对应的应用程序
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //设置数据,mimeType
                intent.setDataAndType(Uri.fromFile(file),
                        FileTypeUtil.getMimeTypeFromFileName(file));
                startActivity(intent);

            }
        });

        //删除所选文件的监听
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
