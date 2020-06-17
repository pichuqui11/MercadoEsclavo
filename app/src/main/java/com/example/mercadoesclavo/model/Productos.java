package com.example.mercadoesclavo.model;

import java.io.Serializable;

public class Productos implements Serializable {

    private String nombre;
    private Integer imagen;
    private Double precio;


    //DESDE MERCADO PARA RECYCLE
    private String id;
    private String title;
    private Double price;
    private String thumbnail;
    private String state_name;
    private String city_name;

    public Productos(String id, String title, Double price, String thumbnail, String state_name, String city_name) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
        this.state_name = state_name;
        this.city_name = city_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    //SIN INTERNET

    public Productos() {
    }

    public Productos(String nombre, Integer imagen, Double precio) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
