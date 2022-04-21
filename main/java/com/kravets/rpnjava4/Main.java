package com.kravets.rpnjava4;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    private static List<Ticket> tickets;

    public static void main(String[] args) {
        tickets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tickets.add(new Ticket());
            System.out.println(tickets.get(i));
        }
        try{
            Server server  = new Server(new ServerSocket(4004));
            while (true) {
                server.receiveFromClient();
                Ticket currentTicket = server.getTicket();
                System.out.println("Ticket received:\n" + currentTicket);
                int bestId = -1;
                List<Integer> bestCorrectIndexes = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    List<Integer> currCorrectIndexes = new ArrayList<>();
                    for (int j = 0; j < 10; j++) {
                        if (Objects.equals(currentTicket.getValues().get(j), tickets.get(i).getValues().get(j)))
                            currCorrectIndexes.add(j + 1);
                        if (currCorrectIndexes.size() > bestCorrectIndexes.size()) {
                            bestId = i + 1;
                            bestCorrectIndexes = currCorrectIndexes;
                        }
                    }
                }
                ReturnData returnData = new ReturnData(bestCorrectIndexes, bestId);
                System.out.println("Answer:\n" + returnData.toString());
                server.replyToClient(returnData);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
