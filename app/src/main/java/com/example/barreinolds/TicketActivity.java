package com.example.barreinolds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

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
        adapter = new TicketProductsAdaper(getTicket(Mesas.numMesa).getProductosComanda());
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(productosRecyclerView);
        productosRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        productosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        enviarPedido = findViewById(R.id.enviarPedido);
        enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EnviarTicket().execute(getTicket(Mesas.numMesa));
            }
        });

    }

    public Ticket getTicket(int numMesa) {
        for (Ticket t : Mesas.tickets) {
            if (t.getMesa() == numMesa) {
                return t;
            }
        }
        return null;
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
                    new EnviarTicket().execute(getTicket(Mesas.numMesa));
                }
            };
}
