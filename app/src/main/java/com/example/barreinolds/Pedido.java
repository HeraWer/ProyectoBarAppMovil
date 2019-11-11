package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Pedido extends AppCompatActivity {

    public void crearXML(String mesa, Context c, ArrayList<Product> listaProductos){

        int importeTotal = 0;

        try {
        File pedidoXML = new File( c.getFilesDir(), "pedidoMesa" + mesa + ".xml");
        if(!pedidoXML.exists()){
            pedidoXML.createNewFile();
        }else {
            pedidoXML.delete();
        }
        XmlSerializer xmlSerializer = Xml.newSerializer();
        OutputStreamWriter osWriter = new OutputStreamWriter(c.openFileOutput(pedidoXML.getName(), MODE_APPEND));
        xmlSerializer.setOutput(osWriter);

        xmlSerializer.startDocument("uft-8", false);
        xmlSerializer.startTag("", "Bar");
        xmlSerializer.startTag("", "Mesa");
        xmlSerializer.attribute("", "id", "1");
        xmlSerializer.startTag("", "Pedido");
        for(int i = 0; i<listaProductos.size(); i++) {
            if(listaProductos.get(i).getCantidad() >= 0){
                xmlSerializer.startTag("", "Producto");
                xmlSerializer.startTag("", "Nombre");
                xmlSerializer.text(listaProductos.get(i).getName());
                xmlSerializer.endTag("", "Nombre");
                xmlSerializer.startTag("", "Cantidad");
                xmlSerializer.text(String.valueOf(listaProductos.get(i).getCantidad()));
                xmlSerializer.endTag("", "Cantidad");
                xmlSerializer.startTag("", "Precio");
                xmlSerializer.text(listaProductos.get(i).getPrice());
                xmlSerializer.endTag("", "Precio");
                xmlSerializer.endTag("","Producto");
                //importeTotal = importeTotal + (listaProductos.get(i).getCantidad() * Integer.parseInt(listaProductos.get(i).getPrice()));
            }
        }
        xmlSerializer.endTag("", "Pedido");
        xmlSerializer.endTag("","Mesa");
        xmlSerializer.endTag("","Bar");
        xmlSerializer.endDocument();
        osWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String seleccionMesa(String mesa){
        Mesas m = new Mesas();

        for(int i = 0; i < m.listaMesas.size(); i++){
            String[] numMesa = m.listaMesas.get(i).split(" ");
            if(numMesa[1].equals(mesa)){
                return numMesa[1];
            }
        }
        return null;
    }
}
