package com.example.leyan_hw1_course;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MyRepository {

    private final StudentDao studentDao;
    private final CourseDao  courseDao;

     MyRepository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        studentDao = db.studentDao();
        courseDao  = db.courseDao();
    }
     void insertCourse(Course course) {
        MyDatabase.databaseWriteExecutor.execute(() ->
                courseDao.insertCourse(course)
        );
    }
    public void updateCourse(Course course) {
        MyDatabase.databaseWriteExecutor.execute(() ->
                courseDao.updateCourse(course)
        );
    }
    public void deleteCourse(Course course) {
        MyDatabase.databaseWriteExecutor.execute(() ->
                courseDao.deleteCourse(course)
        );
    }
     LiveData<List<Course>> getAllCourses() {
        return courseDao.getAllCourses();
    }
     LiveData<Course> getAllCoursesById(int courseId) {
        return courseDao.getAllCoursesById(courseId);
    }
     void insertStudent(Student student) {
        MyDatabase.databaseWriteExecutor.execute(() ->
                studentDao.insertStudent(student)
        );
    }
     void updateStudent(Student student) {
        MyDatabase.databaseWriteExecutor.execute(() ->
                studentDao.updateStudent(student)
        );
    }
     void deleteStudent(Student student) {
        MyDatabase.databaseWriteExecutor.execute(() ->
                studentDao.deleteStudent(student)
        );
    }
     LiveData<List<Student>> getAllStudents() {
        return studentDao.getAllStudents();
    }
     LiveData<List<Student>> getAllStudentByCourseId(int courseId) {
        return studentDao.getAllStudentsByCourseId(courseId);
    }



    LiveData<List<CourseWithCount>> getCoursesWithCounts() {
        return courseDao.getCoursesWithCounts();
    }

    LiveData<List<StudentWithCourse>> getStudentsWithCourse() {
        return studentDao.getStudentsWithCourse();
    }

}
