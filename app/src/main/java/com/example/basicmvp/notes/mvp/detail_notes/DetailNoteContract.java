package com.example.basicmvp.notes.mvp.detail_notes;

import com.example.basicmvp.notes.mvp.BasePresenter;
import com.example.basicmvp.notes.mvp.BaseView;
import com.example.basicmvp.notes.mvp.model.Notes;

/**
 * Created by mithilesh on 8/28/16.
 */
public interface DetailNoteContract {
    interface View extends BaseView<Presenter> {

        interface DeleteNotesCallBack {
            void success(String message);

            void failed(String errMessage);
        }
    }

    interface Presenter extends BasePresenter {
        void deleteNote(Notes note, View.DeleteNotesCallBack callBack);
    }
}
