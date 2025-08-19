package com.example.leyan_hw1_course;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseVH> {
    private List<Course> items = new ArrayList<>();

    public void setCourses(List<Course> courses) {
        this.items = (courses == null) ? new ArrayList<>() : courses;
        notifyDataSetChanged();
    }

    @NonNull @Override
    public CourseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseVH(v);
    }



    @Override
    public void onBindViewHolder(@NonNull CourseVH holder, int position) {
        Course c = items.get(position);
        holder.tvId.setText(String.valueOf(c.getId()));
        holder.tvName.setText(c.getCourseName());
        holder.tvCount.setText(String.valueOf(c.getNoOfStudents()));
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class CourseVH extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvCount;
        CourseVH(@NonNull View itemView) {
            super(itemView);
            tvId   = itemView.findViewById(R.id.tv_course_id);
            tvName = itemView.findViewById(R.id.tv_course_name);
            tvCount= itemView.findViewById(R.id.tv_course_count);
        }
    }
}
