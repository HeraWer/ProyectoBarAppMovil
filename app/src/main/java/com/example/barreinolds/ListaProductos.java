package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaProductos extends AppCompatActivity {

    ImageView imgProducto;
    ListView listView;
    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        titulo = findViewById(R.id.nombre_productos);
        getSupportActionBar().setTitle("Bar Reinolds");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            titulo.setText(bundle.getString("Categoria"));
            ArrayList<String> categorias = listaProductos(bundle.getString("Categoria"));
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, categorias);
            listView = (ListView) findViewById(R.id.listaM);
            listView.setAdapter(itemsAdapter);
        }

    }

    private ArrayList<String> listaProductos(String categoria) {
        ArrayList<String> productos = new ArrayList<String>();
        productos.clear();
        if (categoria.equalsIgnoreCase("postres")) {
            productos.add("Cafe");
            productos.add("Pastel");
            productos.add("Helado");
        } else if (categoria.equalsIgnoreCase("bebidas")) {
            productos.add("Cocacola");
            productos.add("Fanta");
            productos.add("Vino");
        }
        return productos;
    }
}
