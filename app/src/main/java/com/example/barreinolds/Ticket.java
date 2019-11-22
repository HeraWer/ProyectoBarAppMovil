package com.example.barreinolds;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Product> productosComanda;
    private int mesa;
    private Camarero camarero;
    private Timestamp datetime;

    public Ticket() {
        productosComanda = new ArrayList<Product>();
    }

    public Ticket(ArrayList<Product> productosComanda, int mesa) {
        this.productosComanda = productosComanda;
        this.mesa = mesa;
    }

    public Ticket(int mesa) {
        productosComanda = new ArrayList<Product>();
        this.mesa = mesa;
        this.camarero = Empleados.camarero;
        this.datetime = new Timestamp(System.currentTimeMillis());
    }

    public Ticket(ArrayList<Product> productosComanda, int mesa, Camarero camarero, Timestamp datetime) {
        super();
        this.productosComanda = productosComanda;
        this.mesa = mesa;
        this.camarero = camarero;
        this.datetime = datetime;
    }

    public Ticket(int mesa, Camarero camarero, Timestamp datetime) {
        super();
        this.mesa = mesa;
        this.camarero = camarero;
        this.datetime = datetime;
        this.productosComanda = new ArrayList<Product>();
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
