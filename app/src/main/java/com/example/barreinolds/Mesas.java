package com.example.barreinolds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.barreinolds.Empleados.totalMesas;

public class Mesas extends AppCompatActivity {

    static int numMesa;
    static ArrayList<String> listaMesas;
    public static ArrayList<Ticket> tickets;
    RecyclerView listView;
    MesasAdapter adapter;
    private String etiqueta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);
        tickets = new ArrayList<Ticket>();

        // Declaracion del adapter para la ListView

        listaMesas = new ArrayList<String>();

        if (totalMesas == 0) {
            totalMesas = 5;
        }

        for (int i = 1; i <= totalMesas; i++) {
            listaMesas.add("Mesa " + i);
        }



        listView = findViewById(R.id.lista_mesas);
        adapter = new MesasAdapter(listaMesas);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
    }
}

