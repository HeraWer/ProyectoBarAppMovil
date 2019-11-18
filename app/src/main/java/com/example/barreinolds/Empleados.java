package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class Empleados extends AppCompatActivity {

    static String empleado;
    ArrayList<String> listaEmpleados;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);

        listaEmpleados = new ArrayList<String>();

        listaEmpleados.add("David");
        listaEmpleados.add("David");
        listaEmpleados.add("David");
        listaEmpleados.add("David");
        listaEmpleados.add("David");

        // Declaracion del adapter para la ListView
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listaEmpleados);

        listView = (ListView) findViewById(R.id.lista_empleados);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Empleados.this, Mesas.class);
                empleado = listaEmpleados.get(position);
                startActivity(intent);
            }
        });
    }
}
