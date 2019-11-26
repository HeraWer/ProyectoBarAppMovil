package com.example.barreinolds;

import java.util.ArrayList;

public class Search {

    public static Ticket getTicket(int numMesa){
        for(Ticket t : Mesas.tickets){
            if(t.getMesa() == numMesa)
                return t;
        }
        return null;
    }

    public static void deleteTicket(int numMesa){
        for(Ticket t : Mesas.tickets){
            if(t.getMesa() == numMesa) {
                Mesas.tickets.remove(t);
                return;
            }
        }
    }

    public static Product compareProducts(Product p, ArrayList<Product> products){
        if(p == null)
            return null;
        for(Product pTicket : products){
            if(p.getId() == pTicket.getId())
                return pTicket;
        }
        return null;
    }

    public static boolean lookForTicketTable(int numMesa) {
        for (Ticket t : Mesas.tickets) {
            if (t.getMesa() == numMesa)
                return true;
        }
        return false;
    }


}
