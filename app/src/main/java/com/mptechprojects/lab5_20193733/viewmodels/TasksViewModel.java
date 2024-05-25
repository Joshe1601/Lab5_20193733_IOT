package com.mptechprojects.lab5_20193733.viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mptechprojects.lab5_20193733.entity.Tasks;
import com.mptechprojects.lab5_20193733.repository.TasksRepository;

import java.util.ArrayList;
import java.util.List;

public class TasksViewModel extends ViewModel {
    private final MutableLiveData<String> userCode = new MutableLiveData<>();
    private final TasksRepository tasksRepository;
    private final LiveData<List<Tasks>> tasksList;

    public TasksViewModel(Application application) {
        tasksRepository = new TasksRepository(application);
        tasksList = tasksRepository.getTasksList();
    }

    public LiveData<String> getUserCode() {
        return userCode;
    }

    public void setUserCode(String code) {
        userCode.setValue(code);
        fetchTasksForUser(code);
    }

    public LiveData<List<Tasks>> getTasks() {
        return tasksList;
    }

    public void fetchTasksForUser(String idUser) {
        tasksRepository.getTasksByUser(idUser);
    }
}



