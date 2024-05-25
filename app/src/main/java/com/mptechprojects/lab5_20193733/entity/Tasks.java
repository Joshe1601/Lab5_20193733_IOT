package com.mptechprojects.lab5_20193733.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tasks")
public class Tasks implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idTask;

    @NonNull
    private String idCode;
    private String taskName;
    private String taskDescription;
    private String taskDate;
    private Boolean taskNotification;
    private String taskPriority;

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    @NonNull
    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(@NonNull String idCode) {
        this.idCode = idCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public Boolean getTaskNotification() {
        return taskNotification;
    }

    public void setTaskNotification(Boolean taskNotification) {
        this.taskNotification = taskNotification;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }
}
