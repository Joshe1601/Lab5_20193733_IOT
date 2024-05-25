package com.mptechprojects.lab5_20193733.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mptechprojects.lab5_20193733.dao.TasksDao;
import com.mptechprojects.lab5_20193733.entity.Tasks;

@Database(entities = {Tasks.class}, version = 1)
public abstract class TasksDatabase extends RoomDatabase {

    public abstract TasksDao tasksDao();

    private static TasksDatabase INSTANCE;

    public static TasksDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TasksDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TasksDatabase.class, "tasks_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
