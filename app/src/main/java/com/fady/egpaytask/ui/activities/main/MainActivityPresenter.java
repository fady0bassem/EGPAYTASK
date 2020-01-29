package com.fady.egpaytask.ui.activities.main;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.fady.egpaytask.R;
import com.fady.egpaytask.data.DataModel;
import com.fady.egpaytask.ui.interfaces.ButtonClickInterface;
import com.fady.egpaytask.utils.CreateButton;
import com.fady.egpaytask.utils.CreateEditText;
import com.fady.egpaytask.utils.CreateSpinner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MainActivityPresenter {

    private Context context;
    private Activity activity;
    private MainActivityInterface anInterface;

    MainActivityPresenter(Context context, Activity activity, MainActivityInterface anInterface) {
        this.context = context;
        this.activity = activity;
        this.anInterface = anInterface;
    }

    String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    ArrayList<DataModel> toGson(String json) {
        Gson gson = new Gson();
        ArrayList<DataModel> dataModelArrayList;
        Type listType = new TypeToken<List<DataModel>>() {
        }.getType();
        dataModelArrayList = gson.fromJson(json, listType);
        return dataModelArrayList;
    }

    List<View> prepareView() {
        List<View> views = new ArrayList<>();

        ArrayList<DataModel> dataModelArrayList = toGson(loadJSONFromAsset());
        if (dataModelArrayList != null) {

            Collections.sort(dataModelArrayList, (o1, o2) -> Integer.valueOf(o1.getSort()).compareTo(Integer.valueOf(o2.getSort())));

            for (int i = 0; i < dataModelArrayList.size(); i++) {
                if (dataModelArrayList.get(i).getType().equals("select")) {
                    views.add(CreateSpinner.createSpinner(context, activity, dataModelArrayList.get(i)));
                } else {
                    views.add(CreateEditText.createEditText(context, activity, dataModelArrayList.get(i)));
                }
            }

            return views;
        }
        return views;
    }

    void setView(LinearLayout linearlayout) {
        List<View> views = prepareView();
        for (View view : views) {
            linearlayout.addView(view);
        }

        linearlayout.addView(CreateButton.CreateButton(context, context.getResources().getString(R.string.done), new ButtonClickInterface() {
            @Override
            public void onClick(Boolean value) {
                getData(toGson(loadJSONFromAsset()));
            }
        }));
    }

    void getData(ArrayList<DataModel> dataModels) {
        for (int i = 0; i < dataModels.size(); i++) {

        }
    }
}