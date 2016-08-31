package com.example.basicmvp.notes.mvp.main_screen;

import com.example.basicmvp.notes.data.DataSource;
import com.example.basicmvp.notes.data.Repository;
import com.example.basicmvp.notes.mvp.model.Notes;

import java.util.List;

/**
 * Created by mithilesh on 8/28/16.
 */
public class NoteListPresenter implements NoteListContract.Presenter {

    private Repository mRepository;
    private NoteListContract.View mView;

    public NoteListPresenter(Repository repository, NoteListContract.View veiw) {
        this.mRepository = repository;
        this.mView = veiw;
        mView.setPresenter(this);
    }

    @Override
    public void getAllNotes(final NoteListContract.View.GetAllNotesCallBack callBack) {
        mRepository.getAllNotes(new DataSource.GetAllNotesCallBack() {
            @Override
            public void success(List<Notes> notes) {
                callBack.success(notes);
            }

            @Override
            public void failed(String errMessage) {
                callBack.failed(errMessage);
            }
        });
    }
}
