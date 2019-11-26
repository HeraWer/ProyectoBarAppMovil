package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;

import static com.example.barreinolds.Empleados.camarero;
import static com.example.barreinolds.Mesas.numMesa;

public class ListaProductos extends AppCompatActivity {

    RecyclerView listaProductosRecyclerView;
    TextView titulo;
    public static ArrayList<Product> productos;
    ListaProductosAdapter listaProductosAdapter;
    public Ticket ticket;

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