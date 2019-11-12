package com.example.barreinolds;

import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable {

    private ArrayList<Product> productosComanda;
    private int mesa;

<<<<<<< HEAD
    public ArrayList<Product> getProductosComanda() {
        return productosComanda;
    }

    public void setProductosComanda(ArrayList<Product> productosComanda) {
        this.productosComanda = productosComanda;
=======
    public Ticket() {
        productosComanda = new ArrayList<Product>();
    }

    public Ticket(ArrayList<Product> productosComanda, int mesa) {
        this.productosComanda = productosComanda;
        this.mesa = mesa;
>>>>>>> 8ffab1503291adbbce9971412dc590fe6b27fa29
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
<<<<<<< HEAD
=======

    public ArrayList<Product> getProductosComanda() {
        return productosComanda;
    }

    public void setProductosComanda(ArrayList<Product> productosComanda) {
        this.productosComanda = productosComanda;
    }
>>>>>>> 8ffab1503291adbbce9971412dc590fe6b27fa29
}
