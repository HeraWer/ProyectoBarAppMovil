package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class ListaCategorias extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categorias);

        getSupportActionBar().setTitle("Bar Reinolds");

        ArrayList<String> categorias = listaCategorias();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, categorias);

        listView = (ListView) findViewById(R.id.lista);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaCategorias.this, ListaProductos.class);
                intent.putExtra("Categoria", listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }

    private ArrayList<String> listaCategorias() {
        ArrayList<String> categorias = new ArrayList<String>();
        categorias.clear();
        categorias.add("Bebidas");
        categorias.add("Carnes");
        categorias.add("Postres");
        return categorias;
    }

}
