package com.megamott.android_recycler.data;

import androidx.room.Database;

import com.megamott.android_recycler.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase {

    public abstract NoteDao noteDao();

}
