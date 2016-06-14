package com.example.ycl.mygank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * 带有OnItemClickListener的RecyclerView.Adapter
 * Created by YCL on 2016/6/14.
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        addOnItemClickListener(holder.itemView, position, onItemClickListener);
    }

    protected void addOnItemClickListener(final View view, final int position, final OnItemClickListener listener){
        if (listener != null && view != null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(view, position);
                }
            });
        }
    }

    /**
     * Item OnClickListener
     */
    public interface OnItemClickListener {
        void onClick(View v, int position);
    }

}
