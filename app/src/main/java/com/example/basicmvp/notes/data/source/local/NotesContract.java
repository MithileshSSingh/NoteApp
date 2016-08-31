package com.example.basicmvp.notes.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by mithilesh on 8/29/16.
 */
public final class NotesContract {

    public NotesContract() {
    }

    public abstract class NotesEntry implements BaseColumns {
        public static final String TABLE_NAME = "Notes";
        public static final String ID = "_id";
        public static final String TITLE = "title";
        public static final String BODY = "body";
        public static final String DATE_CREATED = "date_created";
    }
}
