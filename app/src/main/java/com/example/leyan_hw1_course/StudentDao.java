package com.example.leyan_hw1_course;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao
public interface StudentDao {

    @Insert
    void insertStudent(Student student);

    @Update
    void updateStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Query("SELECT * FROM Student")
    LiveData<List<Student>> getAllStudents();

    @Query("SELECT * FROM Student WHERE courseId = :courseId")
    LiveData<List<Student>> getAllStudentsByCourseId(int courseId);

    @Query("SELECT s.id, s.name, s.department, s.photo, s.dateOfBirth, s.courseId, c.courseName " +
            "FROM Student s INNER JOIN Course c ON c.id = s.courseId " +
            "ORDER BY s.id")
    LiveData<List<StudentWithCourse>> getStudentsWithCourse();
}
