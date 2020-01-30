package com.fady.egpaytask.ui.activities.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.fady.egpaytask.R;
import com.fady.egpaytask.data.models.DataModel;
import com.fady.egpaytask.data.models.MultipleModel;
import com.fady.egpaytask.data.remote.ApiClient;
import com.fady.egpaytask.data.remote.ApiInterface;
import com.fady.egpaytask.ui.dialogues.CustomAlertDialog;
import com.fady.egpaytask.ui.interfaces.ButtonClickInterface;
import com.fady.egpaytask.utils.CreateButton;
import com.fady.egpaytask.utils.CreateEditText;
import com.fady.egpaytask.utils.CreateSpinner;
import com.fady.egpaytask.utils.ErrorCodes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                getData(toGson(loadJSONFromAsset()), views);
            }
        }));
    }

    void getData(ArrayList<DataModel> dataModels, List<View> views) {
        boolean status = false;
        Collections.sort(dataModels, (o1, o2) -> Integer.valueOf(o1.getSort()).compareTo(Integer.valueOf(o2.getSort())));
        ArrayList<MultipleModel> multipleModelArrayList = new ArrayList<>();

        for (int i = 0; i < dataModels.size(); i++) {
            if (dataModels.get(i).getType().equals("select")) {
                //multipleModelArrayList = toJson(dataModels.get(i).getMultiple());
                multipleModelArrayList.addAll(toJson(dataModels.get(i).getMultiple()));
            } else {
                EditText editText = (EditText) views.get(i);
                if (dataModels.get(i).getRequired().equals("yes") && editText.getText().toString().equals("")) {
                    editText.setError(context.getString(R.string.required));
                    editText.requestFocus();
                    status = false;
                    break;
                } else {
                    status = true;
                }
            }
        }

        Map<String, String> dataMap = new HashMap<>();

        if (status) {
            for (View view : views) {
                if (view instanceof EditText) {
                    dataMap.put(String.valueOf(view.getId()), ((EditText) view).getText().toString());
                } else {
                    Spinner spinner = (Spinner) view;
                    String selected = spinner.getSelectedItem().toString();
                    for (MultipleModel model : multipleModelArrayList) {
                        if (selected.equals(model.getName())) {
                            dataMap.put(String.valueOf(spinner.getId()), model.getValue());
                        }
                    }
                }
            }
            anInterface.onInitializeRequest(dataMap);
        }
    }

    private ArrayList<MultipleModel> toJson(String json) {
        Gson gson = new Gson();
        ArrayList<MultipleModel> multipleModel = new ArrayList<>();
        Type listType = new TypeToken<List<MultipleModel>>() {
        }.getType();
        multipleModel = gson.fromJson(json, listType);
        return multipleModel;
    }

    void dataRequest(final Dialog dialog, Map<String, String> dataMap) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.Data_MAP_CALL(dataMap);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    CustomAlertDialog.getInstance().showInfoDialog(context.getString(R.string.response_success), response.body(), context.getString(R.string.done), context, 0);
                } else {
                    CustomAlertDialog.getInstance().showErrDialog(context, ErrorCodes.ERR_RESPONSE_ERROR, 0);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                if (t instanceof IOException) {
                    CustomAlertDialog.getInstance().showErrDialog(context, ErrorCodes.ERR_NETWORK_FAILURE, 0);
                } else {
                    CustomAlertDialog.getInstance().showErrDialog(context, ErrorCodes.ERR_CONVERSION_ISSUE, 0);

                }
            }
        });
    }
}