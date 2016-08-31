package com.example.basicmvp.notes.di;

import android.content.Context;

import com.example.basicmvp.notes.data.Repository;
import com.example.basicmvp.notes.data.source.fake.FakeDataSource;
import com.example.basicmvp.notes.data.source.local.LocalDataSource;

/**
 * Created by mithilesh on 8/28/16.
 */
public class RepositoryInjector {

    public static Repository provideRepository(Context context) {
        return Repository.getInstance(LocalDataSource.getInstance(context));
    }
}
