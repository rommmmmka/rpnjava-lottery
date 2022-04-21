package com.kravets.rpnjava4;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

public class Main {
    private static List<Ticket> tickets;

    public static void main(String[] args) {
        tickets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tickets.add(new Ticket());
            System.out.println(tickets.get(i));
        }
        System.out.println("");
        try{
            Server server  = new Server(new ServerSocket(4004));
            while (true) {
                try {
                    server.receiveFromClient();
                    Ticket currentTicket = server.getTicket();
                    if (currentTicket == null) {
                        server = new Server(new ServerSocket(4004));
                        continue;
                    }
                    System.out.println("Ticket received:\n" + currentTicket);
                    int bestId = -1;
                    Set<Integer> bestCorrect = new HashSet<>();
                    for (int i = 0; i < 10; i++) {
                        Set<Integer> currCorrect = new HashSet<>(tickets.get(i).getValues());
                        currCorrect.retainAll(currentTicket.getValues());
                        if (bestId == -1 || bestCorrect.size() < currCorrect.size()) {
                            bestId = i + 1;
                            bestCorrect = currCorrect;
                        }
                    }
                    ReturnData returnData = new ReturnData(bestCorrect, bestId);
                    System.out.println(returnData);
                    server.replyToClient(returnData);
                    System.out.println("");
                }
                catch (Exception e) {

                }
            }
        } catch (Exception e){}
    }
}
