package com.fady.egpaytask.ui.activities.main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.fady.egpaytask.R;
import com.fady.egpaytask.data.remote.ApiInterface;
import com.fady.egpaytask.ui.activities.base.BaseActivity;
import com.fady.egpaytask.ui.dialogues.CustomAlertDialog;
import com.fady.egpaytask.ui.dialogues.CustomProgressDialog;
import com.fady.egpaytask.ui.dialogues.DialogClickInterface;

import java.util.Map;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainActivityInterface, DialogClickInterface {

    @BindView(R.id.views_linearlayout)
    LinearLayout linearlayout;

    private Context context = this;
    private MainActivityPresenter presenter;

    private Dialog dialog;
    private ApiInterface apiInterface;

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

    @Override
    public void onInitializeRequest(Map<String, String> dataMap) {
        dialog = CustomProgressDialog.loadingIndicatorView(context, false);
        presenter.dataRequest(dialog, dataMap);
    }

    @Override
    public void onClickPositiveButton(DialogInterface pDialog, int pDialogIntefier) {
        if (pDialogIntefier == 0)
            CustomAlertDialog.getInstance().onClickNegativeButton(pDialog, 0);
    }

    @Override
    public void onClickNegativeButton(DialogInterface pDialog, int pDialogIntefier) {
        if (pDialogIntefier == 0)
            CustomAlertDialog.getInstance().onClickNegativeButton(pDialog, 0);
    }
}