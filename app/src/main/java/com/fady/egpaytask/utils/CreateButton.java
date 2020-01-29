package com.fady.egpaytask.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.fady.egpaytask.R;
import com.fady.egpaytask.ui.interfaces.ButtonClickInterface;

public class CreateButton extends AppCompatActivity {

    public static Button CreateButton(Context context, String text, final ButtonClickInterface callbacks) {

        Button button = new Button(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(25, 25, 25, 25);

        button.setText(text);
        button.setLayoutParams(params);
        button.setGravity(Gravity.CENTER);
        button.setTextColor(context.getResources().getColor(R.color.white));
        button.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        button.setOnClickListener(v -> callbacks.onClick(true));

        return button;
    }
}