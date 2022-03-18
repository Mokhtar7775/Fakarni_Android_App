package com.example.fakarni;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskati.R;

import java.util.ArrayList;


public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.ViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList name,descriptions,date;

    public MyAdaptor(Activity activity,Context context,ArrayList name,ArrayList descriptions, ArrayList date) {
        this.activity = activity;
        this.name = name;
        this.descriptions = descriptions;
        this.date = date;
        this.context=context;
    }

    @NonNull
    @Override
    public MyAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_row,parent,false);
        //View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdaptor.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //Listitem item=listitems.get(position);
        holder.Taskname.setText(String.valueOf(name.get(position)));
        holder.descreption.setText(String.valueOf(descriptions.get(position)));
        holder.Time.setText(String.valueOf(date.get(position)));
        //RecyclerView onClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addTask.TaskDetails(holder.Taskname.getText().toString(),holder.Date.getText().toString(),holder.Time.getText().toString(),,holder.descreption.getText().toString());
                Intent intent = new Intent(context, AddTask.class);
                intent.putExtra("TastName",String.valueOf(name.get(position)));
                intent.putExtra("descreption",String.valueOf(descriptions.get(position)));
                intent.putExtra("Date",String.valueOf(date.get(position)));
                //activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Taskname;
        private TextView descreption;
        private TextView Time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Taskname=itemView.findViewById(R.id.Title);
            descreption=itemView.findViewById(R.id.Myinfo);
            Time =itemView.findViewById(R.id.More);
        }

        /*@Override
        public void onClick(View v) {
           int postion=getAdapterPosition();
           Listitem item=listitems.get(postion);
            Intent intent=new Intent(context, AddTaskk.class);
            intent.putExtra("Taskname",item.getName());
            intent.putExtra("descreption",item.getDescreption());
            intent.putExtra("Date",item.getDate());
            context.startActivity(intent);


            //Toast.makeText(context,item.getName(),Toast.LENGTH_SHORT).show();
        }*/
    }
}
