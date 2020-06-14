/**
 * @author      : Jarrod Norris
 * Class        : ITIS 4166-UOL
 * Date         : 9 Nov. 2017
 */

package classes;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Cart implements Serializable {
    
     private final ArrayList<OrderItems> items = new ArrayList<>();
     
     public Cart() {
      
     }
    
     public void addItem(OrderItems item) {
         System.out.println("cart.java @ line 25");
        this.items.add(item);
    }
     
    public OrderItems getItems(int i) {
        System.out.println(this.items.get(i).getTotal());
        return this.items.get(i);
    } 
    
    public ArrayList<OrderItems> getItems() {
        return this.items;
    }
    
    public String getCartTotal(double totalCart) {        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(totalCart);
    }
    
    public String getTotal(double total) {        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(total);
    }
    
    public String getTax(double tax) {        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(tax);
    }
    
    public void removeItem(OrderItems o) {
        this.items.remove(o);
    }
    
    public void removeItems() {        
        this.items.clear();       
    }

}
