package com.feicui.edu.housekeeper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.feicui.edu.housekeeper.R;

/**
 * Created by asus on 2016/10/11.
 */
public class SoftMgrCanvas extends View {

    private Paint paint;

    public SoftMgrCanvas(Context context) {
        this(context, null);
    }

    public SoftMgrCanvas(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SoftMgrCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.rgb(214, 152, 113));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(150, 150, 150, paint);
    }
}
