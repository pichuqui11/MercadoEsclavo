package com.example.mercadoesclavo.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductoService {

    @GET("search")
    Call<Object> getProductos(@Query("q") String busqueda);
}
