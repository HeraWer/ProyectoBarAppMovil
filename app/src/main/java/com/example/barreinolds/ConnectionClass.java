package com.example.barreinolds;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ConnectionClass {

    public static final int PORT = 7412;
    private InetAddress serverIP;
    private DatagramSocket dSocket;
    private DatagramPacket dPacket;
    private ObjectOutputStream outputServer;
    private ByteArrayOutputStream baos;
    private byte[] buff;


    public ConnectionClass() throws IOException {
        byte[] addr = {(byte) 192, (byte) 168, 40, 22};
        serverIP = InetAddress.getByAddress(addr);

        dSocket = new DatagramSocket(PORT);

        baos  = new ByteArrayOutputStream(6400);
        outputServer = new ObjectOutputStream(baos);
    }

    public void sendTicket(Ticket t) throws IOException {
        buff = baos.toByteArray();
        outputServer.writeObject(t);

        dPacket = new DatagramPacket(buff, buff.length);
        dSocket.send(dPacket);
    }

}
