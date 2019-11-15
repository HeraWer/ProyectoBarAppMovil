package com.example.barreinolds;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

public class EnviarTicket extends AsyncTask<Ticket, Void, Void> {

    ConnectionClass connection;
    Ticket ticket;

    @Override
    protected Void doInBackground(Ticket... tickets) {
        try {
            connection = new ConnectionClass();
            connection.sendTicket(ticket);
        } catch (IOException e) {
        }
        return null;
    }

}
