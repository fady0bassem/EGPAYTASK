package com.fady.egpaytask.ui.activities.splash;

import android.content.Intent;
import android.os.Bundle;

import com.fady.egpaytask.R;
import com.fady.egpaytask.ui.activities.base.BaseActivity;

public class SplashActivity extends BaseActivity implements SplashActivityInterface {

    private SplashActivityPresenter presenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        presenter = new SplashActivityPresenter(this, this);
        presenter.navigate();
    }

    @Override
    public void openMainActivity() {
        finish();
    }
}