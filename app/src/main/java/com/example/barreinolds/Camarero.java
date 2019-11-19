package com.example.barreinolds;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Esta es la clase que define a los camareros.
 */
public class Camarero implements Serializable {

    // Atributos de la clase
    private int id;
    private String nombre;
    private String username;
    private ArrayList<Integer> mesas;
    private static final long serialVersionUID = 3L;

    /*
     * Constructor vacío.
     */
    public Camarero() {
        mesas = new ArrayList<Integer>();
    }

    /*
     * Constructor en el que se le pasa el nombre y el numero
     * de mesa y se le asigna a sus atributos.
     */
    public Camarero(int id, String nombre, String username, ArrayList<Integer> mesas) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.mesas = mesas;
    }

    /*
     * Getters y setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getMesas() {
        return mesas;
    }

    public void setMesas(ArrayList<Integer> mesas) {
        this.mesas = mesas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}