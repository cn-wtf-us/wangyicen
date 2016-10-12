package com.feicui.edu.housekeeper.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by asus on 2016/10/11.
 */
public class MyCanvas extends View {
    //画笔
    private Paint paint;

    public MyCanvas(Context context) {
        this(context, null);
    }

    public MyCanvas(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();

        paint.setColor(Color.rgb(33, 99, 51));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(100, 100, 50, paint);

        Path path = new Path();
        path.moveTo(300, 400);
        path.lineTo(500, 400);
        path.lineTo(700, 600);
        path.lineTo(500, 800);
        path.lineTo(300, 800);
        path.lineTo(100, 600);
        path.close();
        canvas.drawPath(path, paint);

    }

}
