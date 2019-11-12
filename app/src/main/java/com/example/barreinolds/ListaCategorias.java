package com.example.barreinolds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class ListaCategorias extends AppCompatActivity {

    ListView listView;
    ArrayList<Category> categorias;
    ArrayList<Product> productos;
    static ArrayList<Product> lp;
    Category cat;
    Product p;
    Pedido pe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categorias);
        getSupportActionBar().setTitle("Bar Reinolds");
        try {
            leerCatXML();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        pe = new Pedido();
        pe.crearXML(getApplicationContext(), productos);

        CustomAdapter customAdapter = new CustomAdapter(this, categorias);
        listView = (ListView) findViewById(R.id.lista_categorias);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lp = categorias.get(position).getListProducts();
                Intent intent = new Intent(ListaCategorias.this, ListaProductos.class);
                intent.putExtra("Categoria", categorias.get(position).getnCategory());
                startActivity(intent);
            }
        });
    }

    public void leerCatXML() throws IOException, XmlPullParserException {
        XmlPullParser xrp = getResources().getXml(R.xml.productes);
        xrp.next();
        int i = 0;
        int event = xrp.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            String etiqueta = null;
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    categorias = new ArrayList<Category>();
                    break;
                case XmlPullParser.START_TAG:
                    etiqueta = xrp.getName();
                    if (etiqueta.equals("categoria")) {
                        productos = new ArrayList<Product>();
                        cat = new Category();
                        cat.setId(xrp.getAttributeValue(null, "id"));
                    } else if (etiqueta != null) {
                        if (etiqueta.equals("nombre")) {
                            cat.setnCategory(xrp.nextText());
                            categorias.add(cat);
                        } else if (etiqueta.equals("producto")) {
                            p = new Product();
                            p.setId(Integer.parseInt(xrp.getAttributeValue(null, "id")));
                            xrp.next();
                            p.setName(xrp.nextText());
                            xrp.next();
                            p.setPrice(xrp.nextText().replace(",", "."));
                            xrp.next();
                            p.setDescription(xrp.nextText());
                            xrp.next();
                            p.setImage(xrp.nextText());
                            productos.add(p);
                            cat.setListProducts(productos);
                        }

                    }
                    break;
            }
            event = xrp.next();
        }
    }

    class CustomAdapter extends ArrayAdapter<Category> {

        public CustomAdapter(@NonNull Context context, ArrayList<Category> resource) {
            super(context, 0, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Category category = getItem(position);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.customlayout_categorias, parent, false);

                TextView nombreCategoria = convertView.findViewById(R.id.nombre_categoria);

                nombreCategoria.setText(category.getnCategory());
            }
            return convertView;
        }
    }
}
