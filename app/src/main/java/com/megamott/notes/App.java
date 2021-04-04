package com.megamott.notes;

import android.app.Application;

import androidx.room.Room;

import com.megamott.notes.data.AppDatabase;
import com.megamott.notes.data.NoteDao;

public class App extends Application {

    private AppDatabase database;
    private NoteDao noteDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-app-db")
                .allowMainThreadQueries()
                .build();

        noteDao = database.noteDao();
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }
}
