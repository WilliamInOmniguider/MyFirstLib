package com.wcom.wheeltest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wiliiamwang on 06/10/2017.
 */

public class WView extends View {

    private Paint paint;

    public WView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();
        int radius;
        radius = x / 2;
        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.BLUE);
//        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
//        paint.setColor(Color.parseColor("#3c29b1"));
        paint.setShader(new LinearGradient(0, 0, 0, getHeight(),
                getResources().getColor(R.color.prehistory_gradient_end),
                getResources().getColor(R.color.prehistory_gradient_start),
                Shader.TileMode.MIRROR));
        canvas.drawCircle(x / 2, y / 2, radius, paint);
    }
}
