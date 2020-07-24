package com.example.mercadoesclavo.dao;

import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.model.ProductosConteiner;
import com.example.mercadoesclavo.service.ProductoService;
import com.example.mercadoesclavo.util.ResultListener;
import com.example.mercadoesclavo.view.DetailFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoDao extends RetrofitDao {

    private ProductoService productoService;


    public ProductoDao() {
        productoService = retrofit.create(ProductoService.class);
    }

    public void getProducto(final ResultListener<List<Productos>> resultListenerFromController, Integer offset) {

        Call<ProductosConteiner> call = this.productoService.getProductos("Esclavo", offset);
        call.enqueue(new Callback<ProductosConteiner>() {
            @Override
            public void onResponse(Call<ProductosConteiner> call, Response<ProductosConteiner> response) {
                if (response.isSuccessful()) {
                    ProductosConteiner productosConteiner = response.body();
                    resultListenerFromController.onFinish(productosConteiner.getProductosList());

                } else {
                    response.errorBody();
                }

            }

            @Override
            public void onFailure(Call<ProductosConteiner> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getDesciptionsPorId (String id, final ResultListener<ArrayList<Productos>> resultListenerFronController){
        Call<ArrayList<Productos>> call = this.productoService.getDescriptionsPorId(id);
        call.enqueue(new Callback<ArrayList<Productos>>() {
            @Override
            public void onResponse(Call<ArrayList<Productos>> call, Response<ArrayList<Productos>> response) {
                if (response.isSuccessful()){
                    ArrayList<Productos> descriptions = response.body();
                    resultListenerFronController.onFinish(descriptions);
                }else{
                    response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Productos>> call, Throwable t) {
            t.printStackTrace();
            }
        });
    }

    public void getProductosPorId (String id, final ResultListener<Productos> resultListenerFronController){
        Call<Productos> call = this.productoService.getProductosPorId(id);
        call.enqueue(new Callback<Productos>() {
            @Override
            public void onResponse(Call<Productos> call, Response<Productos> response) {
                if (response.isSuccessful()){
                    Productos productos = response.body();
                    resultListenerFronController.onFinish(productos);
                }else{
                    response.errorBody();
            }
            }

            @Override
            public void onFailure(Call<Productos> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

}