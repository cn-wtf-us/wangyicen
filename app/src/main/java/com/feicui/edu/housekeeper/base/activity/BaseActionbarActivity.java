package com.feicui.edu.housekeeper.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;

public abstract class BaseActionbarActivity extends BaseActivity {

    public static final int NULL_ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setActionBar(int resIDTitle, int resIDLeft, int resIDRight){
        TextView tv_actionBar_title = (TextView) findViewById(R.id.tv_actionBar_title);
        ImageView iv_actionBar_left = (ImageView) findViewById(R.id.iv_actionBar_left);
        ImageView iv_actionBar_right = (ImageView) findViewById(R.id.iv_actionBar_right);

        if (resIDLeft != NULL_ID){
            iv_actionBar_left.setImageResource(resIDLeft);
        }else {
            iv_actionBar_left.setVisibility(View.INVISIBLE);
        }
        if (resIDRight != NULL_ID){
            iv_actionBar_right.setImageResource(resIDRight);
        }else {
            iv_actionBar_right.setVisibility(View.INVISIBLE);
        }
        if (resIDTitle != NULL_ID){
            tv_actionBar_title.setText(resIDTitle);
        }

    }

}
