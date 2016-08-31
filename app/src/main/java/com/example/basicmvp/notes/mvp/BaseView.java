package com.example.basicmvp.notes.mvp;

/**
 * Created by mithilesh on 8/28/16.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
}
