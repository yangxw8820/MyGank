package com.example.ycl.mygank.home.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ycl.mygank.BR;
import com.example.ycl.mygank.Category;
import com.example.ycl.mygank.R;
import com.example.ycl.mygank.bean.DataInfo;

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
    public int getItemViewType(int position) {
        int type;
        DataInfo.Results results = list.get(position);
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
        DataInfo.Results results = list.get(position);

        holder.binding.setVariable(BR.data, results);

        if (Category.FU_LI.equalsIgnoreCase(results.getType())){
            Glide.with(holder.itemView.getContext().getApplicationContext())
                    .load(results.getUrl())
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_error)
//                    .override(0, 0)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontTransform()
                    .crossFade()
                    .into(holder.iv);
        } else if (Category.SHI_PIN.equalsIgnoreCase(results.getType())){

        } else {

        }


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
        public ViewDataBinding binding;
        public ImageView iv;

        public VH(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

    protected interface Type{
        int TEXT = 0;
        int PHOTO = 1;
        int VIDEO = 2;
    }

}
