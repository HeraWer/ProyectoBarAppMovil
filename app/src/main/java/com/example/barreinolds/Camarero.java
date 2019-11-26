package com.example.barreinolds;

import java.io.Serializable;
/*
 * Esta es la clase que define a los camareros.
 */
public class Camarero implements Serializable {

    // Atributos de la clase
    private int id;
    private String nombre;
    private String username;
    private String password;
    private static final long serialVersionUID = 3L;
    /*
     * Constructor vacío.
     */

    /*
     * Constructor en el que se le pasa el nombre y el numero
     * de mesa y se le asigna a sus atributos.
     */
    public Camarero(int id, String nombre, String username) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}