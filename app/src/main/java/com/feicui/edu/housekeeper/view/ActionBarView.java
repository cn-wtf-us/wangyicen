package com.feicui.edu.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class ActionBarView extends LinearLayout {
    private ImageView iv_actionbar_left;//左边图片
    private TextView tv_actionbar_title;//中间文字
    private ImageView iv_actionbar_right;//右边图片

    public ActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.activity_actionbar, this);
        iv_actionbar_left = (ImageView) findViewById(R.id.iv_left);
        iv_actionbar_right = (ImageView) findViewById(R.id.iv_right);
        tv_actionbar_title = (TextView) findViewById(R.id.tv_title);
    }

    /**
     * 初始化actionbar
     * @param title         中间标题文字
     * @param leftResID     左边图像资源ID
     * @param rightResID    右边图像资源ID
     * @param listener      左右两边图标的监听
     */
    public void initActionBar(String title, int leftResID, int rightResID,OnClickListener listener){
        tv_actionbar_title.setText(title);
        if (leftResID == -1) {
            iv_actionbar_left.setVisibility(View.INVISIBLE);
        } else {
            iv_actionbar_left.setImageResource(leftResID);
            iv_actionbar_left.setOnClickListener(listener);
        }
        if (rightResID == -1) {
            iv_actionbar_right.setVisibility(View.INVISIBLE);
        } else {
            iv_actionbar_right.setImageResource(rightResID);
            iv_actionbar_right.setOnClickListener(listener);
        }

    }
}
