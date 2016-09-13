package com.feicui.edu.housekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.feicui.edu.housekeeper.adapter.TelclassAdapter;

public class TelmsgActivity extends AppCompatActivity {
    private ListView listView;
    private TelclassAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telmsg);

        listView = (ListView) findViewById(R.id.lv_tel);
        adapter = new TelclassAdapter(this);
        listView.setAdapter(adapter);



    }
}
