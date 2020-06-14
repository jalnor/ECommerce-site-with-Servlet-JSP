/**
 * @author      : Jarrod Norris
 * Class        : ITIS 4166-UOL
 * Date         : 9 Nov. 2017
 */

package classes;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Order implements Serializable{

    private int orderNumber;
    private Timestamp date;
    private float taxRate;
    private double total;
    private boolean paid;
    private int userID;
    User user;
    ArrayList<OrderItems> items;
    /**
     * Constructor sets instance variables.
     */
    public Order() {
        orderNumber = 0;
        date = null;
        taxRate = 7;
        total = 0.0;
        paid = false;
        userID = 0;
        user = new User();
        items = new ArrayList();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**
     * Method sets the orderNumber on current order object.
     * @param orderNumber 
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    /**
     * Method gets the orderNumber for current order object.
     * @return orderNumber
     */            
    public int getOrderNumber() {
        return this.orderNumber;
    }
    /**
     * Method sets the current date on order object.
     * @param date 
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }
    /**
     * Method gets the date associated with the order object.
     * @return date
     */
    public Timestamp getDate() {
        return this.date;
    }
    /**
     * 
     * @param taxRate 
     */
    public void setTaxRate(float taxRate) {
        this.taxRate = taxRate;
    }
    /**
     * 
     * @return 
     */            
    public float getTaxRate() {
        return taxRate;
    }
    /**
     * 
     * @param paid 
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    /**
     * 
     * @return 
     */            
    public boolean getPaid() {
        return paid;
    }
    /**
     * 
     * @param user 
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * 
     * @return 
     */
    public User getUser() {
        return user;
    }
    /**
     * 
     * @param items 
     */
    public void setItems(ArrayList<OrderItems> items) {
        this.items = items;
    }
    /**
     * 
     * @return 
     */
    public ArrayList<OrderItems> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
