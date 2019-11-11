package com.example.barreinolds;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Category> listaCategoria;
    Category cat;
    Product p;
    ArrayList<Product> listaProductos;
    ArrayList<String> listaCategoriaNombres; // RECOGIA LOS NOMBRES DE LA LISTA CATEGORIA


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            leerCatXML();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        listaCategoriaNombres();


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
                    listaCategoria = new ArrayList<Category>();
                    break;
                case XmlPullParser.START_TAG:
                    etiqueta = xrp.getName();
                    if (etiqueta.equals("categoria")) {
                        listaProductos = new ArrayList<Product>();
                        cat = new Category();
                        cat.setId(xrp.getAttributeValue(null, "id"));
                    } else if (etiqueta != null) {
                        if (etiqueta.equals("nombre")) {
                            cat.setnCategory(xrp.nextText());
                            listaCategoria.add(cat);
                        } else if (etiqueta.equals("producto")) {
                            p = new Product();
                            p.setId(Integer.parseInt(xrp.getAttributeValue(null, "id")));
                            xrp.next();
                            p.setName(xrp.nextText());
                            xrp.next();
                            p.setPrice(xrp.nextText());
                            xrp.next();
                            p.setDescription(xrp.nextText());
                            xrp.next();
                            p.setImage(xrp.nextText());
                            listaProductos.add(p);
                            cat.setListProducts(listaProductos);
                        }

                    }
                    break;


            }
            event = xrp.next();
        }
    }

    public void listaCategoriaNombres() {

        for (Category cat : listaCategoria) {
            listaCategoriaNombres.add(cat.getnCategory());

        }

    }

    /*public void listaProductos(String nameCat){

        for(Product p : productes){
            listaProductos.add();
        }
    }*/


}


