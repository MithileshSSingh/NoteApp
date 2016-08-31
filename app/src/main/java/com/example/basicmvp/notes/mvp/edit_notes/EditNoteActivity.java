package com.example.basicmvp.notes.mvp.edit_notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.basicmvp.notes.R;
import com.example.basicmvp.notes.data.Repository;
import com.example.basicmvp.notes.di.RepositoryInjector;
import com.example.basicmvp.notes.mvp.BaseActivity;
import com.example.basicmvp.notes.mvp.model.Notes;
import com.example.basicmvp.notes.utils.ActivityUtils;

/**
 * Created by mithilesh on 8/29/16.
 */
public class EditNoteActivity extends BaseActivity {

    private EditNoteFragment mView;
    private EditNotePresenter mPresenter;

    private int noteId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void init() {
        initViews();
        initMember();
        initListener();
        initData();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initMember() {
        Intent intent = getIntent();
        Notes notes = (Notes) intent.getSerializableExtra("edit");

        mView = (EditNoteFragment) getSupportFragmentManager().findFragmentById(R.id.content);

        if (mView == null) {
            mView = EditNoteFragment.newInstance();

            Bundle bundle = new Bundle();
            bundle.putSerializable("edit", notes);
            mView.setArguments(bundle);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.content);
        }

        mPresenter = new EditNotePresenter(
                RepositoryInjector.provideRepository(getApplicationContext()),
                mView);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void showSnakBarText(Context context, String message) {
        Intent intent = new Intent();
        intent.putExtra("message", message);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
