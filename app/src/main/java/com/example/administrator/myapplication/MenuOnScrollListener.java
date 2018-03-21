package com.example.administrator.myapplication;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

//上拉加载
//继承RecyclerView.OnScrollListener
public abstract class MenuOnScrollListener extends RecyclerView.OnScrollListener {
    private int LastVisibleItemPosition=-1;
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if(newState==RecyclerView.SCROLL_STATE_IDLE&&LastVisibleItemPosition+1==recyclerView.getAdapter().getItemCount()){
            loadMore();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(dy>0){
            LinearLayoutManager linearLayoutManager=(LinearLayoutManager)recyclerView.getLayoutManager();
            LastVisibleItemPosition=linearLayoutManager.findLastVisibleItemPosition();
        }
    }

    public abstract void  loadMore();
}
