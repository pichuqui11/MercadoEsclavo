package com.example.mercadoesclavo.dao;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.Productos;

import java.util.ArrayList;
import java.util.List;

public abstract class ProveedorDeProductos {

    public static List<Productos> getProductos(){
        List<Productos> productosList = new ArrayList<>();

        productosList.add(new Productos("Atari 2600", R.drawable.producto_atari2600,3000.00));
        productosList.add(new Productos("Muñeco de Caballero del Zodiaco",R.drawable.producto_caballero, 560.46));
        productosList.add(new Productos("Cacerola Essen",R.drawable.producto_cacerola, 1600.00));
        productosList.add(new Productos("Tiki Taka", R.drawable.producto_tikitaka,200.00));
        productosList.add(new Productos("PC última generación",R.drawable.producto_compac,5000.00));
        productosList.add(new Productos("Llavero con movimiento",R.drawable.producto_llavero,500.00));
        productosList.add(new Productos("Tablet Huawei",R.drawable.producto_tablethuawei,1200.00));
        productosList.add(new Productos("Tamagochi",R.drawable.producto_tamagochi,3540.00));
        productosList.add(new Productos("Motorola V3",R.drawable.producto_v3,2530.00));


        return productosList;
    }

}
