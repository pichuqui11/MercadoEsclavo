package com.example.mercadoesclavo.dao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDao {

    private static final String URL_BASE ="https://api.mercadolibre.com/sites/MLA/";
    protected Retrofit retrofit;

    public RetrofitDao() {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



    }
}
