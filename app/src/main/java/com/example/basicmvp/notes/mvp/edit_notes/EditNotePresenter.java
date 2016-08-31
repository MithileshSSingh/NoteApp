package com.example.basicmvp.notes.mvp.edit_notes;

import com.example.basicmvp.notes.data.DataSource;
import com.example.basicmvp.notes.data.Repository;
import com.example.basicmvp.notes.mvp.model.Notes;

/**
 * Created by mithilesh on 8/28/16.
 */
public class EditNotePresenter implements EditNoteContract.Presenter {

    private Repository mRepository;
    private EditNoteContract.View mView;

    public EditNotePresenter(Repository repository, EditNoteContract.View view) {
        this.mRepository = repository;
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void editNote(Notes note, final EditNoteContract.View.EditNotesCallBack callBack) {
        mRepository.editNote(note, note.getId(), new DataSource.CommonOperationCallBack() {

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
