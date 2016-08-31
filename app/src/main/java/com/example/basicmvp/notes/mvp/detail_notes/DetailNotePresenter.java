package com.example.basicmvp.notes.mvp.detail_notes;

import com.example.basicmvp.notes.data.DataSource;
import com.example.basicmvp.notes.data.Repository;
import com.example.basicmvp.notes.mvp.model.Notes;

/**
 * Created by mithilesh on 8/28/16.
 */
public class DetailNotePresenter implements DetailNoteContract.Presenter {

    private Repository mRepository;
    private DetailNoteContract.View mView;

    public DetailNotePresenter(Repository repository, DetailNoteContract.View view) {
        this.mRepository = repository;
        this.mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void deleteNote(Notes note, final DetailNoteContract.View.DeleteNotesCallBack callBack) {
        mRepository.completeNotes(note.getId(), new DataSource.CommonOperationCallBack() {
            @Override
            public void success(String successMessage) {
                callBack.success(successMessage);
            }

            @Override
            public void failed(String errMessage) {
                callBack.failed(errMessage);
            }
        });
    }
}
