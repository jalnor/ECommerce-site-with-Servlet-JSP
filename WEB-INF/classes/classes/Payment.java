/**
 * @author      : Jarrod Norris
 * Class        :
 * Date         :
 */

package classes;

import java.io.Serializable;
import java.util.ArrayList;


public class Payment implements Serializable{
    
    
    private ArrayList<Integer> month; 
    private ArrayList<Integer> year;
    private int yr;
    private int mnth;
    private String creditCardType;
    private String cardNumber;
    private String expireMonth;
    private String expireYear;
    private String cvv;
    
    public Payment() {
       
       yr = 2017;
       mnth = 1;
       month = new ArrayList<>();
       year = new ArrayList<>();
       creditCardType = null;
       cardNumber = null;
       expireMonth = null;
       expireYear = null;
       cvv = null;
    }
    
    public Payment(String creditCardType, String cardNumber, String expireMonth, String expireYear, String cvv) {
        this.creditCardType = creditCardType;
        this.cardNumber = cardNumber;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.cvv = cvv;
    }

    public int getYr() {
        return yr;
    }

    public void setYr(int yr) {
        this.yr = yr;
    }

    public int getMnth() {
        return mnth;
    }

    public void setMnth(int mnth) {
        this.mnth = mnth;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(String expireMonth) {
        this.expireMonth = expireMonth;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(String expireYear) {
        this.expireYear = expireYear;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    
    
    public ArrayList<Integer> getYear() {
        System.out.println(" Payment @ line 30 " + year);
        for ( int i = 0; i < 10; i++ ) {
            //System.out.println(yr);
            year.add(yr);
            yr++;
            System.out.println(year.get(i));
        } 
        System.out.println(year);
        return year;
    }
    
    public ArrayList<Integer> getMonth() {
        System.out.println(month); 
        for ( int k = 0; k < 12; k++ ) {
           month.add(mnth++);
        } 
        System.out.println(month); 
        return month;
        
    }   
    
}
