package com.mptechprojects.lab5_20193733.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mptechprojects.lab5_20193733.dao.TasksDao;
import com.mptechprojects.lab5_20193733.database.TasksDatabase;
import com.mptechprojects.lab5_20193733.entity.Tasks;

import java.util.List;

public class TasksRepository {

    private final TasksDao tasksDao;
    private final MutableLiveData<List<Tasks>> tasksList = new MutableLiveData<>();

    public TasksRepository(Application application) {
        TasksDatabase db = TasksDatabase.getDatabase(application);
        tasksDao = db.tasksDao();
    }

    public LiveData<List<Tasks>> getTasksList() {
        return tasksList;
    }

    public void insertTask(Tasks[] arrayTasks) {
        new SaveThread().execute(arrayTasks);
    }

    private class SaveThread extends AsyncTask<Tasks, Void, Void> {
        @Override
        protected Void doInBackground(Tasks... tasks) {
            for (Tasks task : tasks) {
                tasksDao.insertTask(task);
            }
            return null;
        }
    }

    public void updateTask(Tasks task) {
        new UpdateThread().execute(task);
    }

    private class UpdateThread extends AsyncTask<Tasks, Void, Void> {
        @Override
        protected Void doInBackground(Tasks... tasks) {
            tasksDao.updateTask(tasks[0]);
            return null;
        }
    }

    public void getAllTasks() {
        new GetTasksThread().execute();
    }

    private class GetTasksThread extends AsyncTask<Void, Void, List<Tasks>> {
        @Override
        protected List<Tasks> doInBackground(Void... voids) {
            return tasksDao.getAllTasks();
        }

        @Override
        protected void onPostExecute(List<Tasks> tasks) {
            tasksList.setValue(tasks);
        }
    }

    public void getTasksByUser(String idUser) {
        new GetTasksByUserThread().execute(Integer.valueOf(idUser));
    }

    private class GetTasksByUserThread extends AsyncTask<Integer, Void, List<Tasks>> {
        @Override
        protected List<Tasks> doInBackground(Integer... idUser) {
            return tasksDao.getTasksByUser(idUser[0]);
        }

        @Override
        protected void onPostExecute(List<Tasks> tasks) {
            tasksList.setValue(tasks);
        }
    }
}

