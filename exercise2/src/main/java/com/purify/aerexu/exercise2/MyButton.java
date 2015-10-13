package com.purify.aerexu.exercise2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Button;

/**
 * Created by AereXu on 2015/9/16.
 */
public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        if (isPressed()) {
            canvas.drawColor(0x6F23fa55);

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(2);

            canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2,
                    (canvas.getWidth()<canvas.getHeight()?canvas.getWidth():canvas.getHeight())/4, paint);
        }
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        // TODO Auto-generated method stub
        super.dispatchSetPressed(pressed);
        invalidate();
    }
}
