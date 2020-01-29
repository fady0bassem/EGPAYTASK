package com.fady.egpaytask.ui.activities.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fady.egpaytask.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getName();

    private LinearLayout linearlayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
        onViewReady(savedInstanceState, getIntent());
    }

    @Override
    public void setContentView(int layoutResID) {
        linearlayout = (LinearLayout) getLayoutInflater().inflate(R.layout.base_activity, null);

        LinearLayout linear = linearlayout.findViewById(R.id.linearlayout);
        getLayoutInflater().inflate(layoutResID, linear, true);

        super.setContentView(linearlayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected abstract int getLayoutResourceId();

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {

    }
}
