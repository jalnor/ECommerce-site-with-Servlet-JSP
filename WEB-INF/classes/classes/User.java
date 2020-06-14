/**
 * @author      : Jarrod Norris
 * Class        : ITIS 4166-UOL
 * Date         : 9 Nov. 2017
 */

package classes;

import java.io.Serializable;


public class User implements Serializable{
    private int userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String addr1;
    private String addr2;
    private String city;
    private String stateRegion;
    private int zipcode;
    private String country;
    private String roleName;
    Payment paymentType;
    
    public User() {
        userID = 0;
        username = "";
        password = "";
        firstName = "";
        lastName = "";
        email = "";
        addr1 = "";
        addr2 = "";
        city = "";
        stateRegion = "";
        zipcode = 0;
        country = "";
        roleName = "";
        paymentType = new Payment();
    }
    
    public User(int uID,  String uname, String pWord, String firstName, String lastName, String email, String addr1,
            String addr2, String city, String stateRegion, int zipcode, String country, String roleName) {
        this.userID = uID;
        this.username = uname;
        this.password = pWord;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.city = city;
        this.stateRegion = stateRegion;
        this.zipcode = zipcode;
        this.country = country;
        this.roleName = roleName;
    }
    
    public void setUserID(int id) {
        this.userID = id;
    }
    
    public int getUserID() {
        return userID;
    }
    
    public void setUsername(String uname) {
        this.username = uname;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setPassword(String pWord) {
        this.password = pWord;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() {        
        return firstName;        
    }
    
     public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
     public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
     public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }
    
    public String getAddr1() {
        return addr1;
    }
    
     public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }
    
    public String getAddr2() {
        return addr2;
    }
    
     public void setCity(String city) {
        this.city = city;
    }
    
    public String getCity() {
        return city;
    }
    
     public void setStateRegion(String stateRegion) {
        this.stateRegion = stateRegion;
    }
    
    public String getStateRegion() {
        return stateRegion;
    }
    
     public void setZipcode(int zipcode) {
        this.zipcode = zipcode; 
    }
    
    public int getZipcode() {
        return zipcode;
    }
    
     public void setCountry(String country) {
        this.country = country;
    }
    
    public String getCountry() {
        return country;
    }
    
    public Payment getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Payment paymentType) {
        this.paymentType = paymentType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
