package com.purify.aerexu.exercise4;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private ImageView imageView;
    private TextView textView;
    private SeekBar radiusSeekBar;
    private SeekBar scaleSeekBar;
    private Button button;
    private boolean blurFlag = true;
    float scaleFactor = 1;
    float radius = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.mTextView);
        radiusSeekBar = (SeekBar) findViewById(R.id.seekBarRadius);
        radiusSeekBar.setOnSeekBarChangeListener(this);
        scaleSeekBar = (SeekBar) findViewById(R.id.seekBarScale);
        scaleSeekBar.setOnSeekBarChangeListener(this);
        button = (Button) findViewById(R.id.mButton);
        imageView = (ImageView) findViewById(R.id.mImageView1);
        textView.setBackground(imageView.getBackground());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (blurFlag) {
                    button.setText("UnDraw");
                    blurFlag = false;
                } else {
                    button.setText("Draw");
                    blurFlag = true;
                }
                drawBackground();
            }
        });

    }

    private void drawBackground() {
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(screenshot);
        imageView.draw(c);
        View view = textView;
        Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);

        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop() / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(screenshot, 0, 0, paint);
        if (!blurFlag) {
            RenderScript rs = RenderScript.create(getApplicationContext());
            Allocation overlayAlloc = Allocation.createFromBitmap(rs, overlay);
            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, overlayAlloc.getElement());
            blur.setInput(overlayAlloc);
            blur.setRadius(radius);
            blur.forEach(overlayAlloc);
            overlayAlloc.copyTo(overlay);
        }
        view.setBackground(new BitmapDrawable(getResources(), overlay));

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar == radiusSeekBar) {
            radius = transformSeekBarValue(seekBar, 1, 20);
            drawBackground();
        } else if (seekBar == scaleSeekBar) {
            scaleFactor = transformSeekBarValue(seekBar, 1, 10);
            drawBackground();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static float transformSeekBarValue(SeekBar seekBar, int min, int max) {
        float value = seekBar.getProgress() / (float) seekBar.getMax();
        float span = max - min;
        return min + value * span;
    }
}
