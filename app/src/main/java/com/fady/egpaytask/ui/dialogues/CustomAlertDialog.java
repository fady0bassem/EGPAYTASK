package com.fady.egpaytask.ui.dialogues;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.fady.egpaytask.R;
import com.fady.egpaytask.utils.ErrorCodes;


public class CustomAlertDialog implements DialogClickInterface, DialogInterface.OnClickListener {

    public static CustomAlertDialog mDialog;
    public DialogClickInterface mDialogClickInterface;
    private int mDialogIdentifier;
    private Context mContext;
    static Dialog dialog;

    public static CustomAlertDialog getInstance() {

        if (mDialog == null)
            mDialog = new CustomAlertDialog();

        return mDialog;

    }

    public void showConfirmDialog(String pTitle, String pMessage, String pPositiveButton, String pNegativeButton, Context pContext, final int pDialogIdentifier) {

        mDialogClickInterface = (DialogClickInterface) pContext;
        mDialogIdentifier = pDialogIdentifier;
        mContext = pContext;

        dialog = new Dialog(pContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alertdialog);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if (!pTitle.equals("")) {
            TextView title = dialog.findViewById(R.id.textTitle);
            title.setText(pTitle);
            title.setVisibility(View.VISIBLE);
        }

        TextView text = dialog.findViewById(R.id.textDialog);
        text.setText(pMessage);
        Button button = (Button) dialog.findViewById(R.id.button);
        button.setText(pPositiveButton);
        Button button1 = (Button) dialog.findViewById(R.id.button1);
        button1.setText(pNegativeButton);
        dialog.setCancelable(false);
        dialog.show();

        // if decline button is clicked, close the custom dialog
        button.setOnClickListener(v -> {
            // Close dialog
            mDialogClickInterface.onClickPositiveButton(dialog, pDialogIdentifier);
        });
        button1.setOnClickListener(v -> {
            // Close dialog
            mDialogClickInterface.onClickNegativeButton(dialog, pDialogIdentifier);
        });
        dialog.setOnKeyListener((arg0, keyCode, event) -> {

            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog.dismiss();
                dialog.setCancelable(true);
            }

            return true;
        });

    }

    public void showInfoDialog(String pTitle, String pMessage, String pPositiveButton, Context pContext, final int pDialogIdentifier) {

        mDialogClickInterface = (DialogClickInterface) pContext;
        mDialogIdentifier = pDialogIdentifier;
        mContext = pContext;

        dialog = new Dialog(pContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alertdialog_info);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if (!pTitle.equals("")) {
            TextView title = dialog.findViewById(R.id.textTitle);
            title.setText(pTitle);
            title.setVisibility(View.VISIBLE);
        }

        TextView text = dialog.findViewById(R.id.textDialog);
        text.setText(pMessage);
        Button button = dialog.findViewById(R.id.button);
        button.setText(pPositiveButton);
        dialog.setCancelable(false);
        dialog.show();
        // if decline button is clicked, close the custom dialog
        button.setOnClickListener(v -> {
            // Close dialog
            mDialogClickInterface.onClickPositiveButton(dialog, pDialogIdentifier);
        });

        dialog.setOnKeyListener((arg0, keyCode, event) -> {

            if (keyCode == KeyEvent.KEYCODE_BACK) {

                dialog.dismiss();
                dialog.setCancelable(true);
            }

            return true;
        });
    }

    public void showErrDialog(Context pContext, String errCode, final int pDialogIdentifier) {

        mDialogClickInterface = (DialogClickInterface) pContext;
        mDialogIdentifier = pDialogIdentifier;
        mContext = pContext;

        dialog = new Dialog(pContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alertdialog_info);
        //0dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView title = dialog.findViewById(R.id.textTitle);
        title.setText(pContext.getString(R.string.error));

        TextView body = dialog.findViewById(R.id.textDialog);

        if (errCode.equals(ErrorCodes.ERR_RESPONSE_ERROR)) {
            body.setText(pContext.getString(R.string.connection_err) + "\n" + errCode);

        } else if (errCode.equals(ErrorCodes.ERR_NETWORK_FAILURE)) {
            body.setText(pContext.getString(R.string.NoInternetConnection) + "\n" + errCode);

        } else if (errCode.equals(ErrorCodes.ERR_CONVERSION_ISSUE)) {
            body.setText(pContext.getString(R.string.unkown_error) + "\n" + errCode);

        } else {
            body.setText(pContext.getString(R.string.connection_err) + "\n" + errCode);
        }

        Button button = dialog.findViewById(R.id.button);
        button.setText(pContext.getString(R.string.close));

        dialog.setCancelable(false);
        dialog.show();

        // if decline button is clicked, close the custom dialog
        button.setOnClickListener(v -> {
            // Close dialog
            mDialogClickInterface.onClickPositiveButton(dialog, pDialogIdentifier);
        });

        dialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    dialog.dismiss();
                    dialog.setCancelable(true);
                }

                return true;
            }


        });
    }

    @Override
    public void onClick(DialogInterface pDialog, int pWhich) {

        switch (pWhich) {
            case DialogInterface.BUTTON_POSITIVE:
                mDialogClickInterface.onClickPositiveButton(pDialog, mDialogIdentifier);

                break;
            case DialogInterface.BUTTON_NEGATIVE:
                mDialogClickInterface.onClickNegativeButton(pDialog, mDialogIdentifier);
                break;
        }

    }

    @Override
    public void onClickPositiveButton(DialogInterface pDialog, int pDialogIntefier) {
    }

    @Override
    public void onClickNegativeButton(DialogInterface pDialog, int pDialogIntefier) {

        dialog.dismiss();
    }
}
