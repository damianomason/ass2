////////////////////////////////////////////////////////////////////
// [Damiano] [Mason] [1201160]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.Order;
import it.unipd.tos.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TakeAwayManager implements TakeAwayBill {

    private final double COMMISSION = 0.5;
    private List<Order> Orders = new ArrayList<Order>();
    private boolean LESS_WINNERS = false;
    private List<User> Winners = new ArrayList<User>();

    public void addOrder(List<MenuItem> itemsOrdered, User user, int h, int m) {
        Orders.add(new Order(user, h, m, itemsOrdered));
    }

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {
        if (itemsOrdered.size() > 30) {
            throw new TakeAwayBillException("Non ci possono essere più di 30 elementi nell'ordine");
        }

        double totalFood = 0.0;
        double totalDrink = 0.0;
        int nGelati = 0;
        double minGelato = Double.MAX_VALUE;

        for (MenuItem menuItem : itemsOrdered) {
            if (menuItem.getType() == MenuItem.items.Bevanda) {
                totalDrink += menuItem.getPrice();
            } else {
                totalFood += menuItem.getPrice();
            }

            if (menuItem.getType() == MenuItem.items.Gelato) {
                nGelati++;
                if (minGelato > menuItem.getPrice()) {
                    minGelato = menuItem.getPrice();
                }
            }
        }

        if (nGelati >= 5) {
            totalFood -= (minGelato / 2);
        }
        if (totalFood > 50.0) {
            totalFood -= (totalFood * 0.1);
        }
        double total = totalFood + totalDrink;
        if (total <= 10.0) {
            return total + COMMISSION;
        } else {
            return total;
        }
    }

    public List<User> freeOrder() {
        int winnerCount = 0;
        int minorsCount = 0;
        Map<String, User> winners = new HashMap<String, User>();
        for (Iterator<Order> it = Orders.iterator(); it.hasNext(); ) {
            Order order = it.next();
            User us = order.getUser();
            if (us.getEta() < 18 && us.getEta() >= 0) {
                minorsCount++;
            }
        }
        minorsCount = Math.min(minorsCount, 10);
        while (winnerCount < minorsCount) {
            for (Iterator<Order> it = Orders.iterator(); it.hasNext(); ) {
                Order order = it.next();
                if (((new Random()).nextInt(100) + 1) % 10 == 0) {
                    Calendar lower = Calendar.getInstance();
                    lower.set(Calendar.HOUR_OF_DAY, 18);
                    lower.set(Calendar.MINUTE, 00);
                    Calendar upper = Calendar.getInstance();
                    upper.set(Calendar.HOUR_OF_DAY, 19);
                    upper.set(Calendar.MINUTE, 00);

                    if (order.getTime().compareTo(lower) >= 0 && order.getTime().compareTo(upper) <= 0) {
                        if (!winners.containsKey(order.getUser().getName())) {
                            User us = order.getUser();
                            if (us.getEta() < 18 && us.getEta() >= 0) {
                                winnerCount++;
                                winners.put(us.getName(), order.getUser());
                                order.setTotal(0);
                            }
                        }
                    }
                }
            }
        }
        if (winnerCount < 9) {
            LESS_WINNERS = true;
        } else {
            LESS_WINNERS = false;
        }

        Winners = new ArrayList<User>(winners.values());
        return Winners;
    }

    boolean getLessWinners() {
        return LESS_WINNERS;
    }

    List<User> getWinners() {
        return Winners;
    }

    List<Order> getOrders() {
        return Orders;
    }
}