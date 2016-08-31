package com.example.basicmvp.notes.data.source.fake;

import android.content.Context;

import com.example.basicmvp.notes.data.DataSource;
import com.example.basicmvp.notes.mvp.model.Notes;

import java.util.ArrayList;

/**
 * Created by mithilesh on 8/29/16.
 */
public class FakeDataSource implements DataSource {


    private static FakeDataSource INSTANCE;

    private Context mContext;

    private FakeDataSource(Context context) {
        mContext = context;
    }

    public static FakeDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new FakeDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getAllNotes(GetAllNotesCallBack callBack) {
        ArrayList<Notes> listNotes = new ArrayList<Notes>();

        Notes notes = new Notes();

        notes.setId(1);
        notes.setTitle("Reminder");
        notes.setBody("Submit the Assignment.");
        notes.setDateCreated("29 Aug");

        listNotes.add(notes);

        Notes note = new Notes();
        note.setId(1);
        note.setTitle("Trip");
        note.setBody("Trip to Ladakh with school friends.");
        note.setDateCreated("30 Aug");

        listNotes.add(note);

        callBack.success(listNotes);
    }

    @Override
    public void getNote(int noteId, GetSingleNoteCallBack callBack) {

    }

    @Override
    public void addNote(Notes notes, CommonOperationCallBack callBack) {

    }

    @Override
    public void editNote(Notes notes, int notesId, CommonOperationCallBack callBack) {

    }

    @Override
    public void completeNotes(int noteId, CommonOperationCallBack callBack) {

    }
}
