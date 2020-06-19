package com.example.mercadoesclavo.controller;

import com.example.mercadoesclavo.dao.ProductoDao;
import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class ProductoController {

    private ProductoDao productoDao;


    public ProductoController() {
        this.productoDao = new ProductoDao();


    }


    public void getProductos(final ResultListener<List<Productos>> resultListenerFromView){
        productoDao.getProducto(new ResultListener<List<Productos>>() {
            @Override
            public void onFinish(List<Productos> result) {
                resultListenerFromView.onFinish(result);
            }

        });

    }


    public void getDescriptionsPorId(String id, final ResultListener<ArrayList<Productos>> resultListenerFromView){
        productoDao.getDesciptionsPorId(id, new ResultListener<ArrayList<Productos>>(){
            @Override
            public void onFinish(ArrayList<Productos> result) {
                resultListenerFromView.onFinish(result);

            }
        });
    }


    public void getProductosPorId(String id, final ResultListener<Productos> resultListenerFromView){
        productoDao.getProductosPorId(id, new ResultListener<Productos>() {
            @Override
            public void onFinish(Productos result) {
                resultListenerFromView.onFinish(result);
            }
        });
    }


}
