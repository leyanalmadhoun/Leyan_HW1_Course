package com.example.leyan_hw1_course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;
public class CourseSpinnerAdapter extends ArrayAdapter<Course> {
    public CourseSpinnerAdapter(@NonNull Context ctx, @NonNull List<Course> data) {
        super(ctx, android.R.layout.simple_spinner_dropdown_item, data);
    }

    @NonNull @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return make(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return make(position, convertView, parent);
    }

    private View make(int pos, View view, ViewGroup parent) {
        TextView tv = (TextView) (view != null ? view :
                LayoutInflater.from(getContext()).inflate(
                        android.R.layout.simple_spinner_dropdown_item, parent, false));
        Course c = getItem(pos);
        tv.setText(c == null ? "" : (c.getId() + " - " + c.getCourseName()));
        return tv;
    }
}
