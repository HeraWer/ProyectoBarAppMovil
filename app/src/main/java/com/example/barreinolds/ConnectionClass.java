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
    private byte[] addr = new byte[]{(byte) 192, (byte) 168, (byte) 40, (byte) 202};
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
            outputServer.writeObject(message);
            return inputClient.readObject();
    }

    /*
    Metodo que envía el ticket al server
     */
    public void sendEmployee(Camarero c) throws IOException {
        outputServer.writeObject(c);
    }


}
