package com.fady.egpaytask.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultipleModel {
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("name")
    @Expose
    private String name;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
