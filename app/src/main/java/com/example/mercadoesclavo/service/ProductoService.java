package com.example.mercadoesclavo.service;

import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.model.ProductosConteiner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductoService {

    @GET("sites/MLA/search")
    Call<ProductosConteiner> getProductos(@Query("q") String busqueda);

    @GET("items/{id}")
    Call<Productos> getProductosPorId(@Path("id") String id);

    @GET("items/{id}/descriptions")
    Call<ArrayList<Productos>> getDescriptionsPorId(@Path("id") String id);

}
