package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    ListView listView;
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

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listaMesas);

        listView = (ListView) findViewById(R.id.lista_mesas);
        listView.setAdapter(itemsAdapter);
        Bundle bundle = getIntent().getExtras();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ListaCategorias.class);
                numMesa = Integer.parseInt(listaMesas.get(position).split(" ")[1]);
                startActivity(intent);
            }
        });
    }
}

