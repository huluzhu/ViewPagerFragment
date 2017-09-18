package com.baway.hujiqiang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.hujiqiang.R;
import com.baway.hujiqiang.bean.Data;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * author:
 * data: 2017/8/25.
 * desc:recyclerview适配器
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Data.ZwlBean> list;
    private Context context;

    //构造方法
    public RecyclerViewAdapter(List<Data.ZwlBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //绘制视图
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item, parent,
                false));
        return holder;
    }

    //绑定视图的方法
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data.ZwlBean bean = list.get(position);
        holder.title.setText(bean.Moment.content);
        holder.time.setText(bean.Moment.date);
        holder.good.setText(bean.Commentzwl.get(0).momentId + "点赞");
        holder.say.setText(bean.Commentzwl.get(0).id + "评论");
        Glide.with(context).load(bean.Moment.pictureList.get(0)).into(holder.itemImage);
        Glide.with(context).load(bean.User.head).into(holder.contentImage1);
        Glide.with(context).load(bean.User.head).into(holder.contentImage2);
        Glide.with(context).load(bean.User.head).into(holder.contentImage3);
    }

    //recyclerview的条目数
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, good, say, time;
        private ImageView itemImage, contentImage1, contentImage2, contentImage3;

        //定义viewholder
        public MyViewHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.item_image);
            contentImage1 = (ImageView) view.findViewById(R.id.item_content_image1);
            contentImage2 = (ImageView) view.findViewById(R.id.item_content_image2);
            contentImage3 = (ImageView) view.findViewById(R.id.item_content_image3);
            title = (TextView) view.findViewById(R.id.item_title);
            time = (TextView) view.findViewById(R.id.item_time);
            good = (TextView) view.findViewById(R.id.item_good);
            say = (TextView) view.findViewById(R.id.item_say);
        }
    }
}
