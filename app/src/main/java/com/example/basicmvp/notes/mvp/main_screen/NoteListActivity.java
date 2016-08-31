package com.example.basicmvp.notes.mvp.main_screen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.basicmvp.notes.R;
import com.example.basicmvp.notes.di.RepositoryInjector;
import com.example.basicmvp.notes.mvp.BaseActivity;
import com.example.basicmvp.notes.mvp.add_notes.AddNoteActivity;
import com.example.basicmvp.notes.mvp.detail_notes.DetailNoteActivity;
import com.example.basicmvp.notes.mvp.edit_notes.EditNoteActivity;
import com.example.basicmvp.notes.mvp.model.Notes;
import com.example.basicmvp.notes.utils.ActivityUtils;

public class NoteListActivity extends BaseActivity implements View.OnClickListener {

    private NoteListPresenter mPresenter;
    private NoteListFragment mView;
    private FloatingActionButton fab;

    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == Activity.RESULT_OK) {
            String message = data.getStringExtra("message");
            Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
        }
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
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    protected void initMember() {

        mView = (NoteListFragment) getSupportFragmentManager().findFragmentById(R.id.content);

        if (mView == null) {
            mView = NoteListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.content);
        }

        mPresenter = new NoteListPresenter(
                RepositoryInjector.provideRepository(getApplicationContext()),
                mView
        );
    }

    @Override
    protected void initListener() {
        fab.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Intent intent = new Intent(this, AddNoteActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
    }

    public void showEditScreen(Notes notes) {
        Intent intent = new Intent(mView.getContext(), EditNoteActivity.class);
        intent.putExtra("edit", notes);
        startActivityForResult(intent, 1);
    }

    public void showDetailScreen(Notes notes) {
        Intent intent = new Intent(mView.getContext(), DetailNoteActivity.class);
        intent.putExtra("detail", notes);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void showSnakBarText(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
