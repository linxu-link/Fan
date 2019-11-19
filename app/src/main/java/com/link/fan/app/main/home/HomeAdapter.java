package com.link.fan.app.main.home;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.link.fan.R;
import com.link.fan.data.bean.MenuDetail;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<MenuDetail, HomeAdapter.HomeViewHolder> {


    public HomeAdapter(int layoutResId, @Nullable List<MenuDetail> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomeViewHolder helper, MenuDetail item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.menu, item);
        binding.setVariable(BR.clickListener, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
        binding.executePendingBindings();
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }


    public static class HomeViewHolder extends BaseViewHolder {

        public HomeViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }

}
