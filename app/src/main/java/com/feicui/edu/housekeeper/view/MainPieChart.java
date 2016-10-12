package com.feicui.edu.housekeeper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.feicui.edu.housekeeper.R;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
public class MainPieChart extends View {

    private Paint paint;
    private RectF rectF;
    private float angel;
    private Thread thread;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (angel < 0){
                angel = 0;
            }
            angel -= 10;

            postInvalidate();
        }
    };

    public MainPieChart(Context context) {
        this(context, null);
    }

    public MainPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        setAngel(360);
        paint.setColor(Color.rgb(240, 30, 100));
        paint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.main_pie_chart));

    }

    public void startMove(){
        thread = new Thread(runnable);
        thread.start();
    }

    public void setAngel(float angel){
        this.angel = angel;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        rectF = new RectF(0, 0, width, height);

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆环
        canvas.drawArc(rectF, -90, angel, false, paint);
    }
}
