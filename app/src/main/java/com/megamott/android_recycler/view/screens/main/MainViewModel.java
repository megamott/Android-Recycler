package com.megamott.android_recycler.view.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.megamott.android_recycler.App;
import com.megamott.android_recycler.model.Note;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final LiveData<List<Note>> listLiveData = App.getInstance().getNoteDao().getAllLiveData();

    public LiveData<List<Note>> getListLiveData() {
        return listLiveData;
    }
}
