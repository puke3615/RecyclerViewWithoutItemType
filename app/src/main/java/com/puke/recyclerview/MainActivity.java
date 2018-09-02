package com.puke.recyclerview;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.puke.recyclerview.base.BaseAdapter;
import com.puke.recyclerview.base.HolderContext;
import com.puke.recyclerview.base.IType;
import com.puke.recyclerview.viewholder.AViewHolder;
import com.puke.recyclerview.viewholder.BViewHolder;
import com.puke.recyclerview.viewholder.CViewHolder;
import com.puke.recyclerview.viewholder.DViewHolder;
import com.puke.recyclerview.viewholder.EViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initAdapter();
        loadMockData();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        refreshLayout = findViewById(R.id.recycler_layout);
        refreshLayout.setOnRefreshListener(this);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void initAdapter() {
        HolderContext holderContext = new HolderContext() {
            @Override
            public void onClickA() {
                Toast.makeText(MainActivity.this, "Click Item A", Toast.LENGTH_SHORT).show();
            }
        };
        adapter = new BaseAdapter(holderContext, Arrays.asList(
                AViewHolder.FACTORY,
                new BViewHolder.Factory(),
                new CViewHolder.Factory(),
                new DViewHolder.Factory(),
                new EViewHolder.Factory()
        ));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    // 模拟后端返回数据
    private void loadMockData() {
        List<IType> dataList = new ArrayList<>();
        Random random = new Random();
        final String[] types = {"typeA", "typeB", "typeC", "typeD", "typeE"};
        for (int i = 0; i < 50; i++) {
            ItemData data = new ItemData();
            data.type = types[random.nextInt(types.length)];
            switch (data.type) {
                case "typeA":
                    data.textA = "AAA";
                    break;
                case "typeB":
                    data.textB = "BBB";
                    break;
                case "typeC":
                    data.textC = "CCC";
                    break;
                case "typeD":
                    data.textD = "DDD";
                    break;
                case "typeE":
                    data.textE = "EEE";
                    break;
            }
            dataList.add(data);
        }
        adapter.setDataSource(dataList);
    }

    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadMockData();
                refreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
