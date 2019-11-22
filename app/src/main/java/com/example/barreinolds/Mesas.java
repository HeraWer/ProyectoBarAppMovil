package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.ArrayList;

import static com.example.barreinolds.Empleados.totalMesas;

public class Mesas extends AppCompatActivity {

    static int numMesa;
    static ArrayList<String> listaMesas;
    public static ArrayList<Ticket> tickets;
    ArrayList<String> listNumMesa;
    RecyclerView listView;
    MesasAdapter adapter;
    private String etiqueta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);
        if(tickets == null){
            tickets = new ArrayList<Ticket>();
        }

        // Declaracion del adapter para la ListView

        listaMesas = new ArrayList<String>();
        listNumMesa = new ArrayList<String>();

        if (totalMesas == 0) {
            totalMesas = 5;
        }

        for (int i = 1; i <= totalMesas; i++) {
            listaMesas.add("Mesa " + i);
            listNumMesa.add(String.valueOf(i));
        }




        listView = findViewById(R.id.lista_mesas);
        adapter = new MesasAdapter(listNumMesa);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
    }
}

