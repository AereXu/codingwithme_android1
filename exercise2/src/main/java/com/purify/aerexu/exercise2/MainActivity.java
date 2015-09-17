package com.purify.aerexu.exercise2;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();

        linearLayout = (LinearLayout)inflater.inflate(R.layout.activity_main,null,false);
        MyButton testBut = new MyButton(this);
        testBut.setText("MyButton");
        testBut.setId(R.id.mMyButton);
        linearLayout.addView(testBut, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(linearLayout);
        testBut.setOnClickListener(this);
        linearLayout.findViewById(R.id.mButton).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        TextView textView= (TextView)findViewById(R.id.mTextView);
        switch (v.getId()){
            case R.id.mButton:
                textView.setText("Button1");
                break;
            case R.id.mMyButton:
                textView.setText("Button2");
                break;
        }
    }


}
