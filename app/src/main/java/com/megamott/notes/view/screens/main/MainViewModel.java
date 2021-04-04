package com.megamott.notes.view.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.megamott.notes.App;
import com.megamott.notes.model.Note;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final LiveData<List<Note>> listLiveData = App.getInstance().getNoteDao().getAllLiveData();

    public LiveData<List<Note>> getListLiveData() {
        return listLiveData;
    }
}
