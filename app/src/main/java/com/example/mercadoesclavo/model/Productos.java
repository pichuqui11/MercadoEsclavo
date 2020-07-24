package com.example.mercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Productos implements Serializable {

    private String nombre;
    private Integer imagen;
    private Double precio;



    //DESDE MERCADO PARA RECYCLE
    private String id;
    private String title;
    private Double price;
    private String thumbnail;
    private String stateName;
    private String cityName;
    @SerializedName("address")
    private Address address;
    @SerializedName("plain_text")
    private String plainText;
    @SerializedName("start_time")
    private Date startTime;
    @SerializedName("sold_quantity")
    private Integer soldQuantity;
    @SerializedName("pictures")
    private ArrayList<Pictures> picturelist;
    private Boolean esFav;

    public Boolean getEsFav() {
        return esFav;
    }

    public void setEsFav(Boolean esFav) {
        this.esFav = esFav;
    }

    public Productos(String id, String title, Double price, String thumbnail, Address address, String plainText, Date startTime, Integer soldQuantity, ArrayList<Pictures> picturelist, Boolean esFav) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
        this.address = address;
        this.plainText = plainText ;
        this.startTime = startTime;
        this.soldQuantity = soldQuantity;
        this.picturelist = picturelist;
        this.esFav = esFav;
    }

    public ArrayList<Pictures> getPictures() {
        return picturelist;
    }

    public void setPicturelist(ArrayList<Pictures> picturelist) {
        this.picturelist = picturelist;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String description) {
        this.plainText = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
