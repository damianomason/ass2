////////////////////////////////////////////////////////////////////
// [Damiano] [Mason] [1201160]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import java.util.Calendar;
import java.util.List;

public class Order {
    private User user;
    private Calendar time;
    private List<MenuItem> items;
    private double total;

    private void OrderFactory(User u, Calendar t, List<MenuItem> i) {
        this.user = u;
        this.time = t;
        this.items = i;
        this.total = 0.0;
    }

    public Order(User u, int hours, int minutes, List<MenuItem> i) {
        Calendar t = Calendar.getInstance();
        t.set(Calendar.HOUR_OF_DAY, hours);
        t.set(Calendar.MINUTE, minutes);
        OrderFactory(u, t, i);
    }

    public User getUser() {
        return user;
    }

    public Calendar getTime() {
        return time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double price) {
        total = price;
    }
}
