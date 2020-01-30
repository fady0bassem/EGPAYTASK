package com.fady.egpaytask.ui.dialogues;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.fady.egpaytask.R;
import com.github.loadingview.LoadingView;

public class CustomProgressDialog {

    static String currentLanguage;

    public static ProgressDialog showProgressDialog(Context context, String message) {

        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setIndeterminate(true);
        dialog.setMessage(message);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.show();

        return dialog;
    }

    public static Dialog loadingIndicatorView(Context context, boolean cancelable) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.indicator_view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LoadingView loadingView = dialog.findViewById(R.id.loadingView);
        loadingView.start();

        dialog.setCancelable(false);
        dialog.show();

        return dialog;
    }


}
