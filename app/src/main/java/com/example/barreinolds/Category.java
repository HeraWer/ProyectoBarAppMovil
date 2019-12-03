package com.example.barreinolds;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*
Esta es la clase que define a las categorias.
Implementa la clase Serializable para poderse enviar entre
Server y cliente
 */
public class Category implements Serializable {

    /*
    Atributos de clase
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String nCategory;
    private ArrayList<Product> listProducts;
    private byte[] imgBlob;

    /*
    Constructor vacio en el que se inicializa el array de productos
     */
    public Category(){
        ArrayList<Product> listProducts = new ArrayList<Product>();
    }

    /*
    Constructor donde se rellenan los campos y se inicializa el array de productos
     */

    public Category(int id, String nCategory) {
        this.id = id;
        this.nCategory = nCategory;
        listProducts = new ArrayList<Product>();
    }

    /*
    Getters y Setters
     */
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

    public void setImgBlob(byte[] imgBlob){
        this.imgBlob = imgBlob;
    }

    public byte[] getImgBlob(){
        return this.imgBlob;
    }
}
