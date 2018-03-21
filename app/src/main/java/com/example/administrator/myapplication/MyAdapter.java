package com.example.administrator.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Map<String,Object>> mdatalist;
    private static final int ITEM_VIEW=0;
    private static final int FOOT_VIEW=1;
    public MyAdapter(Context context, List<Map<String,Object>> datalist){
        this.mContext=context;
        this.mdatalist=datalist;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(mContext).inflate(R.layout.item_view,null);
        View footView=LayoutInflater.from(mContext).inflate(R.layout.foot_view,null);
        if(viewType==ITEM_VIEW){
            return new ViewHolder(itemView,viewType);
        }
        return new ViewHolder(footView,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder)holder;
        if(getItemViewType(position)==FOOT_VIEW){
            viewHolder.footView.setText("加载中...");
        }else {
            viewHolder.title.setText((String)mdatalist.get(position).get("title"));
            viewHolder.image.setImageResource((int)mdatalist.get(position).get("image"));
            viewHolder.info.setText((String)mdatalist.get(position).get("info"));
        }
    }

    @Override
    public int getItemCount() {
        return mdatalist.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==mdatalist.size()){
            return FOOT_VIEW;
        }else {
            return ITEM_VIEW;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView info;
        TextView footView;
        public ViewHolder(View itemView,int viewType) {
            super(itemView);
            if(viewType==ITEM_VIEW){
                image=(ImageView)itemView.findViewById(R.id.iv_image);
                title=(TextView)itemView.findViewById(R.id.tv_title);
                info=(TextView)itemView.findViewById(R.id.tv_info);
            }else if(viewType==FOOT_VIEW){
                footView=(TextView)itemView.findViewById(R.id.tv_foot_view);
            }
        }
    }
}
