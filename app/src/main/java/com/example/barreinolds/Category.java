package com.example.barreinolds;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String nCategory;
    private ArrayList<Product> listProducts;

    public Category(){
        ArrayList<Product> listProducts = new ArrayList<Product>();
    }

    public Category(int id, String nCategory) {
        this.id = id;
        this.nCategory = nCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
