/**
 * @author      : Jarrod Norris
 * Class        : ITIS 4166-UOL
 * Date         : 9 Nov. 2017
 */

package classes;

import java.io.Serializable;
import java.text.NumberFormat;
import utilities.DB;


public class OrderItems implements Serializable{
    
    private int quantity;
    private double total;
    private String productCode;
    private Product product;
    private int orderNumber;
    /**
     * @Constructor
     */
    public OrderItems() {
        quantity = 0;
        total = 0.0;
        orderNumber = 0;
        product = new Product();
    } 

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    /**
     * Method sets the quantity to the provided amount
     * @param quantity 
     */   
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Method returns the current quantity
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Method returns total calculated from product price and quantity
     * @return total
     */
    public double getTotal() {
        return total = (product.getPrice() * quantity);
    }
    /**
     * Method returns the calculated total in currency format
     * @return total
     */
    public String getTotals() {        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(total);
    }
    /**
     * Method sets the product provided
     * @param product 
     */    
    public void setProduct(Product product) {
        this.product = product;
    }
    /**
     * Method returns current product
     * @return product
     */
    public Product getProduct() {
        
        return this.product;
    }
    
    public void setProduct(String productCode) {
        this.product = DB.selectProduct(productCode);
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
