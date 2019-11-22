package com.example.barreinolds;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionClass {

    private byte[] addr = new byte[]{(byte) 192, (byte) 168, (byte) 40, (byte) 202};
    public static final int PORT = 1234;
    public InetAddress serverIP = InetAddress.getByAddress(addr);
    private Socket socket = new Socket(serverIP, PORT);
    private ObjectOutputStream outputServer = new ObjectOutputStream(socket.getOutputStream());
    private ObjectInputStream inputClient = new ObjectInputStream(socket.getInputStream());

    public ConnectionClass() throws IOException {
    }

    public void sendTicket(Ticket t) throws IOException {
        outputServer.writeObject(t);
    }

    public Object sendMessage(Message message) throws IOException, ClassNotFoundException {
        if(message.getRequest().equals("CAMAREROS")) {
            outputServer.writeObject(message);
            return inputClient.readObject();
        }else if(message.getRequest().equals("NUMMESAS")){
            outputServer.writeObject(message);
            return inputClient.readObject();
        }else if(message.getRequest().equals("CATEGORIAS")){
            outputServer.writeObject(message);
            return inputClient.readObject();
        }else if(message.getRequest().equals("RECUPERARTICKET")){
            outputServer.writeObject(message);
            return inputClient.readObject();
        }
        return null;
    }

}
