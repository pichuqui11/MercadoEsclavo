package com.example.mercadoesclavo.controller;

import com.example.mercadoesclavo.dao.ProductoDao;
import com.example.mercadoesclavo.util.ResultListener;

import java.io.ObjectStreamException;

public class ProductoController {

    private ProductoDao productoDao;

    public ProductoController() {
        this.productoDao = new ProductoDao();

    }


    public void getProductos(final ResultListener<Object> resultListenerFromView){
        productoDao.getProducto(new ResultListener<Object>() {
            @Override
            public void onFinish(Object result) {
                resultListenerFromView.onFinish(result);
            }
        });

    }
}
