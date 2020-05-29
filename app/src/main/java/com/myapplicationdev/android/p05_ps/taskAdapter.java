package com.myapplicationdev.android.p05_ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class taskAdapter extends ArrayAdapter<Task> {
    Context context;
    ArrayList<Task> tasks;
    int resource;
    TextView tvID, tvTask, tvDetail;

    public taskAdapter(Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);
        tvID = rowView.findViewById(R.id.tvID);
        tvTask = rowView.findViewById(R.id.tvTask);
        tvDetail = rowView.findViewById(R.id.tvDetail);

        Task task = tasks.get(position);
        tvID.setText(Integer.toString(task.get_id()));
        tvTask.setText(task.getTask());
        tvDetail.setText(task.getTaskContent());

        return rowView;
    }
}
