package com.example.leyan_hw1_course;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentVH> {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private List<Student> items = new ArrayList<>();

    public void setStudents(List<Student> students) {
        this.items = (students == null) ? new ArrayList<>() : students;
        notifyDataSetChanged();
    }

    @NonNull @Override
    public StudentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentVH holder, int position) {
        Student s = items.get(position);
        holder.tvName.setText(s.getName());
        holder.tvDept.setText(s.getDepartment());
        holder.tvCourseId.setText(String.valueOf(s.getCourseId()));
        holder.tvDob.setText(s.getDateOfBirth() != null ? df.format(s.getDateOfBirth()) : "");
        if (s.getPhoto() != null) {
            holder.ivPhoto.setImageBitmap(s.getPhoto());
        } else {
            holder.ivPhoto.setImageDrawable(null);
        }
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class StudentVH extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName, tvDept, tvCourseId, tvDob;
        StudentVH(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_student_photo);
            tvName = itemView.findViewById(R.id.tv_student_name);
            tvDept  = itemView.findViewById(R.id.tv_student_dept);
            tvCourseId = itemView.findViewById(R.id.tv_student_course_id);
            tvDob = itemView.findViewById(R.id.tv_student_dob);
        }
    }
}
