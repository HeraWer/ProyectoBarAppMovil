package com.example.barreinolds;

import java.io.InputStream;
import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private String description;
    private String price;
    private int cantidad;
    private String image_desktop;
    private String image_movil;
    private boolean served;
    private InputStream imgBlob;
    private static final long serialVersionUID = 2L;


    public Product(int id, String name, String description, String price, int cantidad, String image_desktop, String image_movil) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.cantidad = cantidad;
        this.image_desktop = image_desktop;
        this.image_movil = image_movil;
        this.served = false;
    }

    public Product() {
        cantidad = 0;
        served = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getImage_movil() {
        return image_movil;
    }

    public void setImage_movil(String image) {
        this.image_movil = image_movil;
    }

    public String getImage_desktop() {
        return image_desktop;
    }

    public void setImage_desktop(String image_desktop) {
        this.image_desktop = image_desktop;
    }

    public boolean isServed() { return served; }

    public void setServed(boolean served) { this.served = served; }

    public InputStream getImgBlob() {
        return imgBlob;
    }

    public void setImgBlob(InputStream imgBlob) {
        this.imgBlob = imgBlob;
    }
}
