package com.example.basicmvp.notes.mvp;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.basicmvp.notes.R;

/**
 * Created by mithilesh on 8/28/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    protected abstract void init();

    protected abstract void initViews();

    protected abstract void initMember();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void showSnakBarText(Context context, String message);
}
