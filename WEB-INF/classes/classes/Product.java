/**
 * @author      : Jarrod Norris
 * Class        : ITIS 4166-UOL
 * Date         : 4 Oct. 2017
 */

package classes;

import java.io.Serializable;
import java.text.NumberFormat;


public class Product implements Serializable{
    
   // private String productID;
    private String productCode;
    private String productName;
    private String catalogCategory;
    private String description;
    private float price;
    private String imageURL;
    
    public Product() {
        productCode = "";
        productName = "";
        catalogCategory = "";
        description = "";
        price = 0;
        imageURL = "";
    }
    
    public Product( String productCode, String productName, String catalogCategory, 
            String description, float price, String imageURL) {
        this.productCode = productCode;
        this.productName = productName;
        this.catalogCategory = catalogCategory;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
    }
    
    public String getProductCode() {
       return productCode; 
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    public String getProductName() {
       return productName; 
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getCatalogCategory() {
       return catalogCategory; 
    }
    
    public void setCatalogCategory(String catalogCategory) {
        this.catalogCategory = catalogCategory;
    }
    
    public String getDescription() {
       return description; 
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public float getPrice() {
       return price; 
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    public String getImageURL() {
       return imageURL; 
    }
    
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    public String getPriceCurrencyFormat() {        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
   
}
