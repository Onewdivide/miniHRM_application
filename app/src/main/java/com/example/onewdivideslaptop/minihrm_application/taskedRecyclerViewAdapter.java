package com.example.onewdivideslaptop.minihrm_application;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        final int tempi = i;

        myViewHolder.project_name.setText("ProjectName : "+mData.get(i).getProject_name());
        myViewHolder.task_des.setText("Description : "+mData.get(i).getTask_des());
        myViewHolder.time.setText("Time : " + mData.get(i).getTime());
        myViewHolder.taskId.setText("TaskID : "+mData.get(i).getTaskId());

        myViewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),editOrDeleteTaskActivity.class);
                intent.putExtra("projectName",mData.get(tempi).getProject_name());
                Log.e("projectName",mData.get(tempi).getProject_name());
                intent.putExtra("taskDescription",mData.get(tempi).getTask_des());
                Log.e("taskDescription",mData.get(tempi).getTask_des());
                intent.putExtra("time",mData.get(tempi).getTime());
                Log.e("time",mData.get(tempi).getTime());
                intent.putExtra("taskId",mData.get(tempi).getTaskId());
                Log.e("taskId",mData.get(tempi).getTaskId());
                intent.putExtra("name",mData.get(tempi).getName());
                Log.e("name",mData.get(tempi).getName());
                intent.putExtra("date",mData.get(tempi).getDate());
                Log.e("date",mData.get(tempi).getDate());
                intent.putExtra("description",mData.get(tempi).getDescription());
                Log.e("description",mData.get(tempi).getDescription());
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private View view;

        private TextView project_name,task_des,time,taskId;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            view = itemView;
            project_name = (TextView) itemView.findViewById(R.id.project_name_id);
            task_des = (TextView) itemView.findViewById(R.id.task_description_id);
            time = (TextView) itemView.findViewById(R.id.time);
            taskId = (TextView) itemView.findViewById(R.id.taskId);

        }

        public View getView(){
            return view;
        }

    }

}
