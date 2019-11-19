package com.example.barreinolds;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class EnviarMessage extends AsyncTask<Message, Void, Object> {

    ConnectionClass connection;
    Message message;

    public EnviarMessage(Message message){
        this.message = message;
    }

    protected Object doInBackground(Message... messages) {
        Object o = null;
        try {
            connection = new ConnectionClass();
            o = connection.sendMessage(this.message);
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return o;
    }

    protected void onPostExecute(Object o) {
        if(message.getRequest().equals("NUMMESAS")){
            Empleados.totalMesas = (Integer)o;
        }else if(message.getRequest().equals("CAMAREROS")){
            Empleados.llistaEmpleats = (ArrayList<Camarero>)o;
        }
    }
}
