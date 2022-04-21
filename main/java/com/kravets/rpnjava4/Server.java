package com.kravets.rpnjava4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream inp;
    private ObjectOutputStream outp;

    private Ticket ticket;

    public Server(ServerSocket serverSocket) {
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();

            this.outp = new ObjectOutputStream(socket.getOutputStream());
            this.inp = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {}
    }

    public void replyToClient(ReturnData returnData) {
        if (socket.isConnected()) {
            try {
                outp.writeObject(returnData);
                System.out.println("Answer sent");
            } catch (IOException e) {
                closeConnection(serverSocket, socket, outp, inp);
            }
        }
    }

    public void receiveFromClient() {
        if (socket.isConnected()) {
            try {
                this.ticket = (Ticket) inp.readObject();
            } catch (IOException | ClassNotFoundException e) {
                closeConnection(serverSocket, socket, outp, inp);
            }
        }
    }

    public void closeConnection(ServerSocket serverSocket, Socket socket, ObjectOutputStream outp, ObjectInputStream inp) {
        try {
            if (inp != null) inp.close();
            if (outp != null) outp.close();
            if (serverSocket != null) serverSocket.close();
            if (socket != null) socket.close();
        } catch (IOException e) {}
    }

    public Ticket getTicket() {
        Ticket returnTicket = ticket;
        ticket = null;
        return returnTicket;
    }
}