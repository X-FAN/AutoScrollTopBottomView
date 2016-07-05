package com.xf.autoscrolltopbottomview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xf.autoscrolltopbottomview.R;

public class ScrollViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_test);
        findViewById(R.id.tv0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollViewTestActivity.this, "view0", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollViewTestActivity.this, "view1", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollViewTestActivity.this, "view2", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollViewTestActivity.this, "view3", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollViewTestActivity.this, "view4", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollViewTestActivity.this, "view5", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollViewTestActivity.this, "view6", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
