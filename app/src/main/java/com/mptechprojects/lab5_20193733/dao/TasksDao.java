package com.mptechprojects.lab5_20193733.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mptechprojects.lab5_20193733.entity.Tasks;

import java.util.List;

@Dao
public interface TasksDao {

    @Insert
    void insertTask(Tasks task);

    @Update
    void updateTask(Tasks task);

    @Query("SELECT * FROM tasks")
    public List<Tasks> getAllTasks();

    @Query("SELECT * FROM tasks WHERE idCode = :idUser")
    public List<Tasks> getTasksByUser(Integer idUser);
}
