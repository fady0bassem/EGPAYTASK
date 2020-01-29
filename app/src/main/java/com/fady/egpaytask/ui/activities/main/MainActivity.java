package com.fady.egpaytask.ui.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fady.egpaytask.R;
import com.fady.egpaytask.ui.activities.base.BaseActivity;
import com.fady.egpaytask.ui.interfaces.ButtonClickInterface;
import com.fady.egpaytask.utils.CreateButton;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainActivityInterface {

    @BindView(R.id.views_linearlayout)
    LinearLayout linearlayout;

    private MainActivityPresenter presenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        presenter = new MainActivityPresenter(this, this, this);

        presenter.setView(linearlayout);
    }
}