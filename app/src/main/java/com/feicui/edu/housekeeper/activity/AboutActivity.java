package com.feicui.edu.housekeeper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.view.ActionBarView;

public class AboutActivity extends AppCompatActivity {
    private ActionBarView bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        bar = (ActionBarView) findViewById(R.id.view_action_bar);

        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutActivity.this.finish();
            }
        };
        bar.initActionBar("关 于", R.drawable.home_left, ActionBarView.ID_BAR, on);

    }

}
