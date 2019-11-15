package com.example.barreinolds;

import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Product> productosComanda;
    private int mesa;

    public Ticket() {
        productosComanda = new ArrayList<Product>();
    }

    public Ticket(ArrayList<Product> productosComanda, int mesa) {
        this.productosComanda = productosComanda;
        this.mesa = mesa;
    }

    public int getMesa() {

        return mesa;
    }

    public void setMesa(int mesa) {

        this.mesa = mesa;
    }

    public ArrayList<Product> getProductosComanda() {

        return productosComanda;
    }

    public void setProductosComanda(ArrayList<Product> productosComanda) {
        this.productosComanda = productosComanda;
    }
}
