package com.yangxw.example.app.mygank.home.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ycl.mygank.BR;
import com.yangxw.example.app.mygank.Category;
import com.example.ycl.mygank.R;
import com.yangxw.example.app.mygank.base.adapter.BaseRecyclerViewAdapter;
import com.yangxw.example.app.mygank.bean.DataResultInfo;
import com.yangxw.example.app.mygank.util.ImageUtil;

import java.util.List;

/**
 * Created by YCL on 2016/6/12.
 */
public class CategoryAdapter extends BaseRecyclerViewAdapter<CategoryAdapter.VH> {

    private List<DataResultInfo> list;

    public CategoryAdapter(List<DataResultInfo> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        DataResultInfo results = list.get(position);
        if (Category.FU_LI.equalsIgnoreCase(results.getType())){
            type = Type.PHOTO;
        } else if (Category.SHI_PIN.equalsIgnoreCase(results.getType())){
            type = Type.VIDEO;
        } else {
            type = Type.TEXT;
        }
        return type;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        int resource;
        switch (viewType){
            case Type.PHOTO:
                resource = R.layout.home_item_rv_category_photo;
                break;
            case Type.VIDEO:
//                resource = 0;
//                break;
            case Type.TEXT:
            default:
                resource = R.layout.home_item_rv_category;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        DataResultInfo results = list.get(position);

        holder.binding.setVariable(BR.data, results);

        if (Category.FU_LI.equalsIgnoreCase(results.getType())){
            ImageUtil.load(holder.itemView.getContext(), results.getUrl(), holder.iv);
        } else if (Category.SHI_PIN.equalsIgnoreCase(results.getType())){

        } else {

        }

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public DataResultInfo getDataFromPosition(int position){
        DataResultInfo results = null;
        if (position >= 0 && position < getItemCount()){
            results = list.get(position);
        }
        return results;
    }

    /**
     * 刷新
     * @param list
     */
    public void notifyDataRefresh(List<DataResultInfo> list){
        if (list == null){
            return;
        }
        notifyItemRangeRemoved(0, getItemCount());
        this.list = list;
        notifyItemRangeInserted(0, getItemCount());

    }

    /**
     * 加载更多
     * @param list
     */
    public void notifyDataMore(List<DataResultInfo> list){
        if (list == null){
            return;
        }
        int count = getItemCount();
        this.list.addAll(list);

        notifyItemRangeInserted(count, list.size());
    }

    /**
     * ViewHolder
     */
    protected static class VH extends RecyclerView.ViewHolder {
        public ViewDataBinding binding;
        public ImageView iv;

        public VH(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

    /**
     * item 类型
     */
    protected interface Type{
        int TEXT = 0;
        int PHOTO = 1;
        int VIDEO = 2;
    }

}
