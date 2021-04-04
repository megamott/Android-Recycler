package com.megamott.notes.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.megamott.notes.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

}
