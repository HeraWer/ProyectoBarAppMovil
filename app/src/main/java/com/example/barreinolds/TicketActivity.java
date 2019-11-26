package com.example.barreinolds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import static com.example.barreinolds.TicketProductsAdaper.productsAdapterArray;

public class TicketActivity extends AppCompatActivity {

    TextView ticketTitle;
    RecyclerView productosRecyclerView;
    TicketProductsAdaper adapter;
    Button enviarPedido;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        ticketTitle = findViewById(R.id.TicketTitle);
        ticketTitle.setText("Pedido mesa " + Mesas.numMesa);

        productosRecyclerView = findViewById(R.id.ProductRecyclerView);
        adapter = new TicketProductsAdaper(Search.getTicket(Mesas.numMesa).getProductosComanda());
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(productosRecyclerView);
        productosRecyclerView.getItemAnimator().setRemoveDuration(800);
        productosRecyclerView.setAdapter(adapter);
        productosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        enviarPedido = findViewById(R.id.enviarPedido);
        enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTicket();
            }
        });

    }

    public void sendTicket(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Creamos el objeto que nos conecta al server
                ConnectionClass connection = null;
                try {
                    // Lo inicializamos
                    connection = new ConnectionClass();
                    connection.sendTicket(Search.getTicket(Mesas.numMesa));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    productsAdapterArray.remove(viewHolder.getAdapterPosition());
                    adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    sendTicket();

                }
            };
}
