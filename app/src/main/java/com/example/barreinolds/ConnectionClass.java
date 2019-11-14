package com.example.barreinolds;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionClass {

    public static final int PORT = 1234;
    private InetAddress serverIP;
    private DatagramSocket dSocket;
    private DatagramPacket dPacket;
    private ObjectOutputStream outputServer;
    private ObjectInputStream inputServer;
    private ByteArrayOutputStream baos;
    private byte[] buff;
    private Socket socket;


    public ConnectionClass() throws IOException {
        byte[] addr = new byte[]{(byte) 192, (byte) 168, (byte) 40, (byte) 22};
        serverIP = InetAddress.getByAddress(addr);
//
//        dSocket = new DatagramSocket(PORT);
//
//        baos  = new ByteArrayOutputStream(5000);
//        outputServer = new ObjectOutputStream(baos);

        socket = new Socket(serverIP, PORT);
        outputServer = new ObjectOutputStream(socket.getOutputStream());
        inputServer = new ObjectInputStream(socket.getInputStream());

    }

    public void sendTicket(Ticket t) throws IOException {
//        buff = baos.toByteArray();
//        outputServer.writeObject(t);
//
//        dPacket = new DatagramPacket(buff, buff.length, serverIP, PORT);
//        dSocket.send(dPacket);
        
        outputServer.writeObject(t);

    }

}
