package com.feicui.edu.housekeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.feicui.edu.housekeeper.adapter.TelclassAdapter;
import com.feicui.edu.housekeeper.db.AssetsDBManager;
import com.feicui.edu.housekeeper.db.DBReader;
import com.feicui.edu.housekeeper.entity.TelclassInfo;
import com.feicui.edu.housekeeper.utils.ToastUtil;

import java.io.IOException;

public class TelmsgActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private TelclassAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telmsg);

        listView = (ListView) findViewById(R.id.lv_tel);
        adapter = new TelclassAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }
    //初始化数据库文件
    private void initAppDBFile(){
        //检测是否存在DB文件
        if (!DBReader.isExistsTeldbFile()){
            try {
                AssetsDBManager.copyAssetsFileToFile(getApplicationContext(), "commonnum.db", DBReader.telFile);
            } catch (IOException e) {
                ToastUtil.show(this, "数据库文件异常", Toast.LENGTH_SHORT);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //适配数据
        initAppDBFile();
        adapter.clearDataTOAdapter();
        adapter.addDataToAdapter(new TelclassInfo("本地电话", 0));//本地电话分类
        try {
            adapter.addDataToAdapter(DBReader.readTeldbClasslist());
        } catch (Exception e) {
            e.printStackTrace();
        }//db库内的电话分类
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //本地通话
        if (position == 0){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            startActivity(intent);
            return;
        }
        //取出当前选择的选项实体内容
        TelclassInfo classInfo = adapter.getItem(position);


    }
}
