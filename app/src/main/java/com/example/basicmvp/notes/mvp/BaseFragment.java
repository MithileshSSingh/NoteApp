package com.example.basicmvp.notes.mvp;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basicmvp.notes.mvp.BaseActivity;

/**
 * Created by mithilesh on 8/28/16.
 */
public abstract class BaseFragment extends Fragment {

    public View mView;
    public BaseActivity mActivity;
    public Resources mResources;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        mResources = mActivity.getResources();
    }


    protected abstract void init();

    protected abstract void initViews();

    protected abstract void initMember();

    protected abstract void initListener();

    protected abstract void initData();
}
