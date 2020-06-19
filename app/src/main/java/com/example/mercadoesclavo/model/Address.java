package com.example.mercadoesclavo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Address implements Serializable{

    @SerializedName("state_name")
    private String stateName;
    @SerializedName("city_name")
    private String cityName;

    public Address(String stateName, String cityName) {
        this.stateName = stateName;
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
