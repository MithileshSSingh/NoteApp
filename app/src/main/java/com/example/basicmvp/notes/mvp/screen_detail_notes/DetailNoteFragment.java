package com.example.basicmvp.notes.mvp.screen_detail_notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.basicmvp.notes.R;
import com.example.basicmvp.notes.mvp.BaseFragment;
import com.example.basicmvp.notes.mvp.model.Notes;

/**
 * Created by mithilesh on 8/29/16.
 */
public class DetailNoteFragment extends BaseFragment implements DetailNoteContract.View, View.OnClickListener {

    private DetailNoteContract.Presenter mPresenter;
    private Notes data;

    private TextView tvTitle;
    private TextView tvDetail;

    private Button btnCancle;
    private Button btnDone;

    public DetailNoteFragment() {

    }

    public static DetailNoteFragment newInstance() {
        return new DetailNoteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_detail_note, container, false);

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

        tvTitle = (TextView) mView.findViewById(R.id.tvTitle);
        tvDetail = (TextView) mView.findViewById(R.id.tvDetail);

        btnCancle = (Button) mView.findViewById(R.id.btnCancel);
        btnDone = (Button) mView.findViewById(R.id.btnDone);
    }

    @Override
    protected void initMember() {
        Bundle bundle = getArguments();
        data = (Notes) bundle.getSerializable("detail");
    }

    @Override
    protected void initListener() {
        btnCancle.setOnClickListener(this);
        btnDone.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tvDetail.setText(data.getBody());
        tvTitle.setText(data.getTitle());
    }

    @Override
    public void setPresenter(DetailNoteContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void deleteNote() {
        mPresenter.deleteNote(data, new DeleteNotesCallBack() {
            @Override
            public void success(String message) {
                ((DetailNoteActivity) mActivity).showSnakBarText(mActivity, message);
            }

            @Override
            public void failed(String errMessage) {
                ((DetailNoteActivity) mActivity).showSnakBarText(mActivity, errMessage);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancel:
                mActivity.finish();
                break;
            case R.id.btnDone:
                deleteNote();
                break;
        }
    }

}
