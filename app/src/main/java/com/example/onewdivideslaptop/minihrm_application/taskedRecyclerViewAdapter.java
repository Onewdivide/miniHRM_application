package com.example.onewdivideslaptop.minihrm_application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onewdivideslaptop.minihrm_application.responseAndBody.task_list_response;

import java.util.List;

public class taskedRecyclerViewAdapter extends RecyclerView.Adapter<taskedRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<task_list_response> mData;

    public taskedRecyclerViewAdapter(Context mContext, List<task_list_response> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.task_list,viewGroup,false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.project_name.setText(mData.get(i).getProject_name());
        myViewHolder.task_des.setText(mData.get(i).getTask_des());
        myViewHolder.time.setText(mData.get(i).getTime());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView project_name,task_des,time;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            project_name = (TextView) itemView.findViewById(R.id.project_name_id);
            task_des = (TextView) itemView.findViewById(R.id.task_description_id);
            time = (TextView) itemView.findViewById(R.id.time);

        }
    }

}
