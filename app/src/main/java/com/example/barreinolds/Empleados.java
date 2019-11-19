package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Empleados extends AppCompatActivity {

    static ArrayList<Camarero> listaEmpleados;
    static int totalMesas;

    EmpleadosAdapter adapter;
    RecyclerView listView;
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

        /*ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listaEmpleados);*/

        listView = findViewById(R.id.lista_empleados);
        adapter = new EmpleadosAdapter(listaEmpleados);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));


    }

}
