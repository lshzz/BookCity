package com.asterism.fresk.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asterism.fresk.R;

import java.util.List;

public class SelectedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder > {
    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    //Type
    private int TYPE_HEADER = 1001;

    public SelectedRecyclerAdapter(Context context, List<String> datas){
        this. mContext=context;
        this. mDatas=datas;
        inflater=LayoutInflater. from(mContext);
    }

    public void setHeaderView(LayoutInflater headerView) {
        inflater = headerView;
    }


    @Override
    public int getItemCount() {

        return mDatas.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        //在第一个位置添加头
        if (position==0){
            return TYPE_HEADER;
        }
        return super.getItemViewType(position);
    }


    //填充onCreateViewHolder方法返回的holder中的控件
//    @Override
//    public void onBindViewHolder(ViewHolder  holder, final int position) {
//
//        holder.tv.setText( mDatas.get(position));
//    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_HEADER){
            View headerView =  inflater.inflate(R.layout.recyclerview_header, parent, false);
            return new HeaderViewHolder(headerView);
        }


        View view = inflater.inflate(R.layout.sl_item_recycler,parent, false);
        ViewHolder  holder= new ViewHolder (view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            //postion==0被头布局占据
            ((ViewHolder) holder).tv.setText(mDatas.get(position-1));
//            ((ViewHolder) holder).tvText.setOnClickListener(view ->
//                    Toast.makeText(mContext, mData.get(position-1), Toast.LENGTH_SHORT).show() );
        }

    }


    /**
     * 正常内容的viewholder
     */
    class ViewHolder  extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder (View view) {
            super(view);
            tv=view.findViewById(R.id.sl_bookname);
        }

    }

    /**
     * 头布局的viewholder
     */
    class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

}
