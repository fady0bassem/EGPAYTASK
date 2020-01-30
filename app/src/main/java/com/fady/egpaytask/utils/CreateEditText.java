package com.fady.egpaytask.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.fady.egpaytask.R;
import com.fady.egpaytask.data.models.DataModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateEditText extends AppCompatActivity {

    public static EditText createEditText(Context context, Activity activity, DataModel dataModel) {

        EditText editText = new EditText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(25, 25, 25, 10);

        editText.setLayoutParams(params);
        editText.setPadding(15, 25, 15, 25);
        editText.setHintTextColor(context.getResources().getColor(android.R.color.darker_gray));

        editText.setId(dataModel.getId());
        editText.setTag(dataModel.getName());
        editText.setHint(dataModel.getName());
        editText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        editText.setGravity(Gravity.START);
        editText.setBackgroundResource(R.drawable.edittext_background);

        if (dataModel.getType() != null) {
            if (dataModel.getType().equals("number")) {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                editText.setMaxLines(1);
                editText.setSingleLine(true);
                editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            } else if (dataModel.getType().equals("textarea")) {
                editText.setHeight(400);
                editText.setGravity(Gravity.TOP);
                editText.setMaxLines(100);
            } else {
                editText.setMaxLines(1);
                editText.setSingleLine(true);
                editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            }
        }

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (dataModel.getType().equals("date")) {
                if (hasFocus) {
                    hideKeyboard(activity);
                    datepicker(context, editText);
                }
            }
        });

        editText.setOnClickListener(v -> {
            if (dataModel.getType().equals("date")) {
                hideKeyboard(activity);
                datepicker(context, editText);
            }
        });

        return editText;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (null != view && null != view.getWindowToken() && EditText.class.isAssignableFrom(view.getClass()))
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        else
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static void datepicker(Context context, EditText editText) {
        Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

            editText.setText(sdf.format(myCalendar.getTime()));
            editText.setError(null);
        };

        new DatePickerDialog(context, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}