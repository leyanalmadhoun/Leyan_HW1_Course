package com.example.leyan_hw1_course;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface CourseDao {
    @Insert
    void insertCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    @Query("SELECT * FROM Course")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM Course WHERE id = :courseId")
    LiveData<Course> getAllCoursesById(int courseId);

    @Query("SELECT c.id, c.courseName, c.noOfStudents, COUNT(s.id) AS studentCount " +
            "FROM Course c LEFT JOIN Student s ON s.courseId = c.id " +
            "GROUP BY c.id ORDER BY c.id")
    LiveData<List<CourseWithCount>> getCoursesWithCounts();
}


