package id.newtdev.todolist.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import id.newtdev.todolist.R;
import id.newtdev.todolist.controller.TaskController;
import id.newtdev.todolist.model.TaskModel;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<TaskModel> taskList;

    public TaskAdapter(ArrayList<TaskModel> taskList){
        this.taskList = taskList;

    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskModel item = taskList.get(position);
        TaskController controller = new TaskController();
        holder.updateForm.setVisibility(View.GONE);
        holder.nameTask.setText(item.getNamaTask());
        holder.dateTxt.setText(item.getTanggalTask());
        holder.checkBtn.setOnClickListener(v -> {
            controller.deleteTask(item.getIdTask());
        });
        holder.editBtn.setOnClickListener(v -> {
            holder.updateForm.setVisibility(View.VISIBLE);
        });
        holder.ubahBtn.setOnClickListener(v -> {
            String newName = holder.updateInput.getText().toString();
            controller.updateTask(item.getIdTask(),newName);
        });
        holder.closeBtn.setOnClickListener(v -> {
            holder.updateForm.setVisibility(View.GONE);
        });
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTask, dateTxt;
        private ImageButton editBtn, checkBtn;
        private LinearLayout updateForm;
        private Button ubahBtn, closeBtn;
        private TextInputEditText updateInput;

        TaskViewHolder(View v){
            super(v);

            nameTask = v.findViewById(R.id.nameTask);
            dateTxt = v.findViewById(R.id.dateTxt);
            editBtn = v.findViewById(R.id.editBtn);
            checkBtn = v.findViewById(R.id.checkBtn);
            updateForm = v.findViewById(R.id.updateForm);
            ubahBtn = v.findViewById(R.id.ubahBtm);
            closeBtn = v.findViewById(R.id.cancelBtn);
            updateInput = v.findViewById(R.id.updateInput);
        }
    }
}
