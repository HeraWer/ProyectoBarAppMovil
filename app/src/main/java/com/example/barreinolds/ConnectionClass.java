package com.example.barreinolds;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/*
Clase donde se crea la conexion con el server
y se ejecutan los diferentes metodos que
envian y reciben los datos a traves de los
input y output streams
 */
public class ConnectionClass {

    /*
    Atributos de clase y datos para la conexión
     */
    private byte[] addr = new byte[]{(byte) 10, (byte) 151, (byte) 104, (byte) 151};
    public static final int PORT = 1234;
    InetAddress serverIP = InetAddress.getByAddress(addr);
    Socket socket = new Socket(serverIP, PORT);
    private ObjectOutputStream outputServer = new ObjectOutputStream(socket.getOutputStream());
    private ObjectInputStream inputClient = new ObjectInputStream(socket.getInputStream());

    /*
    Constructor donde conectamos el socket y asignamos un Timeout
    Ademas se cogen los input y output streams del socket
     */
    public ConnectionClass() throws IOException {

    }

    /*
    Metodo que envía el ticket al server
     */
    public void sendTicket(Ticket t) throws IOException {
        outputServer.writeObject(t);
    }

    /*
    Metodo que envia el mensaje al server
    para recibir lo que se solicita a traves
    del campo request de la clase Message
     */
    public Object sendMessage(Message message) throws IOException, ClassNotFoundException {
        // Si el mensaje es CAMAREROS, enviamos mensaje y esperamos array de camareros
        if(message.getRequest().equals("CAMAREROS")) {
            outputServer.writeObject(message);
            return inputClient.readObject();

        // Si el mensaje es NUMMESAS, enviamos mensaje y esperamos total numero de mesas
        }else if(message.getRequest().equals("NUMMESAS")){
            outputServer.writeObject(message);
            return inputClient.readObject();

        // Si el mensaje es CATEGORIAS, enviamos mensaje y esperamos array de categorias
        // Estas a su vez tienen un array de lista de productos
        }else if(message.getRequest().equals("CATEGORIAS")){
            outputServer.writeObject(message);
            return inputClient.readObject();

        // Si el mensaje es RECUPERARTICKET, enviamos mensaje y esperamos array de tickets (comandas)
        }else if(message.getRequest().equals("RECUPERARTICKET")){
            outputServer.writeObject(message);
            return inputClient.readObject();
        }
        return null;
    }


}
