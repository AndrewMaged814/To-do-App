package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Task_RecyclerViewAdapter extends RecyclerView.Adapter<Task_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    public static ArrayList<TaskModel> taskModelArrayList;
    int row_index=-1;
    public Task_RecyclerViewAdapter(Context context, ArrayList<TaskModel> taskModelArrayList,RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        Task_RecyclerViewAdapter.taskModelArrayList = taskModelArrayList;
        this.recyclerViewInterface = recyclerViewInterface;

    }
    @NonNull
    @Override
    public Task_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this is where I inflate the layout (giving a look to our rows)

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);

        return new Task_RecyclerViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Task_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views created in the recycler_view_raw layout file
        //based on the position of the recycler view

        TaskModel taskModel = taskModelArrayList.get(position);

        holder.tvName.setText(taskModel.getTaskName());
        holder.imageView.setImageResource(taskModel.getImage());
        boolean isChecked = taskModelArrayList.get(position).isTaskDone();
        holder.cbDone.setChecked(isChecked);


    }

    @Override
    public int getItemCount() {
        //the recycler view just want to know the number of items you want displayed
        return taskModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //grabbing the views from our recycler_view_row layout file
        //kinda like in the onCreate method
        ImageView imageView;
        TextView tvName;
        CheckBox cbDone;

        private final static String TAG = "TASK APP";

        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.tvTaskName);
            cbDone = itemView.findViewById(R.id.cbDone);
            cbDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    String taskName = tvName.getText().toString();
                    TaskModel task = taskModelArrayList.get(getAdapterPosition());
                    Log.d(TAG,"onCreate: " + taskModelArrayList.toString());
                    if(task!=null){
                        if (cbDone.isChecked()) {
                            tvName.setText(Html.fromHtml("<strike>" + taskName + "</strike>"));
                            task.setTaskDone(true);
                        }
                        else{
                            tvName.setText(taskName);
                            task.setTaskDone(false);
                        }
                        Log.d(TAG,"onCreate: " + taskModelArrayList.toString());

                    }

                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.OnItemClick(position);
                        }

                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemLongClick(position);
                        }

                    }
                    return true;
                }
            });

        }

    }
}
