package com.example.ycl.mygank.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ycl.mygank.R;
import com.example.ycl.mygank.bean.DataInfo;
import com.example.ycl.mygank.databinding.HomeItemRvCategoryBinding;

import java.util.List;

/**
 * Created by YCL on 2016/6/12.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.VH> {

    private List<DataInfo.Results> list;

    public CategoryAdapter(List<DataInfo.Results> list) {
        this.list = list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_rv_category, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        DataInfo.Results results = list.get(position);

        holder.binding.setData(results);

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void notifyDataRefresh(List<DataInfo.Results> list){
        if (list == null){
            return;
        }
        notifyItemRangeRemoved(0, getItemCount());
        this.list = list;
        notifyItemRangeInserted(0, getItemCount());

    }

    public void notifyDataMore(List<DataInfo.Results> list){
        if (list == null){
            return;
        }
        int count = getItemCount();
        this.list.addAll(list);

        notifyItemRangeInserted(count, list.size());
    }

    protected static class VH extends RecyclerView.ViewHolder {

        public HomeItemRvCategoryBinding binding;

        public VH(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
