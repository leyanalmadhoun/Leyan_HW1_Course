package com.example.leyan_hw1_course;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
public class MyViewModel extends AndroidViewModel {
    MyRepository repository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new MyRepository(application);
    }

    void insertCourse(Course course) {
        repository.insertCourse(course);
    }
    public void updateCourse(Course course) {
        repository.updateCourse(course);
    }
    public void deleteCourse(Course course) {
        repository.deleteCourse(course);
    }
    LiveData<List<Course>> getAllCourses() {
        return repository.getAllCourses();
    }
    LiveData<Course> getAllCoursesById(int courseId) {
        return repository.getAllCoursesById(courseId);
    }
    void insertStudent(Student student) {
        repository.insertStudent(student);
    }
    void updateStudent(Student student) {
        repository.updateStudent(student);
    }
    void deleteStudent(Student student) {
        repository.deleteStudent(student);
    }
    LiveData<List<Student>> getAllStudents() {
        return repository.getAllStudents();
    }
    LiveData<List<Student>> getAllStudentByCourseId(int courseId) {
        return repository.getAllStudentByCourseId(courseId);
    }

    LiveData<List<CourseWithCount>> getCoursesWithCounts() {
        return repository.getCoursesWithCounts();
    }

    LiveData<List<StudentWithCourse>> getStudentsWithCourse() {
        return repository.getStudentsWithCourse();
    }

}
