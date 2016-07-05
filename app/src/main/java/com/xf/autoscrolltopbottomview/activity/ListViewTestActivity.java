package com.xf.autoscrolltopbottomview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.xf.autoscrolltopbottomview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ListView listView = (ListView) findViewById(R.id.list_view);
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("test", "topView" + i);
            list.add(map);
        }
        listView.setAdapter(new SimpleAdapter(this, list, R.layout.test_item, new String[]{"test"}, new int[]{R.id.test}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewTestActivity.this, "click" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
