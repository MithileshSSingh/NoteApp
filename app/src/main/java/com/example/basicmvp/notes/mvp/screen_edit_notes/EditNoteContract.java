package com.example.basicmvp.notes.mvp.screen_edit_notes;

import com.example.basicmvp.notes.mvp.BasePresenter;
import com.example.basicmvp.notes.mvp.BaseView;
import com.example.basicmvp.notes.mvp.model.Notes;

/**
 * Created by mithilesh on 8/28/16.
 */
public interface EditNoteContract {
    interface View extends BaseView<Presenter> {

        interface EditNotesCallBack {
            void success(String message);

            void failed(String errMessage);
        }
    }

    interface Presenter extends BasePresenter {
        void editNote(Notes note, View.EditNotesCallBack callBack);
    }
}
