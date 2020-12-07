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

public class TakeAwayManagerTest {
    @Test
    public void CalculateBillTotalTest() {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayManager testBill = new TakeAwayManager();
        User user = new User("Pino", 17);

        itemsOrdered.add(new MenuItem("Banana Split", MenuItem.items.Gelato, 4.00));
        itemsOrdered.add(new MenuItem("Pinguino", MenuItem.items.Budino, 2.50));
        itemsOrdered.add(new MenuItem("Coca cola", MenuItem.items.Bevanda, 1.50));

        try {
            assertEquals(8.00, testBill.getOrderPrice(itemsOrdered, user), 0.0);
        } catch (TakeAwayBillException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void HalvedPriceOnCheapestIceCreamWithFiveOrMoreIceCreamsTest() {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayManager testBill = new TakeAwayManager();
        User user = new User("Pino", 17);

        itemsOrdered.add(new MenuItem("Banana split", MenuItem.items.Gelato, 4.00));
        itemsOrdered.add(new MenuItem("Coca cola", MenuItem.items.Bevanda, 1.50));
        itemsOrdered.add(new MenuItem("Coppa nafta", MenuItem.items.Gelato, 3.50));
        itemsOrdered.add(new MenuItem("Cremino", MenuItem.items.Gelato, 4.00));
        itemsOrdered.add(new MenuItem("Buonino", MenuItem.items.Budino, 3.00));
        itemsOrdered.add(new MenuItem("Cremino", MenuItem.items.Gelato, 4.00));
        itemsOrdered.add(new MenuItem("Coca cola", MenuItem.items.Bevanda, 1.50));
        itemsOrdered.add(new MenuItem("Coppa del nonno", MenuItem.items.Gelato, 3.50));
        itemsOrdered.add(new MenuItem("Bombardino", MenuItem.items.Gelato, 4.00));
        itemsOrdered.add(new MenuItem("Biancaneve", MenuItem.items.Budino, 3.00));

        try {
            assertEquals(30.25, testBill.getOrderPrice(itemsOrdered, user), 0.0);
        } catch (TakeAwayBillException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void NotHalvedPriceOnCheapestIceCreamWithFiveOrLessIceCreamsTest() {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayManager testBill = new TakeAwayManager();
        User user = new User("Pino", 17);

        itemsOrdered.add(new MenuItem("Coca cola", MenuItem.items.Bevanda, 1.50));
        itemsOrdered.add(new MenuItem("Cremino", MenuItem.items.Gelato, 4.00));
        itemsOrdered.add(new MenuItem("Buonino", MenuItem.items.Budino, 3.00));
        itemsOrdered.add(new MenuItem("Cremino", MenuItem.items.Gelato, 4.00));
        itemsOrdered.add(new MenuItem("Coca cola", MenuItem.items.Bevanda, 1.50));
        itemsOrdered.add(new MenuItem("Coppa del nonno", MenuItem.items.Gelato, 3.50));
        itemsOrdered.add(new MenuItem("Bombardino", MenuItem.items.Gelato, 4.00));
        itemsOrdered.add(new MenuItem("Biancaneve", MenuItem.items.Budino, 3.00));

        try {
            assertEquals(24.50, testBill.getOrderPrice(itemsOrdered, user), 0.0);
        } catch (TakeAwayBillException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void Discount10With50EurosBillTotal() {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayManager testBill = new TakeAwayManager();
        User user = new User("Pino", 17);

        for(int i = 1; i <= 10; i++)
            itemsOrdered.add(new MenuItem("Banana split", MenuItem.items.Gelato, 4.00));

        itemsOrdered.add(new MenuItem("Coca cola" , MenuItem.items.Bevanda, 1.50));
        itemsOrdered.add(new MenuItem("Fragola", MenuItem.items.Gelato, 3.50));
        itemsOrdered.add(new MenuItem("Coppa nafta" , MenuItem.items.Gelato, 4.00));
        itemsOrdered.add(new MenuItem("Biancaneve" , MenuItem.items.Budino, 3.00));
        try {
            assertEquals(50.25, testBill.getOrderPrice(itemsOrdered, user), 0.0);
        }
        catch (TakeAwayBillException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void Discount10With50EurosNoIceCreamsBillTotal() {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayManager testBill = new TakeAwayManager();
        User user = new User("Pino", 17);

        for(int i = 1; i <= 10; i++)
            itemsOrdered.add(new MenuItem("Budo", MenuItem.items.Budino, 4.00));

        itemsOrdered.add(new MenuItem("Coca cola" , MenuItem.items.Bevanda, 1.50));
        itemsOrdered.add(new MenuItem("Fragola", MenuItem.items.Budino, 3.50));
        itemsOrdered.add(new MenuItem("Fragola", MenuItem.items.Budino, 3.50));
        itemsOrdered.add(new MenuItem("Buonino" , MenuItem.items.Budino, 3.00));
        itemsOrdered.add(new MenuItem("Biancaneve" , MenuItem.items.Budino, 3.00));
        try {
            assertEquals(49.2, testBill.getOrderPrice(itemsOrdered, user), 0.0);
        }
        catch (TakeAwayBillException e){
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = TakeAwayBillException.class)
    public void MoreThan30ElementsInBillTest() throws TakeAwayBillException {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayManager testBill = new TakeAwayManager();
        User user = new User("Pino", 17);

        for(int i = 1; i <= 40; i++)
            itemsOrdered.add(new MenuItem("Greedy Cream", MenuItem.items.Gelato, 4.00));

        testBill.getOrderPrice(itemsOrdered, user);
    }
}