package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaCategorias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> categorias = listaCategorias();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categorias);
        ListView listView = (ListView) findViewById(R.id.lista);
        listView.setAdapter(itemsAdapter);
    }

    private ArrayList<String> listaCategorias() {
        ArrayList<String> categorias =  new ArrayList<String>();
        categorias.add("Bebidas");
        categorias.add("Carnes");
        categorias.add("Postres");
        return categorias;
    }
}
