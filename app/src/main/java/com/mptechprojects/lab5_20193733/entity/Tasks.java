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
    private Double idCode;
    private String taskName;
    private String taskDescription;
    private String taskDate;
    private Boolean taskNotification;
    private String taskPriority;


}
