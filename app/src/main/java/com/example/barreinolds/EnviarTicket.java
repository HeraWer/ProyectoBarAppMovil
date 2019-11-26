package com.example.barreinolds;

import android.os.AsyncTask;

import java.io.IOException;

/*
Clase asincrona que envia el ticket al server
 */
public class EnviarTicket extends AsyncTask<Ticket, Void, Void> {

    /*
    Atributo de la clase instancia de la ConnectionClass
     */
    ConnectionClass connection;

    @Override
    protected Void doInBackground(Ticket... tickets) {
//        try {
//            // Inicializamos conexion
//            //connection = new ConnectionClass();
//
//            // Enviamos el ticket
//            //connection.sendTicket(tickets[0]);
//        } catch (IOException e) {
//
//        }
        return null;
    }

}
