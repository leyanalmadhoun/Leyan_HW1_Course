package com.example.leyan_hw1_course;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private Button btnAddStudent, btnAddCourse;
    private RecyclerView rvCourses, rvStudents;
    private CourseAdapter courseAdapter;
    private StudentAdapter studentAdapter;
    private MyViewModel myViewModel;
    private List<Course> coursesCache = new ArrayList<>();
    @Nullable private Bitmap lastCapturedBitmap;
    @Nullable private ImageView currentPreviewIv;

    private final ActivityResultLauncher<Intent> cameraActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                                lastCapturedBitmap = (Bitmap) result.getData().getExtras().get("data");
                                if (currentPreviewIv != null && lastCapturedBitmap != null) {
                                    currentPreviewIv.setImageBitmap(lastCapturedBitmap);
                                }
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        btnAddStudent = findViewById(R.id.btn_add_student);
        btnAddCourse  = findViewById(R.id.btn_add_course);

        rvCourses  = findViewById(R.id.rv_courses);
        rvStudents = findViewById(R.id.rv_students);

        courseAdapter = new CourseAdapter();
        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        rvCourses.setAdapter(courseAdapter);

        studentAdapter = new StudentAdapter();
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        rvStudents.setAdapter(studentAdapter);

        myViewModel.getAllCourses().observe(this, courses -> {
            coursesCache = (courses != null) ? courses : new ArrayList<>();
            courseAdapter.setCourses(coursesCache);
        });

        myViewModel.getAllStudents().observe(this, students -> {
            studentAdapter.setStudents(students);
        });

        btnAddCourse.setOnClickListener(v -> showAddCourseDialog());
        btnAddStudent.setOnClickListener(v -> showAddStudentDialog());
    }

    private void showAddCourseDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_course, null, false);
        EditText etName  = view.findViewById(R.id.etCourseName);
        EditText etCount = view.findViewById(R.id.etCourseCount);

        new AlertDialog.Builder(this)
                .setTitle("Add Course")
                .setView(view)
                .setPositiveButton("Add", (d, w) -> {
                    String name = etName.getText().toString().trim();
                    String countStr = etCount.getText().toString().trim();
                    int noOfStudents = 0;
                    if (!countStr.isEmpty()) {
                        try { noOfStudents = Integer.parseInt(countStr); } catch (Exception ignored) {}
                    }
                    if (!name.isEmpty()) {
                        myViewModel.insertCourse(new Course(name, noOfStudents));
                    } else {
                        Toast.makeText(this, "Enter course name", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showAddStudentDialog() {
        if (coursesCache == null || coursesCache.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setMessage("Add a course first.")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        lastCapturedBitmap = null;
        currentPreviewIv   = null;

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null, false);
        EditText etName  = view.findViewById(R.id.etStudentName);
        EditText etDept  = view.findViewById(R.id.etStudentDept);
        Spinner spCourse = view.findViewById(R.id.spCourse);
        Button btnPick   = view.findViewById(R.id.btnPickDate);
        Button btnTake   = view.findViewById(R.id.btnTakePhoto);
        ImageView ivPrev = view.findViewById(R.id.ivPreview);

        currentPreviewIv = ivPrev;

        CourseSpinnerAdapter spinnerAdapter = new CourseSpinnerAdapter(this, coursesCache);
        spCourse.setAdapter(spinnerAdapter);

        final Date[] selectedDob = {null};
        btnPick.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            DatePickerDialog dp = new DatePickerDialog(
                    this,
                    (picker, y, m, d) -> {
                        Calendar cal = Calendar.getInstance();
                        cal.set(y, m, d, 0, 0, 0);
                        selectedDob[0] = cal.getTime();
                        btnPick.setText(String.format(Locale.getDefault(), "%04d-%02d-%02d", y, m + 1, d));
                    },
                    c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)
            );
            dp.show();
        });

        btnTake.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraActivityResultLauncher.launch(intent);
        });

        new AlertDialog.Builder(this)
                .setTitle("Add Student")
                .setView(view)
                .setPositiveButton("Add", (d, w) -> {
                    String name = etName.getText().toString().trim();
                    String dept = etDept.getText().toString().trim();
                    if (name.isEmpty() || dept.isEmpty() || selectedDob[0] == null) {
                        Toast.makeText(this, "Fill student data & date", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int pos = spCourse.getSelectedItemPosition();
                    int courseId = coursesCache.get(pos).getId();

                    myViewModel.insertStudent(
                            new Student(name, dept,
                                    lastCapturedBitmap,
                                    selectedDob[0],
                                    courseId)
                    );
                })
                .setNegativeButton("Cancel", (d, w) -> {  })
                .setOnDismissListener(dialog -> currentPreviewIv = null)
                .show();
    }
}
