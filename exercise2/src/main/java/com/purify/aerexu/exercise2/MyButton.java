package com.purify.aerexu.exercise2;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.Button;

/**
 * Created by AereXu on 2015/9/16.
 */
public class MyButton extends Button{
    public MyButton(Context context){
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        if (isPressed())
            canvas.drawColor(0x6F23fa55);
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        // TODO Auto-generated method stub
        super.dispatchSetPressed(pressed);
        invalidate();
    }
}
