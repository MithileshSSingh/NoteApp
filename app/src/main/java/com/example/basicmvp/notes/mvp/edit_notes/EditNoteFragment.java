package com.example.basicmvp.notes.mvp.edit_notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.basicmvp.notes.R;
import com.example.basicmvp.notes.mvp.BaseFragment;
import com.example.basicmvp.notes.mvp.add_notes.AddNoteActivity;
import com.example.basicmvp.notes.mvp.model.Notes;
import com.example.basicmvp.notes.utils.DateTimeUtils;

import java.util.Date;

/**
 * Created by mithilesh on 8/29/16.
 */
public class EditNoteFragment extends BaseFragment implements EditNoteContract.View, View.OnClickListener {

    private EditNoteContract.Presenter mPresenter;

    private Notes data;

    private EditText etTitle;
    private EditText etDetail;

    private Button btnCancle;
    private Button btnUpdate;

    public EditNoteFragment() {

    }

    public static EditNoteFragment newInstance() {
        return new EditNoteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_edit_note, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

        etDetail = (EditText) mView.findViewById(R.id.etDetail);
        etTitle = (EditText) mView.findViewById(R.id.etTitle);

        btnCancle = (Button) mView.findViewById(R.id.btnCancel);
        btnUpdate = (Button) mView.findViewById(R.id.btnUpdate);
    }

    @Override
    protected void initMember() {
        Bundle bundle = getArguments();
        data = (Notes) bundle.getSerializable("edit");
    }

    @Override
    protected void initListener() {
        btnCancle.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        etTitle.setText(data.getTitle());
        etDetail.setText(data.getBody());
    }

    @Override
    public void setPresenter(EditNoteContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void editNote(Notes notes) {
        mPresenter.editNote(notes, new EditNotesCallBack() {
            @Override
            public void success(String message) {
                ((EditNoteActivity) mActivity).showSnakBarText(mActivity, message);
                mActivity.finish();
            }

            @Override
            public void failed(String errMessage) {
                ((EditNoteActivity) mActivity).showSnakBarText(mActivity, errMessage);
                mActivity.finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUpdate:
                String title = etTitle.getText().toString().trim();
                String detail = etDetail.getText().toString().trim();

                if (title.equals("") || detail.equals("")) {
                    ((EditNoteActivity) getActivity()).showSnakBarText(mActivity, "Enter title and Detail.");
                } else {
                    Notes notes = new Notes();

                    notes.setId(data.getId());
                    notes.setTitle(title);
                    notes.setBody(detail);

                    editNote(notes);
                }
                break;
            case R.id.btnCancel:
                getActivity().finish();
                break;
        }
    }
}
