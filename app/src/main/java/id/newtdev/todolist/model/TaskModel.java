package id.newtdev.todolist.model;

public class TaskModel {
    private String idTask;
    private String namaTask;
    private String tanggalTask;

    public TaskModel(String namaTask, String tanggalTask){
        this.namaTask = namaTask;
        this.tanggalTask = tanggalTask;
    }

    public TaskModel(){

    }

    public String getNamaTask(){
        return  namaTask;
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public void setNamaTask(String namaTask) {
        this.namaTask = namaTask;
    }

    public String getTanggalTask() {
        return tanggalTask;
    }

    public void setTanggalTask(String tanggalTask) {
        this.tanggalTask = tanggalTask;
    }
}
