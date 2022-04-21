package com.kravets.rpnjava4;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket socket;
    private ObjectInputStream inp;
    private ObjectOutputStream outp;

    private ArrayList<Integer> correctIndexes;
    private int bestId;

    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.outp = new ObjectOutputStream(socket.getOutputStream());
            this.inp = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            closeConnection(socket, outp, inp);
        }
    }

    public void sendToServer(Ticket ticket) {
        try {
            if (ticket != null)
                outp.writeObject(ticket);
        } catch (IOException e) {
            e.printStackTrace();
            closeConnection(socket, outp, inp);
        }
    }

    public void receiveFromServer() {
        try {
            ReturnData returnData = (ReturnData) inp.readObject();
            this.correctIndexes = new ArrayList<>(returnData.getCorrectIndexes());
            this.bestId = returnData.getBestId();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            closeConnection(socket, outp, inp);
        }
    }

    public void closeConnection(Socket socket, ObjectOutputStream outp, ObjectInputStream inp) {
        try {
            if (inp != null) inp.close();
            if (outp != null) outp.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getCorrectIndexes() {
        return correctIndexes;
    }

    public int getBestId() {
        return bestId;
    }
}