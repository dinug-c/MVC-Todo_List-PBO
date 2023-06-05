package id.newtdev.todolist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import id.newtdev.todolist.R;
import id.newtdev.todolist.controller.TaskController;
import id.newtdev.todolist.model.TaskModel;

public class MainActivity extends AppCompatActivity {

    private Button urutkanBtn, kirimBtn;
    private TextInputLayout taskInput;
    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;
    private ArrayList<TaskModel> taskList;
    private TaskController taskController;
    private String newTask;
    private String tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Object/ID Binding
        urutkanBtn = findViewById(R.id.urutkanBtn);
        kirimBtn = findViewById(R.id.btnKirim);
        taskInput = findViewById(R.id.todoInput);
        taskRecyclerView = findViewById(R.id.taskRecy);

        // Inisialisasi
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskList = new ArrayList<TaskModel>();
        taskAdapter = new TaskAdapter(taskList);
        taskRecyclerView.setAdapter(taskAdapter);

        taskController = new TaskController();
        taskController.getTaskItems(taskList,taskAdapter);

        // Add Task
        newTask = String.valueOf(taskInput.getEditText().getText());
        tanggal = taskController.getDate();
        kirimBtn.setOnClickListener(v -> taskController.addTask(newTask,tanggal));

        // Urutkan Task
        urutkanBtn.setOnClickListener(v -> {
            taskController.sortData(taskList, taskAdapter);
        });

    }
}