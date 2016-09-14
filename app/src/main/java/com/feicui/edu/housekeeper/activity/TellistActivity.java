package com.feicui.edu.housekeeper.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.adapter.TellistAdapter;
import com.feicui.edu.housekeeper.db.DBReader;

import java.io.IOException;

public class TellistActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private TellistAdapter adapter;
    private int idx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel_list);

        //获取数据用来判断是显示哪一种分类的电话号码
        idx = getIntent().getIntExtra("idx", -1);

        listView = (ListView) findViewById(R.id.list_item);
        listView.setOnItemClickListener(this);
        adapter = new TellistAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //添加数据
        try {
            adapter.addDataToAdapter(DBReader.readTeldbTable(idx));
        } catch (Exception e) {
            e.printStackTrace();
        }//db库内的电话分类
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = adapter.getItem(position).name;
        String number = adapter.getItem(position).number;
        showCallDialog(name, number);
    }

    private void showCallDialog(final String name, final String number){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage("是否开始拨打" + name + "电话？\n\nTEL:" + number);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("拨号", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //电话拨打
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel://" + number));
                //startActivity(intent);

            }
        });
        builder.show();

    }
}
