package com.example.basicmvp.notes.data;

import com.example.basicmvp.notes.mvp.model.Notes;

import java.util.List;

/**
 * Created by mithilesh on 8/28/16.
 */
public interface DataSource {

    interface GetAllNotesCallBack {
        void success(List<Notes> notes);

        void failed(String errMessage);
    }

    interface GetSingleNoteCallBack {
        void success(Notes notes);

        void failed(String errMessage);
    }

    interface CommonOperationCallBack {
        void success(String successMessage);

        void failed(String errMessage);
    }

    void getAllNotes(GetAllNotesCallBack callBack);

    void getNote(int noteId, GetSingleNoteCallBack callBack);

    void addNote(Notes notes, CommonOperationCallBack callBack);

    void editNote(Notes notes, int notesId, CommonOperationCallBack callBack);

    void completeNotes(int noteId, CommonOperationCallBack callBack);
}