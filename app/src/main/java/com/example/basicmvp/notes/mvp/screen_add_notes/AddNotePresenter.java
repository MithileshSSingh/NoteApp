package com.example.basicmvp.notes.mvp.screen_add_notes;

import com.example.basicmvp.notes.data.DataSource;
import com.example.basicmvp.notes.data.Repository;
import com.example.basicmvp.notes.mvp.model.Notes;

/**
 * Created by mithilesh on 8/28/16.
 */
public class AddNotePresenter implements AddNoteContract.Presenter {

    private Repository mRepository;
    private AddNoteContract.View mView;

    public AddNotePresenter(Repository repository, AddNoteContract.View view) {
        this.mRepository = repository;
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void addNote(Notes note, final AddNoteContract.View.AddNotesCallBack callBack) {
        mRepository.addNote(note, new DataSource.CommonOperationCallBack() {
            @Override
            public void success(String message) {
                callBack.success(message);
            }

            @Override
            public void failed(String errMessage) {
                callBack.failed(errMessage);
            }
        });
    }
}
