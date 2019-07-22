package com.zk.design;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zk.design.adapter.IRecycleViewData;
import com.zk.design.adapter.RecycleViewAdapter;
import com.zk.design.adapter.RecycleViewHolder;
import com.zk.design.bean.LeftMessage;
import com.zk.design.bean.RightMessage;
import com.zk.design.bean.TimeMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        recyclerView = findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        initAdapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
        if (adapter != null) {
            adapter.clear();
            adapter = null;
        }
    }

    private void initAdapter() {
        adapter = new RecycleViewAdapter() {
            @Override
            protected void bindData(@NonNull RecycleViewHolder viewHolder, IRecycleViewData data, int position) {
                if (data instanceof TimeMessage) {
                    viewHolder.setText(R.id.time, ((TimeMessage) data).getTime());
                } else if (data instanceof LeftMessage) {
                    viewHolder.setText(R.id.message, ((LeftMessage) data).getMessage());
                } else if (data instanceof RightMessage) {
                    viewHolder.setText(R.id.message, ((RightMessage) data).getMessage());
                }
            }
        };
        recyclerView.setAdapter(adapter);
        addData();
    }

    private void addData() {
        List<IRecycleViewData> data = new ArrayList<>();
        data.add(new TimeMessage(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())));
        data.add(new LeftMessage("你好呀"));
        data.add(new RightMessage("很高兴认识你"));
        adapter.addData(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                addData();
                break;
            case R.id.delete:
                adapter.removeData(0);
                break;
        }
    }
}
