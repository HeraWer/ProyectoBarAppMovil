package com.example.barreinolds;

import java.util.ArrayList;

public class Ticket {

    private ArrayList<Product> productosComanda;
    private int mesa;

    public ArrayList<Product> getProductosComanda() {
        return productosComanda;
    }

    public void setProductosComanda(ArrayList<Product> productosComanda) {
        this.productosComanda = productosComanda;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
}
