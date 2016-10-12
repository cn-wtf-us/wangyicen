package com.feicui.edu.housekeeper.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.feicui.edu.housekeeper.R;

public class CanvasActivity extends AppCompatActivity {

    private RunningText runningText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        runningText = (RunningText) findViewById(R.id.running_text);

    }

    public void test(View view){
        switch (view.getId()){
            case R.id.start:
                runningText.startMove();
                break;
            case R.id.stop:
                runningText.stopMove();
                break;
        }
    }
}
