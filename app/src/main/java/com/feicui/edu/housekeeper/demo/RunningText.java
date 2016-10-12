package com.feicui.edu.housekeeper.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
public class RunningText extends View {

    private Paint paint;
    private float x;
    private Thread thread;
    private boolean isRun = true;

    private final String TEXT = "从你的全世界路过...";

    public RunningText(Context context) {
        this(context, null);
    }

    public RunningText(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(30);
        //线程启动
        thread= new Thread(runnable);

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while(isRun){
                //更新x轴坐标
                x ++;
                if (x >= getWidth()){
                    x = -paint.measureText(TEXT);
                }
                //重新绘制文字
                postInvalidate();
                //设置速度
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void startMove(){
        isRun = true;
        thread.start();
    }

    public void stopMove(){
        isRun = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(TEXT, x, 100, paint);
    }
}
