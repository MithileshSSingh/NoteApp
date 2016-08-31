package com.example.basicmvp.notes.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.basicmvp.notes.data.DataSource;
import com.example.basicmvp.notes.mvp.model.Notes;

import java.util.ArrayList;

/**
 * Created by mithilesh on 8/28/16.
 */
public class LocalDataSource implements DataSource {

    private static LocalDataSource INSTANCE;
    private DbHelper mDbHelper;

    private Context mContext;

    private LocalDataSource(Context context) {
        mContext = context;
        mDbHelper = new DbHelper(context);
    }

    public static LocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(context);
        }
        return INSTANCE;
    }

    /**
     * Main code of getting all notes from database will be here
     *
     * @param callBack
     */
    @Override
    public void getAllNotes(GetAllNotesCallBack callBack) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor c = null;

        try {
            c = db.query(
                    NotesContract.NotesEntry.TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            ArrayList<Notes> list = new ArrayList<Notes>();

            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++, c.moveToNext()) {
                Notes notes = new Notes();

                notes.setId(c.getInt(c.getColumnIndex(NotesContract.NotesEntry.ID)));
                notes.setTitle(c.getString(c.getColumnIndex(NotesContract.NotesEntry.TITLE)));
                notes.setBody(c.getString(c.getColumnIndex(NotesContract.NotesEntry.BODY)));
                notes.setDateCreated(c.getString(c.getColumnIndex(NotesContract.NotesEntry.DATE_CREATED)));
                list.add(notes);
            }

            callBack.success(list);
        } catch (Exception e) {
            e.printStackTrace();

            callBack.failed(e.getMessage());

        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    @Override
    public void getNote(int noteId, GetSingleNoteCallBack callBack) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String selection = NotesContract.NotesEntry.ID + "=" + noteId;

        Cursor c = null;

        try {

            c = db.query(
                    NotesContract.NotesEntry.TABLE_NAME,
                    null,
                    selection,
                    null,
                    null,
                    null,
                    null
            );


            ArrayList<Notes> list = new ArrayList<Notes>();

            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++, c.moveToNext()) {
                Notes notes = new Notes();

                notes.setId(c.getInt(c.getColumnIndex(NotesContract.NotesEntry.ID)));
                notes.setTitle(c.getString(c.getColumnIndex(NotesContract.NotesEntry.TITLE)));
                notes.setBody(c.getString(c.getColumnIndex(NotesContract.NotesEntry.BODY)));
                notes.setDateCreated(c.getString(c.getColumnIndex(NotesContract.NotesEntry.DATE_CREATED)));
                list.add(notes);
            }

            callBack.success(list.get(0));

        } catch (Exception e) {
            e.printStackTrace();

            callBack.failed(e.getMessage());

        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    @Override
    public void addNote(Notes notes, CommonOperationCallBack callBack) {

        try {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(NotesContract.NotesEntry.TITLE, notes.getTitle());
            values.put(NotesContract.NotesEntry.BODY, notes.getBody());
            values.put(NotesContract.NotesEntry.DATE_CREATED, notes.getDateCreated());

            long count = db.insert(NotesContract.NotesEntry.TABLE_NAME, null, values);

            callBack.success("1 note inserted.");

        } catch (SQLiteException e) {
            e.printStackTrace();
            callBack.failed(e.getMessage());
        }
    }

    @Override
    public void editNote(Notes notes, int notesId, CommonOperationCallBack callBack) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(NotesContract.NotesEntry.TITLE, notes.getTitle());
            values.put(NotesContract.NotesEntry.BODY, notes.getBody());

            String selection = NotesContract.NotesEntry.ID + " = ? ";
            String[] arg = new String[]{String.valueOf(notesId)};
            int count = db.update(
                    NotesContract.NotesEntry.TABLE_NAME,
                    values,
                    selection,
                    arg);
            callBack.success(count + " note updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            callBack.failed(e.getMessage());
        }
    }

    @Override
    public void completeNotes(int noteId, CommonOperationCallBack callBack) {
        try {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            String selection = NotesContract.NotesEntry.ID + " = " + noteId;
            int count = db.delete(
                    NotesContract.NotesEntry.TABLE_NAME,
                    selection,
                    null
            );

            callBack.success(count + " note completed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            callBack.failed(e.getMessage());
        }
    }

}
