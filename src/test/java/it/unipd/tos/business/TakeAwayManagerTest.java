////////////////////////////////////////////////////////////////////
// [Damiano] [Mason] [1201160]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.business.TakeAwayManager;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import it.unipd.tos.model.User;
import org.junit.Test;

public class TakeAwayManagerTest{
    @Test
    public void CalculateBillTotalTest(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayManager testBill = new TakeAwayManager();
        User user = new User("Pino", 17);

        itemsOrdered.add(new MenuItem("Banana Split", MenuItem.items.Gelato, 4.00));
        itemsOrdered.add(new MenuItem("Pinguino", MenuItem.items.Budino, 2.50));
        itemsOrdered.add(new MenuItem("Coca cola", MenuItem.items.Bevanda, 1.50));

        try{
            assertEquals(8.00, testBill.getOrderPrice(itemsOrdered, user), 0.0);
        }
        catch (TakeAwayBillException e){
            System.out.println(e.getMessage());
        }
    }
}