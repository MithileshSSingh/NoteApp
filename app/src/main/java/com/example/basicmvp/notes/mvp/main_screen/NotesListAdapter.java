package com.example.basicmvp.notes.mvp.main_screen;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.basicmvp.notes.R;
import com.example.basicmvp.notes.mvp.detail_notes.DetailNoteActivity;
import com.example.basicmvp.notes.mvp.edit_notes.EditNoteActivity;
import com.example.basicmvp.notes.mvp.model.Notes;

import java.util.Collections;
import java.util.List;

/**
 * Created by mithilesh on 8/29/16.
 */
public class NotesListAdapter extends RecyclerView.Adapter<NotesListViewHolder> {

    private List<Notes> mListData = Collections.emptyList();
    private LayoutInflater mInflater;

    private NoteListContract.View.ListItemClickCallBack mListener;

    public NotesListAdapter(Context context, List<Notes> list, NoteListContract.View.ListItemClickCallBack listener) {
        mInflater = LayoutInflater.from(context);
        mListData = list;
        mListener = listener;
    }

    @Override
    public NotesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = mInflater.inflate(R.layout.item_note, parent, false);
        return new NotesListViewHolder(contentView,mListener);
    }

    @Override
    public void onBindViewHolder(NotesListViewHolder holder, int position) {
        Notes notes = mListData.get(position);
        holder.apply(notes);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setList(List<Notes> list) {
        this.mListData = list;
    }
}

class NotesListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private View mView;
    private TextView tvTitle;
    private TextView tvBody;
    private TextView tvDate;

    private ImageView ivEdit;

    private LinearLayout llNote;

    NoteListContract.View.ListItemClickCallBack mListener;

    private Notes mData;

    public NotesListViewHolder(View itemView, NoteListContract.View.ListItemClickCallBack listener) {
        super(itemView);
        mView = itemView;
        mListener = listener;
        init();
    }

    public NotesListViewHolder(View itemView) {
        super(itemView);
    }

    private void init() {
        initView();
        initListener();
    }

    private void initView() {
        tvTitle = (TextView) mView.findViewById(R.id.tvTitle);
        tvBody = (TextView) mView.findViewById(R.id.tvBody);
        tvDate = (TextView) mView.findViewById(R.id.tvCreatedDate);
        ivEdit = (ImageView) mView.findViewById(R.id.ivEdit);

        llNote = (LinearLayout) mView.findViewById(R.id.llNote);
    }

    private void initListener() {
        ivEdit.setOnClickListener(this);
        llNote.setOnClickListener(this);
    }

    public void apply(Notes data) {
        mData = data;
        tvTitle.setText(data.getTitle());
        tvBody.setText(data.getBody());
        tvDate.setText(data.getDateCreated());
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ivEdit:
                mListener.showEditScreen(mData);
                break;
            case R.id.llNote:
                mListener.showDetailScreen(mData);
                break;
        }
    }
}
