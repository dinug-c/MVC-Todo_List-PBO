package id.newtdev.todolist.interfaces;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import id.newtdev.todolist.model.TaskModel;
import id.newtdev.todolist.view.TaskAdapter;

public interface TaskInterface {
    void addTask(String nama, String tanggal);
    void updateTask(String taskId, String taskName);
    void deleteTask(String id);
    void sortData(ArrayList<TaskModel> taskList, TaskAdapter taskAdapter);
    String getDate();
    ValueEventListener getTaskItems(ArrayList<TaskModel> taskItems, TaskAdapter taskAdapter);
}
