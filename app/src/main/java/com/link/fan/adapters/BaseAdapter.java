package com.link.fan.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_NORMAL = -1;

    protected ArrayList<T> mDataList = new ArrayList<>();

    protected List<View> mHeaderViews = new ArrayList<>();

    protected OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    public void addHeaderView(View headerView) {
        mHeaderViews.add(headerView);
        notifyItemInserted(0);
    }

    public View getHeaderView(int pos) {
        if (pos <= mHeaderViews.size()) {
            return mHeaderViews.get(pos);
        } else {
            return null;
        }
    }

    public void addDataList(ArrayList<T> dataList) {//刷新数据
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderViews.size() == 0) {
            return TYPE_NORMAL;
        }
        for (int i = 0; i < mHeaderViews.size(); i++) {
            if (position == i) {
                return position;
            }
        }
        return TYPE_NORMAL;
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, final int viewType) {
        if (mHeaderViews.size() != 0 && viewType != TYPE_NORMAL) {
            return new Holder(mHeaderViews.get(viewType));
        }
        return onCreate(parent, viewType);
    }


    @Override
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) != TYPE_NORMAL) {
            return;
        }

        final int pos = getRealPosition(viewHolder);
        final T data = mDataList.get(pos);
        onBind(viewHolder, pos, data);

        if (mListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(pos, data);
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) != TYPE_NORMAL
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(@NotNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderViews.size() == 0 ? position : position - mHeaderViews.size();
    }

    @Override
    public int getItemCount() {
        return mHeaderViews.size() == 0 ? mDataList.size() : mDataList.size() + mHeaderViews.size();
    }

    public abstract RecyclerView.ViewHolder onCreate(ViewGroup parent, final int viewType);

    public abstract void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, T data);

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T data);
    }
}
