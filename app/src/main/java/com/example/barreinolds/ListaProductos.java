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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.barreinolds.ListaCategorias.lp;

public class ListaProductos extends AppCompatActivity {

    ListView listView;
    TextView titulo;
    ArrayList<Product> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        titulo = findViewById(R.id.nombre_productos);
        getSupportActionBar().setTitle("Bar Reinolds");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            titulo.setText(bundle.getString("Categoria"));
            productos = lp;
//            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
//                    android.R.layout.simple_list_item_1, categorias);

            CustomAdapter customAdapter = new CustomAdapter(this, productos);
            listView = (ListView) findViewById(R.id.lista_productos);
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
            Product producto = getItem(position);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.customlayout_productos, parent, false);

                ImageView imgProducto = (ImageView) convertView.findViewById(R.id.imagen_producto);
                TextView nombreProducto = convertView.findViewById(R.id.nombre_producto);
                TextView precioProducto = convertView.findViewById(R.id.precio_producto);

                String uri = producto.getImage();
                int imageResource = getResources().getIdentifier(uri, "drawable", getPackageName());
                Drawable imagenDra = ContextCompat.getDrawable(getContext(), imageResource);

                imgProducto.setImageDrawable(imagenDra);
//                imgProducto.setImageResource(imageResource);
                nombreProducto.setText(producto.getName());
                precioProducto.setText(producto.getPrice() + "€");
            }
            return convertView;
        }
    }
}
