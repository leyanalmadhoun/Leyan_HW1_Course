package com.example.leyan_hw1_course;
public class CourseWithCount {
    public int id;
    public String courseName;
    public int noOfStudents;
    public int studentCount;
    @Override
    public String toString() {
        return "CourseWithCount{id=" + id +
                ", courseName='" + courseName + '\'' +
                ", noOfStudents=" + noOfStudents +
                ", studentCount=" + studentCount + '}';
    }
}
