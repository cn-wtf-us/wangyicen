package com.feicui.edu.housekeeper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by asus on 2016/10/11.
 */
public class SoftMgrPiechart extends View {

    private Paint paint, paint1, paint2;
    private RectF rectF;
    private float phoneAngel, sdAngel;
    private float curPhoneAngel, curSDAngel;
    private boolean isRun = true;

    private Thread thread;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            while (isRun){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (curPhoneAngel > phoneAngel){
                    curPhoneAngel = phoneAngel;
                }
                if (curSDAngel > sdAngel){
                    curSDAngel = sdAngel;
                }
                curPhoneAngel += 2;
                curSDAngel += 2;
                if (curPhoneAngel == phoneAngel && curSDAngel == sdAngel){
                    isRun = false;
                }
                postInvalidate();
            }
        }
    };

    public void startDraw(){
        thread = new Thread(runnable);
        thread.start();
    }

    public SoftMgrPiechart(Context context) {
        this(context, null);
    }

    public SoftMgrPiechart(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint1 = new Paint();//手机存储
        paint2 = new Paint();//SD卡存储
        paint.setColor(Color.rgb(175, 200, 245));
        paint1.setColor(Color.rgb(0, 136, 255));
        paint2.setColor(Color.rgb(140, 80, 255));
    }

    public void setAngel(float phoneAngel, float sdAngel){
        this.phoneAngel = phoneAngel;
        this.sdAngel = sdAngel;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        rectF = new RectF(0, 0 , width, height);
        //设置测量的尺寸到控件上,必须要写，否则会报错的
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆
        canvas.drawArc(rectF, -90, 360, true, paint);
        //手机内存
        canvas.drawArc(rectF, -90, phoneAngel, true, paint1);
        //SD卡内存
        canvas.drawArc(rectF, (-90 + phoneAngel), sdAngel, true, paint2);
//        canvas.drawCircle(150, 150, 150, paint);
    }
}
