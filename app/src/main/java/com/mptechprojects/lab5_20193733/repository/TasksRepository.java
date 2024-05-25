package com.mptechprojects.lab5_20193733.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mptechprojects.lab5_20193733.dao.TasksDao;
import com.mptechprojects.lab5_20193733.database.TasksDatabase;
import com.mptechprojects.lab5_20193733.entity.Tasks;

import java.util.List;

public class TasksRepository extends ViewModel {

    private TasksDao tasksDao;

    private MutableLiveData<List<Tasks>> tasksList = new MutableLiveData<>();

    public MutableLiveData<List<Tasks>> getTasksList() {
        return tasksList;
    }

    public void setTasksList(MutableLiveData<List<Tasks>> tasksList) {
        this.tasksList = tasksList;
    }

    public TasksRepository(Application application) {
        TasksDatabase db = TasksDatabase.getDatabase(application);
        tasksDao = db.tasksDao();
    }

    public void insertTask(Tasks[] arrayTasks) {
        SaveThread saveThread = new SaveThread();
        saveThread.execute(arrayTasks);

    }

    private class SaveThread extends AsyncTask<Tasks, Void, Void> {
        @Override
        protected Void doInBackground(Tasks... tasks) {
            for (int i = 0; i < tasks.length; i++) {
                tasksDao.insertTask(tasks[i]);
            }

            return null;
        }
    }

}
