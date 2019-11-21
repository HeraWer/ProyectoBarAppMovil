package com.example.barreinolds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;

import static com.example.barreinolds.Empleados.camarero;
import static com.example.barreinolds.Mesas.numMesa;

public class ListaProductos extends AppCompatActivity {

    ListView listView;
    RecyclerView listaProductosRecyclerView;
    TextView titulo;
    public static ArrayList<Product> productos;
    ListaProductosAdapter listaProductosAdapter;
    public Ticket ticket;
    Pedido p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        ticket = getTicket(numMesa);
        if (ticket == null) {
            ticket = new Ticket(numMesa, camarero, new Timestamp(System.currentTimeMillis()));
            Mesas.tickets.add(ticket);
        }
        ticket.setMesa(numMesa);

        titulo = findViewById(R.id.nombre_producto);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            titulo.setText(bundle.getString("Categoria"));
//            productos = lp;
            listaProductosRecyclerView = findViewById(R.id.lista_producto);

            listaProductosAdapter= new ListaProductosAdapter(productos);
            listaProductosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            listaProductosRecyclerView.setAdapter(listaProductosAdapter);
        } else {
            Toast.makeText(ListaProductos.this, "Lista vacia", Toast.LENGTH_LONG).show();
        }

    }

    protected void onResume() {
        super.onResume();
        ticket = getTicket(numMesa);
        boolean encontrado;
        for (Product catProduct : productos) {
            encontrado = false;
            for (Product ticketProduct : ticket.getProductosComanda()) {
                if (ticketProduct.getId() == catProduct.getId()) {
                    encontrado = true;
                    break;
                }
            }
            if (catProduct.getCantidad() > 0 && encontrado == false) {
                catProduct.setCantidad(0);
            }
        }
        if (ticket == null) {
            ticket = new Ticket(numMesa, camarero, new Timestamp(System.currentTimeMillis()));
            Mesas.tickets.add(ticket);
        }
    }

    public Ticket getTicket(int numMesa) {
        for (Ticket t : Mesas.tickets) {
            if (t.getMesa() == numMesa) {
                return t;
            }
        }
        return null;
    }

}