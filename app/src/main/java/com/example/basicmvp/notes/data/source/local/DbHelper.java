package com.example.basicmvp.notes.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mithilesh on 8/28/16.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "notes.db";

    private final String CREATE_NOTES_TABLE = "" +
            "CREATE TABLE " + NotesContract.NotesEntry.TABLE_NAME + " ( " +
            NotesContract.NotesEntry.ID + "           integer primary key autoincrement, " +
            NotesContract.NotesEntry.TITLE + "        text, " +
            NotesContract.NotesEntry.BODY + "         text, " +
            NotesContract.NotesEntry.DATE_CREATED + " text " +
            ")";

    private final String DELETE_NOTES_TABLE = "" +
            "DROP TABLE IF EXIST " + NotesContract.NotesEntry.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_NOTES_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
