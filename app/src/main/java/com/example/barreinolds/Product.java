package com.example.barreinolds;


import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private String description;
    private String price;
    private int cantidad;
    private String image;
    private static final long serialVersionUID = 2L;

    public Product(int id, String name, String description, String price, int cantidad, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.cantidad = cantidad;
        this.image = image;
    }

    public Product() {
        cantidad = 0;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
