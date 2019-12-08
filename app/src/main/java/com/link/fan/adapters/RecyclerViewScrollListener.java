package com.link.fan.adapters;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    /*each offset of item*/
    private int mOffset;
    /*get this by trigonometric function 3 / 5, and opposite is list item height*/
    private int mHeight;

    private float mItemHeight;
    private float mRate;
    private int mMinItemCount;
    private int mLoadedCount;

    public RecyclerViewScrollListener(int offset) {
        mOffset = offset;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager)recyclerView.getLayoutManager();
            int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
            View firstView = manager.findViewByPosition(firstVisibleItemPosition);
            if (firstView == null) {
                return;
            }
            int lastCompletelyVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition();
            mLoadedCount = lastCompletelyVisibleItemPosition - firstVisibleItemPosition
                    + 3;

            mItemHeight = firstView.getHeight();
            if (mMinItemCount == 0) {
                mMinItemCount = (int) Math.floor(mHeight / mItemHeight);
            }
            mRate = mItemHeight / mOffset;

            int top = firstView.getTop();
            for (int i = 0; i < mLoadedCount; i++) {
                View view = manager.findViewByPosition(firstVisibleItemPosition + i);
                if (view != null) {
                    float offset = mOffset * (mMinItemCount - i - 1);
                    view.setTranslationX(Math.abs(top / mRate) + offset);
                }
            }
        }
    }

}
