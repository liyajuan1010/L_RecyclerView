package com.example.administrator.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

//继承RecyclerView.ItemDecoration
public class MenuItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private static final int mDividerHeight=50;
    public MenuItemDecoration(){
        mPaint=new Paint();
        mPaint.setColor(Color.RED);
    }

    //绘制分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount=parent.getChildCount();//获取当前屏幕显示几项
        float dividerLeft=parent.getPaddingLeft();
        float dividerRight=parent.getWidth()-parent.getPaddingRight();
        for(int i=0;i<childCount;i++){
            View itemView=parent.getChildAt(i);//获取屏幕中第i项所在的位置
            int index=parent.getChildAdapterPosition(itemView);
            if(index==0){
                continue;//第一个ItemView不需要在上面绘制分割线
            }
            float dividerTop=itemView.getTop()-mDividerHeight;
            float dividerBottom=itemView.getTop();
//            if (i%2==0){
//                mPaint.setColor(Color.BLUE);
//            }else {
//                mPaint.setColor(Color.RED);
//            }
            c.drawRect(dividerLeft,dividerTop,dividerRight,dividerBottom,mPaint);
        }
    }

    //设置itemView偏移量
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(parent.getChildAdapterPosition(view)!=0){//获取当前view对象所处的位置
            outRect.top=mDividerHeight;
        }
    }
}
