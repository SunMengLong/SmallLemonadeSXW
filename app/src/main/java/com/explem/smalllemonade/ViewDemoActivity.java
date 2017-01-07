package com.explem.smalllemonade;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ViewDemoActivity extends AppCompatActivity {
    private  TextView tv;
    private com.explem.smalllemonade.view.LoadingView loadView;
    Handler    handler=new    Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            loadView.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
            startActivity(new Intent(ViewDemoActivity.this,MainActivity.class));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_demo);
        tv=(TextView)findViewById(R.id.tv);
        loadView=(com.explem.smalllemonade.view.LoadingView)findViewById(R.id.loadView);
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_view_demo, menu);
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