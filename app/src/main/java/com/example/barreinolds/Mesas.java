package com.example.barreinolds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.ArrayList;

public class Mesas extends AppCompatActivity {

    static int numMesa;
    static ArrayList<String> listaMesas;
    public static ArrayList<Ticket> tickets;
    RecyclerView listView;
    MesasAdapter adapter;
    private String etiqueta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);
        tickets = new ArrayList<Ticket>();

        // Aqui llamo al metodo para leer el XML con el Try-Catch
        try {
            leerConfigMesas();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        listView = findViewById(R.id.lista_mesas);
        adapter = new MesasAdapter(listaMesas);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
    }


    // Metodo leer XML config para mesas
    public void leerConfigMesas() throws IOException, XmlPullParserException {
        // Parseo el xml para poder tener el puntero seleccionandolo
        XmlPullParser xrp = getResources().getXml(R.xml.config);
        // xrp.next para avanzar una etiqueta en el xml
        xrp.next();
        // Genera un evento por etiqueta
        int event = xrp.getEventType();
        // Bucle que no deja de recorrerlo hasta que no llegue al final del document xml
        while (event != XmlPullParser.END_DOCUMENT) {
            // Guardo las las etiquetas que se va encontrando
            etiqueta = xrp.getName();
            // Genero una condicion con null porque necesita considerarlas, porque el espacio entre etiqueta y etiqueta el xrp.getName es null
           if (etiqueta != null) {
               // Cuando etiqueta sea cantidad entra
                if(etiqueta.equals("cantidad")) {
                    String numeroMesas = xrp.nextText();
                    // Genero un arraylist cada vez que miro el xml a si de reinicia
                    listaMesas = new ArrayList<String>();
                    int contador = Integer.parseInt(numeroMesas);
                    // For para tener la cantidad de mesas en una lista
                    for (int i = 1; i <= contador; i++) {
                        listaMesas.add("Mesa " + i);
                    }
                }
            }
           // Cada vez que acaba avanza una etiqueta.
            event = xrp.next();
        }
    }
}

