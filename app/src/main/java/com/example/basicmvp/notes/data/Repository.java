package com.example.basicmvp.notes.data;

import com.example.basicmvp.notes.mvp.model.Notes;

import java.util.List;

/**
 * Created by mithilesh on 8/28/16.
 */
public class Repository implements DataSource {

    private static Repository INSTANCE;

    private final DataSource mLocalDataSource;

    private Repository(DataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    public static Repository getInstance(DataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getAllNotes(final GetAllNotesCallBack callBack) {
        mLocalDataSource.getAllNotes(new GetAllNotesCallBack() {
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

    @Override
    public void getNote(int noteId, final GetSingleNoteCallBack callBack) {
        mLocalDataSource.getNote(noteId, new GetSingleNoteCallBack() {
            @Override
            public void success(Notes notes) {
                callBack.success(notes);
            }

            @Override
            public void failed(String errMessage) {
                callBack.failed(errMessage);
            }
        });
    }

    @Override
    public void addNote(Notes notes, final CommonOperationCallBack callBack) {
        mLocalDataSource.addNote(notes, new CommonOperationCallBack() {
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

    @Override
    public void editNote(Notes notes, int notesId, final CommonOperationCallBack callBack) {
        mLocalDataSource.editNote(notes, notesId, new CommonOperationCallBack() {
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

    @Override
    public void completeNotes(int noteId, final CommonOperationCallBack callBack) {
        mLocalDataSource.completeNotes(noteId, new CommonOperationCallBack() {
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
