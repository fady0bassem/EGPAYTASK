package com.fady.egpaytask.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.fady.egpaytask.R;
import com.fady.egpaytask.data.DataModel;
import com.fady.egpaytask.data.MultipleModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CreateSpinner extends AppCompatActivity {

    public static Spinner createSpinner(Context context, Activity activity, DataModel dataModel) {

        Spinner spinner = new Spinner(context, Spinner.MODE_DIALOG);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(25, 25, 25, 10);

        spinner.setLayoutParams(params);
        spinner.setPadding(15, 25, 15, 25);
        spinner.setGravity(Gravity.RIGHT);

        spinner.setId(dataModel.getId());
        spinner.setTag(dataModel.getName());
        spinner.setBackgroundResource(R.drawable.spinner_background);

        ArrayList<MultipleModel> multipleModelArrayList = toJson(dataModel.getMultiple());
        setSpinnerAdapter(context, spinner, multipleModelArrayList);

        for (int i = 0; i < multipleModelArrayList.size(); i++) {
            MultipleModel model = multipleModelArrayList.get(i);
            if (model.getValue().equals(dataModel.getDefaultValue()))
                spinner.setSelection(i);
        }

        return spinner;
    }

    static ArrayList<MultipleModel> toJson(String json) {
        Gson gson = new Gson();
        ArrayList<MultipleModel> multipleModel = new ArrayList<>();
        Type listType = new TypeToken<List<MultipleModel>>() {
        }.getType();
        multipleModel = gson.fromJson(json, listType);
        return multipleModel;
    }

    static void setSpinnerAdapter(Context context, Spinner spinner, ArrayList<MultipleModel> multipleModelArrayList){
        List stringsList = new ArrayList();

        for (MultipleModel item : multipleModelArrayList)
            stringsList.add(item.getName());

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, stringsList);
        spinner.setAdapter(adapter);
    }

}
