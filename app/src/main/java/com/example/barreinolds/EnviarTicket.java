package com.example.barreinolds;

import android.os.AsyncTask;

import java.io.IOException;

public class EnviarTicket extends AsyncTask<Ticket, Void, Void> {

    ConnectionClass connection;

    @Override
    protected Void doInBackground(Ticket... tickets) {
        try {
            connection = new ConnectionClass();
            connection.sendTicket(tickets[0]);
        } catch (IOException e) {
        }
        return null;
    }

}
