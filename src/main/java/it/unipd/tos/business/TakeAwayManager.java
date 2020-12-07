////////////////////////////////////////////////////////////////////
// [Damiano] [Mason] [1201160]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.ArrayList;
import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayManager implements TakeAwayBill{

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {
        double total = 0.0;
        int nGelati = 0;
        double minGelato = Double.MAX_VALUE;

        for (MenuItem menuItem : itemsOrdered) {
            total += menuItem.getPrice();

            if(menuItem.getType() == MenuItem.items.Gelato){
                nGelati++;
                if(minGelato > menuItem.getPrice()){
                    minGelato = menuItem.getPrice();
                }
            }
        }

        if(nGelati >= 5){
            total = total - (minGelato/2);
        }
        return total;
    }
}