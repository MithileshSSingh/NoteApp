package com.example.basicmvp.notes.mvp.add_notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.basicmvp.notes.R;
import com.example.basicmvp.notes.mvp.BaseActivity;
import com.example.basicmvp.notes.mvp.BaseFragment;
import com.example.basicmvp.notes.mvp.edit_notes.EditNotePresenter;
import com.example.basicmvp.notes.mvp.model.Notes;
import com.example.basicmvp.notes.utils.DateTimeUtils;

import java.util.Date;

/**
 * Created by mithilesh on 8/30/16.
 */
public class AddNoteFragment extends BaseFragment implements AddNoteContract.View, View.OnClickListener {

    private AddNoteContract.Presenter mPresenter;

    private EditText etTitle;
    private EditText etDetail;

    private Button btnCancel;
    private Button btnAdd;

    public AddNoteFragment() {

    }

    public static AddNoteFragment newInstance() {
        return new AddNoteFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_add_note,
                container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
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
        etTitle = (EditText) mView.findViewById(R.id.etTitle);
        etDetail = (EditText) mView.findViewById(R.id.etDetail);

        btnAdd = (Button) mView.findViewById(R.id.btnAdd);
        btnCancel = (Button) mView.findViewById(R.id.btnCancel);
    }

    @Override
    protected void initMember() {

    }

    @Override
    protected void initListener() {
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setPresenter(AddNoteContract.Presenter presenter) {
        mPresenter = presenter;
    }


    private void addNote(Notes notes) {
        mPresenter.addNote(notes, new AddNotesCallBack() {
            @Override
            public void success(String message) {
                ((AddNoteActivity) getActivity()).showSnakBarText(mActivity, message);
            }

            @Override
            public void failed(String errMessage) {
                ((AddNoteActivity) mActivity).showSnakBarText(mActivity, errMessage);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                String title = etTitle.getText().toString().trim();
                String detail = etDetail.getText().toString().trim();
                String date = DateTimeUtils.formateToDate(new Date());

                if (title.equals("") || detail.equals("")) {
                    ((AddNoteActivity) getActivity()).showSnakBarText(mActivity, "Enter title and Detail.");
                } else {
                    Notes notes = new Notes();
                    notes.setTitle(title);
                    notes.setBody(detail);
                    notes.setDateCreated(date);

                    addNote(notes);
                }
                break;
            case R.id.btnCancel:
                getActivity().finish();
                break;
        }
    }
}
