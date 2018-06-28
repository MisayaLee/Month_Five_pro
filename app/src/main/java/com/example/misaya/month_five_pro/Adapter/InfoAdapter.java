package com.example.misaya.month_five_pro.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.misaya.month_five_pro.Bean.InfoBean;
import com.example.misaya.month_five_pro.MainActivity;
import com.example.misaya.month_five_pro.R;

import java.util.List;

/**
 * Created by XC on 2018/6/27.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private Context context;
    private List<InfoBean.SongListBean> mDatas;

    public static boolean tag = false;
    public static boolean iselected = false;
    //    public static
    static boolean checked = false;

    public InfoAdapter(Context context, List<InfoBean.SongListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_one, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final InfoBean.SongListBean songListBean = mDatas.get(position);
        Glide.with(context).load(songListBean.getPic_big()).into(holder.iv);
        holder.titleTv.setText(songListBean.getAlbum_title());
        holder.nameTv.setText(songListBean.getAuthor());
        holder.tv3.setText(songListBean.getSi_proxycompany());
        if (tag) {
//            holder.checkBox.setChecked(true);
            holder.checkBox.setVisibility(View.VISIBLE);
        }
        if (iselected) {
            holder.checkBox.setChecked(true);
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        MainActivity.main_check.setChecked(true);
                        MainActivity.main_check.setText("取消全选");
                        isChecked=false;
                    } else {
                        MainActivity.main_check.setChecked(false);
                        MainActivity.main_check.setText("全选");
                    }
                }
            });

        } else {
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView titleTv, nameTv, tv3;
        CheckBox checkBox;


        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.one_iv);
            titleTv = itemView.findViewById(R.id.one_title);
            nameTv = itemView.findViewById(R.id.one_name);
            tv3 = itemView.findViewById(R.id.one_si);
            checkBox = itemView.findViewById(R.id.one_check);
        }
    }

}
