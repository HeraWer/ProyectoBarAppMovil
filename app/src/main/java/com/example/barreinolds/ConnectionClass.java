package com.example.barreinolds;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionClass {

    private byte[] addr = new byte[]{(byte) 192, (byte) 168, (byte) 41, (byte) 156};
    public static final int PORT = 1234;
    private InetAddress serverIP = InetAddress.getByAddress(addr);
    private Socket socket = new Socket(serverIP, PORT);
    private ObjectOutputStream outputServer = new ObjectOutputStream(socket.getOutputStream());

    public ConnectionClass() throws IOException {
    }

    public void sendTicket(Ticket t) throws IOException {
        outputServer.writeObject(t);
    }

}
