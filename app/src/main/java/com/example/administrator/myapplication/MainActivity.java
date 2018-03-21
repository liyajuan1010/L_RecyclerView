package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private List<Map<String,Object>> mdatalist;
    private LinearLayoutManager mLinearLaoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Handler mHandler=new Handler();//模拟延迟，选os包
    private ProgressBar mProgressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        mSwipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        mProgressbar=(ProgressBar)findViewById(R.id.progressbar);
        initdata();
        myAdapter=new MyAdapter(this,mdatalist);
        mLinearLaoutManager=new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLinearLaoutManager);//设置布局方式

        mRecyclerView.addItemDecoration(new MenuItemDecoration());//添加分割线

        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.addOnScrollListener(new MenuOnScrollListener() {
            @Override
            public void loadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("image", R.mipmap.ic_launcher);
                        map.put("title", "LLL");
                        map.put("info", "1010");
                        mdatalist.add(map);
                        myAdapter.notifyDataSetChanged();
                        mRecyclerView.smoothScrollToPosition(mdatalist.size()-1);
                    }
                },2000);
            }
        });

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setAdapter(myAdapter);
                mProgressbar.setVisibility(View.GONE);//设置控件隐藏
            }
        },4000);
    }

    private void initdata() {
        int[] image={R.drawable.gongbaojiding,R.drawable.shuizhuroupian,R.drawable.suanlajidantang,R.drawable.xihucuyu,R.drawable.yuxiangrousi};
        String[] title={"宫保鸡丁","水煮肉片","酸辣鸡蛋汤","西湖醋鱼","鱼香肉丝"};
        String[] info={"11","22","33","44","55"};
        mdatalist=new ArrayList<Map<String,Object>>();
        for(int i=0;i<5;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("image",image[i]);
            map.put("title",title[i]);
            map.put("info",info[i]);
            mdatalist.add(map);
        }
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //清空原始数据
                mRecyclerView.removeAllViews();
                mdatalist.clear();
                for(int i=0;i<10;i++){
                    Map<String,Object> map=new HashMap<>();
                    map.put("image",R.mipmap.ic_launcher);
                    map.put("title","new Title");
                    map.put("info","999");
                    mdatalist.add(map);
                }
//                数据重新加载完成后，提示数据发生改变，并且设置现在不再刷新
//                myAdapter.notifyDataSetChanged();
                 mSwipeRefreshLayout.setRefreshing(false);
            }
        },2000);
    }
}
