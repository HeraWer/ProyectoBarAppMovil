package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class ListaCategorias extends AppCompatActivity {

    /*
    Objetos necesarios de la clase
     */
    RecyclerView recyclerView;
    static ArrayList<Category> categorias;
    ListaCategoriasAdapter adapter;
    ImageButton b;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categorias);

        // Llamamos al metodo que gestiona el boton de ver comanda
        manageTicketButton();

        // Llamamos al metodo que recupera categorias
        recuperarCategorias();

        // Gestionamos el RecyclerView
        recyclerView = findViewById(R.id.lista_categorias);
        adapter = new ListaCategoriasAdapter((categorias));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }

    /*
    Este metodo busca el boton de ver comanda y le añade un onClickListener
     */
    public void manageTicketButton(){
        b = findViewById(R.id.verComandaButton);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Si existe un ticket en la mesa actual, hacemos un intent a la TicketActivity
                if (Search.lookForTicketTable(Mesas.numMesa)) {
                    Intent i = new Intent(ListaCategorias.this, TicketActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    /*
    Método que ejecuta la conexion para recuperar las categorias del server.
    Para mas informacion de como funciona, mirar los comentarios de metodos
    parecidos en la clase Empleados.
     */
    public void recuperarCategorias(){

        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                ConnectionClass connection = null;
                try {
                    connection = new ConnectionClass();
                    Object o;
                    o = connection.sendMessage(new Message("CATEGORIAS"));
                    categorias = (ArrayList<Category>) o;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}