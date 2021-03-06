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
    private float maxAngel;
    private int state = 0;
    private final int STATE_BACK = 0;
    private final int STATE_GO = 1;
    private boolean isRun = true;
    //设置数组
    private int[] speed = {1, 1, 1, 2, 2, 2, 4, 4, 4, 8, 8, 8, 12, 12, 12};

    private Thread thread;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int index = 0;
            if (!isRun){
                return;
            }
            isRun = true;
            while (isRun){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (state){
                    case STATE_BACK:
                        if (angel < 0){
                            angel = 0;
                            //设置前进
                            state = STATE_GO;
                        }
                        if (index >= speed.length){
                            index = speed.length - 1;
                        }
                        angel -= speed[index];
                        index++;
                        break;
                    case STATE_GO:
                        if (angel > maxAngel){
                            angel = maxAngel;
                            //结束循环
                            isRun = false;
                            state = STATE_BACK;
                        }
                        if (index >= speed.length){
                            index = speed.length - 1;
                        }
                        angel += speed[index];
                        index++;
                        break;
                }
                postInvalidate();
            }
        }
    };

    public void setMaxAngel(float maxAngel){
        this.maxAngel = maxAngel;
    }

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
        canvas.drawArc(rectF, -90, angel, true, paint);
    }
}
