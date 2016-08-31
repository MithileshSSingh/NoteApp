package com.example.basicmvp.notes.mvp.add_notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.basicmvp.notes.R;
import com.example.basicmvp.notes.di.RepositoryInjector;
import com.example.basicmvp.notes.mvp.BaseActivity;
import com.example.basicmvp.notes.utils.ActivityUtils;

/**
 * Created by mithilesh on 8/30/16.
 */
public class AddNoteActivity extends BaseActivity {

    private AddNotePresenter mPresenter;
    private AddNoteFragment mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

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
        mView = (AddNoteFragment) getSupportFragmentManager().findFragmentById(R.id.contentAddNote);

        if (mView == null) {
            mView = AddNoteFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.contentAddNote);
        }

        mPresenter = new AddNotePresenter(
                RepositoryInjector.provideRepository(getApplicationContext()),
                mView
        );
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
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
