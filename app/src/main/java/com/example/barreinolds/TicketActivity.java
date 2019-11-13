package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class TicketActivity extends AppCompatActivity {

    TextView ticketTitle;
    RecyclerView productosRecyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        ticketTitle = findViewById(R.id.TicketTitle);
        ticketTitle.setText("Pedido mesa " + Mesas.numMesa);

        productosRecyclerView = findViewById(R.id.ProductRecyclerView);
        TicketProductsAdaper adapter = new TicketProductsAdaper(getTicket(Mesas.numMesa).getProductosComanda());
        productosRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        productosRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public Ticket getTicket(int numMesa){
        for(Ticket t : Mesas.tickets){
            if(t.getMesa() == numMesa){
                return t;
            }
        }
        return null;
    }


}
