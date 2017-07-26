package com.heima.yqz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.heima.yqz.tabmenuview.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mryang on 2016/12/2.
 */

public class MenuAdapter24 extends RecyclerView.Adapter {
    private Context context;
    private List<String> menus;

    public void setNewsCenterMenuBeanList(List<String> menus) {
        this.menus = menus;
//        //刷新显示
//        notifyDataSetChanged();
    }

    //通过构造传递上下文和集合
    public MenuAdapter24(Context context, List<String> menus) {
        this.context = context;
        this.menus = menus;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvMenuTitle.setText(menus.get(position));
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_arrow)
        ImageView ivArrow;
        @BindView(R.id.tv_menu_title)
        TextView tvMenuTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
