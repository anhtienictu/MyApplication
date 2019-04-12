package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    ListView lvten;
    TextView tvname;
    ArrayList<song> arraysong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lvten=(ListView) findViewById(R.id.lvten);
        tvname=(TextView) findViewById(R.id.tvname);
        addsong();


        customadapter customadapter=new customadapter(this,R.layout.namesong,arraysong);
        lvten.setAdapter(customadapter);

        lvten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                intent.putExtra("pos",position);
                startActivity(intent);
            }
        });

    }
    private void addsong() {
        arraysong=new ArrayList<>();
        arraysong.add(new song("Như có bác hồ", R.raw.nhucobacho));
        arraysong.add(new song("Đúng người đúng thời điểm", R.raw.dungnguoidungthoidiem));
        arraysong.add(new song("Em Đã Thấy Anh Cùng Người Ấy", R.raw.emdathayanhcungnguoiay));
        arraysong.add(new song("Hồng nhân", R.raw.hongnhan));

    }
}

