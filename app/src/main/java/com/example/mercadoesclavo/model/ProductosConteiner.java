package com.example.mercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductosConteiner {

    @SerializedName("results")
    public List<Productos> productosList;

    public ProductosConteiner(){

    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }
}
