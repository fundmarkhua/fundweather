package com.example.congbai.fundweather.task.adapater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.congbai.fundweather.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fundmarkhua on 2017/3/6
 * Email:57525101@qq.com
 * recycleView 适配器   省市区县数据适配
 */

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.myViewHolder> {
    private List<String> mAreaList;

    static class myViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_area_name)
        TextView tv_area_name;

        public myViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //自定义点击事件接口
    public interface OnItemClickListener {
        void onItemClick(View view, int positon);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public AreaAdapter(List<String> mAreaList) {
        this.mAreaList = mAreaList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.area_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {
        String areaName = mAreaList.get(position);
        holder.tv_area_name.setText(areaName);

        //设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mAreaList.size();
    }
}
