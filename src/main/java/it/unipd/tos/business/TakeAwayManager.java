////////////////////////////////////////////////////////////////////
// [Damiano] [Mason] [1201160]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayManager implements TakeAwayBill{

    private final double commission = 0.5;

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {
        if(itemsOrdered.size() > 30) {
            throw new TakeAwayBillException("Non ci possono essere più di 30 elementi nell'ordine");
        }

        double totalFood = 0.0;
        double totalDrink = 0.0;
        int nGelati = 0;
        double minGelato = Double.MAX_VALUE;

        for (MenuItem menuItem : itemsOrdered) {
            if(menuItem.getType() == MenuItem.items.Bevanda) {
                totalDrink += menuItem.getPrice();
            }
            else {
                totalFood += menuItem.getPrice();
            }

            if(menuItem.getType() == MenuItem.items.Gelato) {
                nGelati++;
                if(minGelato > menuItem.getPrice()){
                    minGelato = menuItem.getPrice();
                }
            }
        }

        if(nGelati >= 5) {
            totalFood -= (minGelato/2);
        }
        if(totalFood > 50.0) {
            totalFood -= (totalFood*0.1);
        }
        double total = totalFood + totalDrink;
        if(total <= 10.0) {
            return total + commission;
        }
        else {
            return total;
        }
    }
}