package com.example.barreinolds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

import java.io.IOException;
import java.util.ArrayList;

import static com.example.barreinolds.ListaCategorias.lp;
import static com.example.barreinolds.Mesas.numMesa;

public class ListaProductos extends AppCompatActivity {

    ListView listView;
    TextView titulo;
    ArrayList<Product> productos;
    Ticket ticket;
    ConnectionClass connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        try {
            connection = new ConnectionClass();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ticket = new Ticket();
        ticket.setMesa(numMesa);

        titulo = findViewById(R.id.nombre_producto);
        getSupportActionBar().setTitle("Bar Reinolds");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            titulo.setText(bundle.getString("Categoria"));
            productos = lp;

            CustomAdapter customAdapter = new CustomAdapter(this, productos);
            listView = (ListView) findViewById(R.id.lista_producto);
            listView.setAdapter(customAdapter);
        } else {
            Toast.makeText(ListaProductos.this, "Lista vacia", Toast.LENGTH_LONG).show();
        }

    }

    class CustomAdapter extends ArrayAdapter<Product> {


        public CustomAdapter(@NonNull Context context, ArrayList<Product> resource) {
            super(context, 0, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Product producto = getItem(position);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.customlayout_productos, parent, false);

                ImageView imgProducto = (ImageView) convertView.findViewById(R.id.imagen_producto);
                TextView nombreProducto = convertView.findViewById(R.id.nombre_producto);
                TextView precioProducto = convertView.findViewById(R.id.precio_producto);
                Button restarProducto = convertView.findViewById(R.id.boton_resta);
                Button sumarProducto = convertView.findViewById(R.id.boton_suma);
                final TextView cantidadProducto = convertView.findViewById(R.id.cantidad_producto);

                restarProducto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (producto.getCantidad() > 0) {
                            producto.setCantidad(producto.getCantidad() - 1);
                            cantidadProducto.setText(String.valueOf(producto.getCantidad()));
                            if (producto.getCantidad() <= 0) {
                                ticket.getProductosComanda().remove(producto);
                            }
                            try {
                                connection.sendTicket(ticket);
                            } catch (IOException e) {
                                Toast.makeText(ListaProductos.this, "Conexion rechazada", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

                sumarProducto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        producto.setCantidad(producto.getCantidad() + 1);
                        cantidadProducto.setText(String.valueOf(producto.getCantidad()));
                        if (ticket.getProductosComanda().contains(producto)) {
                            ticket.getProductosComanda().set(ticket.getProductosComanda().indexOf(producto), producto);
                        } else {
                            ticket.getProductosComanda().add(producto);
                        }
                        try {
                            connection.sendTicket(ticket);
                        } catch (IOException e) {
                            Toast.makeText(ListaProductos.this, "Conexion rechazada", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                String uri = producto.getImage();
                int imageResource = getResources().getIdentifier(uri, "drawable", getPackageName());
                Drawable imagenDra = ContextCompat.getDrawable(getContext(), imageResource);

                imgProducto.setImageDrawable(imagenDra);
                nombreProducto.setText(producto.getName());
                precioProducto.setText(producto.getPrice() + "€");
            }
            return convertView;
        }
    }
}