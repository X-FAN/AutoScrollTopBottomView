package com.xf.autoscrolltopbottomview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xf.autoscrolltopbottomview.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.scrollview_test).setOnClickListener(this);
        findViewById(R.id.listview_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.scrollview_test:
                intent.setClass(MainActivity.this,ScrollViewTestActivity.class);
                startActivity(intent);
                break;
            case R.id.listview_test:
                intent.setClass(MainActivity.this,ListViewTestActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
