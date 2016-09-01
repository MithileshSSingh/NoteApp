package com.example.basicmvp.notes.mvp.screen_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basicmvp.notes.R;
import com.example.basicmvp.notes.mvp.BaseFragment;
import com.example.basicmvp.notes.mvp.model.Notes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mithilesh on 8/28/16.
 */
public class NoteListFragment extends BaseFragment implements View.OnClickListener, NoteListContract.View, NoteListContract.View.ListItemClickCallBack {

    private NoteListContract.Presenter mPresenter;

    private RecyclerView rvNotesList;

    private NotesListAdapter mNoteListAdapter;

    private ArrayList<Notes> mListNotes = new ArrayList<Notes>();

    public NoteListFragment() {

    }

    public static NoteListFragment newInstance() {
        return new NoteListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fratment_notes_list,
                container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAllNotes();
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
        rvNotesList = (RecyclerView) mView.findViewById(R.id.rvNotesList);
    }

    @Override
    protected void initMember() {
        mNoteListAdapter = new NotesListAdapter(mActivity, mListNotes, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mActivity.getApplicationContext());
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        rvNotesList.setLayoutManager(layoutManager);
        rvNotesList.setItemAnimator(itemAnimator);

        rvNotesList.setAdapter(mNoteListAdapter);

        mNoteListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
    }

    private void loadAllNotes() {
        mPresenter.getAllNotes(new GetAllNotesCallBack() {
            @Override
            public void success(List<Notes> listNotes) {
                Log.v("NoteListFragment ", listNotes.toString());

                mListNotes.clear();
                mListNotes.addAll(listNotes);

                mNoteListAdapter.setList(mListNotes);
                mNoteListAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(String errMessage) {

                if (errMessage == null || errMessage.trim().equals("")) {
                    errMessage = "Error While getting all Notes...";
                }

                Snackbar.make(mView, errMessage, Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                break;
        }
    }

    @Override
    public void setPresenter(NoteListContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showEditScreen(Notes notes) {
        ((NoteListActivity) mActivity).showEditScreen(notes);
    }

    @Override
    public void showDetailScreen(Notes notes) {
        ((NoteListActivity) mActivity).showDetailScreen(notes);
    }
}
