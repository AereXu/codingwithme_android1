package com.purify.aerexu.exercise3;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private Button button;
    private boolean butFlag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.mTextView);
        button = (Button) findViewById(R.id.mButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(butFlag){
                    imageView = (ImageView)findViewById(R.id.mImageView1);
                    butFlag = false;
                }else {
                    imageView = (ImageView)findViewById(R.id.mImageView2);
                    butFlag = true;
                }
                textView.setBackground(imageView.getBackground());
                Bitmap screenshot;
                screenshot = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(screenshot);
                imageView.draw(c);
                View view = textView;
                Bitmap overlay = Bitmap.createBitmap(view.getMeasuredWidth(),
                        view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(overlay);
                canvas.translate(-view.getLeft(), -view.getTop());
                Paint paint = new Paint();
                paint.setFlags(Paint.FILTER_BITMAP_FLAG);
                canvas.drawBitmap(screenshot, 0, 0, paint);
                view.setBackground(new BitmapDrawable(getResources(), overlay));
            }
        });

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
}
