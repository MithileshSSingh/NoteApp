package com.example.basicmvp.notes.mvp.add_notes;

import com.example.basicmvp.notes.mvp.BasePresenter;
import com.example.basicmvp.notes.mvp.BaseView;
import com.example.basicmvp.notes.mvp.model.Notes;

import java.util.List;

/**
 * Created by mithilesh on 8/28/16.
 */
public interface AddNoteContract {
    interface View extends BaseView<Presenter> {

        interface AddNotesCallBack {
            void success(String message);

            void failed(String errMessage);
        }
    }

    interface Presenter extends BasePresenter {
        void addNote(Notes note, View.AddNotesCallBack callBack);
    }
}
