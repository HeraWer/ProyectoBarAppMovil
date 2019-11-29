package com.example.barreinolds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.barreinolds.TicketProductsAdaper.productsAdapterArray;

public class TicketActivity extends AppCompatActivity {

    TextView ticketTitle;
    public static TextView totalTicket;
    RecyclerView productosRecyclerView;
    ArrayList<Product> aLProductsTicket;
    TicketProductsAdaper adapter;
    Button enviarPedido;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);


        ticketTitle = findViewById(R.id.TicketTitle);
        ticketTitle.setText("Pedido mesa " + Mesas.numMesa);

        totalTicket = findViewById(R.id.totalPrecioTicket);
        calcularTotal();
        aLProductsTicket = Search.getTicket(Mesas.numMesa).getProductosComanda();
        productosRecyclerView = findViewById(R.id.ProductRecyclerView);
        adapter = new TicketProductsAdaper(aLProductsTicket);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(productosRecyclerView);
        productosRecyclerView.getItemAnimator().setRemoveDuration(800);
        productosRecyclerView.setAdapter(adapter);
        productosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.enviar2);

        enviarPedido = findViewById(R.id.enviarPedido);
        enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(70);
                mp.start();
                sendTicket();
            }
        });

    }

    public static void sendTicket() {
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
                    Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    MediaPlayer mp = MediaPlayer.create(TicketActivity.this, R.raw.restar);
                    mp.start();
                    vb.vibrate(70);
                    productsAdapterArray.remove(viewHolder.getAdapterPosition());
                    adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    calcularTotal();
                    sendTicket();

                }
            };

    public static void calcularTotal() {
        float total = Search.getTotalPrice(Search.getTicket(Mesas.numMesa));
        TicketActivity.totalTicket.setText("Total: " + NumberFormat.round(total) + "\nTotal IVA: " + NumberFormat.round(total + (total * 0.1f)));
    }
}
