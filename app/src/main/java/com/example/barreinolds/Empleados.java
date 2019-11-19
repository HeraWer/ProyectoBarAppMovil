package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Empleados extends AppCompatActivity {

    static String empleado;
    static ArrayList<Camarero> listaEmpleados;
    static int totalMesas;
    ListView listView;
    static Camarero camarero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);

        final CountDownLatch latch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                ConnectionClass connection = null;
                try {
                    connection = new ConnectionClass();
                    Object o;
                    o = connection.sendMessage(new Message("CAMAREROS"));
                    listaEmpleados = (ArrayList<Camarero>) o;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                latch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ConnectionClass connection = null;
                try {
                    connection = new ConnectionClass();
                    Object o;
                    o = connection.sendMessage(new Message("NUMMESAS"));
                    totalMesas = (Integer) o;
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

        if (listaEmpleados == null) {
            listaEmpleados = new ArrayList<Camarero>();
            listaEmpleados.add(new Camarero(1, "David", "dasafe"));
        }

        // Declaracion del adapter para la ListView
        ArrayAdapter<Camarero> itemsAdapter = new ArrayAdapter<Camarero>(this,
                android.R.layout.simple_list_item_1, listaEmpleados);

        listView = (ListView) findViewById(R.id.lista_empleados);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                camarero = listaEmpleados.get(position);
                Intent intent = new Intent(Empleados.this, Mesas.class);
                empleado = listaEmpleados.get(position).getNombre();
                startActivity(intent);
            }
        });
    }
}
