package com.example.basicmvp.notes.mvp.main_screen;

import com.example.basicmvp.notes.mvp.model.Notes;
import com.example.basicmvp.notes.mvp.BasePresenter;
import com.example.basicmvp.notes.mvp.BaseView;

import java.util.List;

/**
 * Created by mithilesh on 8/28/16.
 */
public interface NoteListContract {
    interface View extends BaseView<Presenter> {

        interface ListItemClickCallBack {
            void showEditScreen(Notes notes);

            void showDetailScreen(Notes notes);
        }

        interface GetAllNotesCallBack {
            void success(List<Notes> listNotes);

            void failed(String errMessage);
        }
    }

    interface Presenter extends BasePresenter {
        void getAllNotes(View.GetAllNotesCallBack callBack);
    }
}
