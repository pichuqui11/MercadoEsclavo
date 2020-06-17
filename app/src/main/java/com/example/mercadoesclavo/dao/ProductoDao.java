package com.example.mercadoesclavo.dao;

import com.example.mercadoesclavo.service.ProductoService;
import com.example.mercadoesclavo.util.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoDao extends RetrofitDao {

    private ProductoService productoService;

    public ProductoDao() {
        productoService = retrofit.create(ProductoService.class);
    }

    public void getProducto(ResultListener<Object> resultListenerFromController){

        Call<Object> call = this.productoService.getProductos("Novedades");
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object body = response.body();

                } else {
                    response.errorBody();
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
