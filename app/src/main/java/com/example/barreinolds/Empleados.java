package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/*
Esta es la Activity que muestra la lista de empleados disponibles
 */
public class Empleados extends AppCompatActivity {

    /*
    Objetos y variables que se utilizaran a lo largo de la app y se
    crean en este punto de la ejecución
     */
    static ArrayList<Camarero> listaEmpleados;
    static int totalMesas;
    static Camarero camarero;
    Button buttonR;
    // Adapter del RecyclerView
    EmpleadosAdapter adapter;

    // Objeto RecyclerView
    RecyclerView recyclerView;

    /*
    Metodo onCreate de la activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);



        // Si no podemos recuperar del server el array de camareros.
        // Inicializamos el array con un valor por defecto
//        if (listaEmpleados == null) {
//            listaEmpleados = new ArrayList<Camarero>();
//            listaEmpleados.add(new Camarero(1, "David", "dasafe", null));
//        }



        // Boton para registrar usuario
        buttonR = findViewById(R.id.buttonRegister);
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(intent);

            }
        });

        /*if(!ConnectionClass.socket.isConnected())
            Toast.makeText(Empleados.this, "Conexión rechazada", Toast.LENGTH_LONG).show();*/
    }

    /*
    Metodo que ejecuta el Thread y hace las llamadas a la clase
    ConnectionClass para recuperar la lista de camareros
     */
    public void recuperarCamareros(){
        /*
        El objeto CountDownLatch, nos permite hacer una parada en ejecucion
        hasta que no llegue a 0 su valor, que se gestiona a través de código
        en el thread.

        De esta manera podemos esperar para no ejecutar más código hasta no
        tener los valores de los objetos que le pedimos al server
         */
        final CountDownLatch latch = new CountDownLatch(1);

        // Creamos un Thread para gestionar acciones de conexión
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Creamos el objeto que nos conecta al server
                ConnectionClass connection = null;
                try {
                    // Lo inicializamos
                    connection = new ConnectionClass();

                    // Creamos el objeto que recuperaremos del server
                    Object o;
                    // Dentro del objeto introducimos el retornado por el server
                    // Le enviamos el mensaje el server:
                    o = connection.sendMessage(new Message("CAMAREROS"));
                    // Igualamos la lista de empleados al objeto
                    listaEmpleados = (ArrayList<Camarero>) o;


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    // Descontamos uno en el latch
                    latch.countDown();
                }


            }
        }).start();
        try {
            // Le decimos al programa que espere hasta que el latch no haya llegado a 0
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    Metodo que ejecuta el Thread y hace las llamadas a la clase
    ConnectionClass para recuperar el número total de mesas
     */
    public void recuperarMesas(){
        /*
        El objeto CountDownLatch, nos permite hacer una parada en ejecucion
        hasta que no llegue a 0 su valor, que se gestiona a través de código
        en el thread.

        De esta manera podemos esperar para no ejecutar más código hasta no
        tener los valores de los objetos que le pedimos al server
         */
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Creamos el objeto que nos conecta al server
                ConnectionClass connection = null;
                try {
                    // Lo inicializamos
                    connection = new ConnectionClass();

                    // Creamos el objeto que recuperaremos del server
                    Object o;

    // Dentro del objeto introducimos el retornado por el server
    // Le enviamos el mensaje el server:
    o = connection.sendMessage(new Message("NUMMESAS"));
    // Igualamos el total de mesas al objeto
    totalMesas = (Integer) o;

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    // Descontamos uno en el latch
                    latch.countDown();
                }

            }
        }).start();
        try {
            // Le decimos al programa que espere hasta que el latch no haya llegado a 0
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Llamamos a los metodos de esta clase que recuperan camareros y mesas:
        recuperarCamareros();
        recuperarMesas();

        // Gestionamos el recyclerView
        recyclerView = findViewById(R.id.lista_empleados);
        adapter = new EmpleadosAdapter(listaEmpleados);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
