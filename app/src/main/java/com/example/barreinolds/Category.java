package com.example.barreinolds;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {

    private String id;
    private String nCategory;
    private ArrayList<Product> listProducts;

    public Category(){

    }

    public Category(String id, String nCategory) {
        this.id = id;
        this.nCategory = nCategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getnCategory() {
        return nCategory;
    }

    public void setnCategory(String nCategory) {
        this.nCategory = nCategory;
    }

    public ArrayList<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(ArrayList<Product> listProducts) {
        this.listProducts = listProducts;
    }
}
