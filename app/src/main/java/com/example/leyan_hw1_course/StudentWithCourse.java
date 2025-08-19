package com.example.leyan_hw1_course;

import android.graphics.Bitmap;
import androidx.room.TypeConverters;
import java.util.Date;

@TypeConverters(Converter.class)
public class StudentWithCourse {
    public int id;
    public String name;
    public String department;
    public Bitmap photo;
    public Date dateOfBirth;
    public int courseId;
    public String courseName;

    @Override
    public String toString() {
        return "StudentWithCourse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", photo=" + photo +
                ", dateOfBirth=" + dateOfBirth +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
