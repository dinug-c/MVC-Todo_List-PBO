package id.newtdev.todolist.controller;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import id.newtdev.todolist.interfaces.TaskInterface;
import id.newtdev.todolist.model.TaskModel;
import id.newtdev.todolist.view.TaskAdapter;

public class TaskController implements TaskInterface {
    private DatabaseReference todoRef;

    public TaskController(){
        todoRef = FirebaseDatabase.getInstance().getReference("todos");
    }

    public void addTask(String nama, String tanggal){
        String id = todoRef.push().getKey();
        TaskModel item = new TaskModel(nama, tanggal);
        item.setIdTask(id);
        todoRef.child(id).setValue(item);
    }

    public void updateTask(TaskModel task){
        todoRef.child(task.getIdTask()).setValue(task);
    }

    public void deleteTask(String id){
        todoRef.child(id).removeValue();
    }

    public String getDate(){
        String tanggalString = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate tanggal = LocalDate.now();
            DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            tanggalString = tanggal.format(formatTanggal);
        }
        return tanggalString;
    }

    public void sortData(ArrayList<TaskModel> taskList, TaskAdapter taskAdapter){
        Collections.sort(taskList, new Comparator<TaskModel>() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            @Override
            public int compare(TaskModel o1, TaskModel o2) {
                try {
                    Date d1 = sdf.parse(o1.getTanggalTask());
                    Date d2 = sdf.parse(o2.getTanggalTask());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        taskAdapter.notifyDataSetChanged();
    }

    public ValueEventListener getTaskItems(ArrayList<TaskModel> taskItems, TaskAdapter taskAdapter){
        return todoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taskItems.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    TaskModel item = itemSnapshot.getValue(TaskModel.class);
                    taskItems.add(item);
                }
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });
    }
}
