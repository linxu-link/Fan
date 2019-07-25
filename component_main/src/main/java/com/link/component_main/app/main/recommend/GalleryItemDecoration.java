package com.link.component_main.app.main.recommend;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import androidx.recyclerview.widget.RecyclerView;

@Deprecated
public class GalleryItemDecoration extends RecyclerView.ItemDecoration{

    int mPageMargin = 15 ;//自定义默认item边距
    int mLeftPageVisibleWidth  ;//第一张图片的左边距

    public GalleryItemDecoration(Context context)
    {
        WindowManager manager = ((Activity)context).getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        mLeftPageVisibleWidth = (int) (((width /Resources.getSystem().getDisplayMetrics().density) - 200 - mPageMargin) / 2);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int positon = parent.getChildAdapterPosition(view); //获得当前item的position
        int itemCount = parent.getAdapter().getItemCount(); //获得item的数量
        int leftMargin;
        if (positon == 0)
        {
            leftMargin = dpToPx(mLeftPageVisibleWidth);
        }else
        {
            leftMargin = dpToPx(mPageMargin);
        }
        int rightMargin;
        if (positon == itemCount - 1)
        {
            rightMargin = dpToPx(mLeftPageVisibleWidth);
        }else
        {
            rightMargin = dpToPx(mPageMargin);
        }
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.setMargins(leftMargin, 30, rightMargin, 60);
        view.setLayoutParams(lp);
        super.getItemOffsets(outRect, view, parent, state);
    }

    private int dpToPx(int dp){
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density + 0.5f); //dp转px
    }
}
