/**
 * @author      : Jarrod Norris
 * Class        : ITIS 4166-UOL
 * Date         : 9 Nov. 2017
 */

package utilities;

import classes.Order;
import classes.OrderItems;
import classes.Payment;
import classes.Product;
import classes.SecurityQuestion;
import classes.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class DB {
    /**
     * Method inserts user into users table of jmansales db.
     * @param user
     * @return integer value
     */
    public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO users (uname, pword, firstName, lastName, email, addr1, addr2, city, state, zip, country, roleName) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getAddr1());
            ps.setString(7, user.getAddr2());
            ps.setString(8, user.getCity());
            ps.setString(9, user.getStateRegion());
            ps.setInt(10, user.getZipcode());
            ps.setString(11, user.getCountry());
            ps.setString(12, user.getRoleName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static int insertSecurityQuestion(SecurityQuestion fav) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO favorites(userID, question, answer) VALUES(?,?,?)";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, fav.getUserID());
            ps.setString(2, fav.getQuestion());
            ps.setString(3, fav.getAnswer());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
    }
    
    public static int updateSecurityQuestion(SecurityQuestion fav) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE favorites SET question = ?, answer = ? WHERE userID = ?";
        
        try{
            ps = connection.prepareStatement(query);
            
            ps.setString(1, fav.getQuestion());
            ps.setString(2, fav.getAnswer());
            ps.setInt(3, fav.getUserID());
            
            System.out.println("DB@line 96 " + query);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
    }
    
    public static SecurityQuestion getQA(String userName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        System.out.println("DB@line 92 userName: " + userName);
        String query = "SELECT * FROM users u, favorites f WHERE  u.userID = f.userID "
                +"AND u.uname = ?";
        
        try {
            SecurityQuestion sq = new SecurityQuestion();
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            System.out.println("DB@line 121 " + query);
            if(rs.next()) {
                sq.setUserID(rs.getInt("userID"));
                sq.setQuestion(rs.getString("question"));
                sq.setAnswer(rs.getString("answer"));                
            }    
            System.out.println("DB@line 103 " );
            return sq;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static String getHint(String userName, String question, String answer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String pWord = "";
        System.out.println("DB@line 92 userName: " + userName + " question: " + question + " answer: " + answer);
        String query = "Select pWord FROM users u, favorites f WHERE  u.userID = f.userID "
                +"AND u.uname = ? AND f.question = ? AND f.answer = ? ";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, question);
            ps.setString(3, answer);
            rs = ps.executeQuery();
            System.out.println("DB@line 95 " + query);
            if(rs.next())
                pWord = rs.getString("pWord");
            System.out.println("DB@line 103 " + pWord);
            return pWord;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method inserts product into product table of jmansales db.
     * @param p
     * @return integer
     */
    public static int insert(Product p) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        System.out.println("DBSetup @ LINE 101");
        String query
                = "INSERT INTO product (productCode, productName, catalogCategory, description, price, imageURL) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, p.getProductCode());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getCatalogCategory());
            ps.setString(4, p.getDescription());
            ps.setFloat(5, p.getPrice());
            ps.setString(6, p.getImageURL());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method inserts payment into payment table of jmansales db.
     * 
     * @param u
     * @return integer
     */
    public static int insertPayment(User u) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        System.out.println("DBSetup @ LINE 101");
        String query
                = "INSERT INTO payment (userID, creditCardType, cardNumber, expireMonth, expireYear, cvv) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, u.getUserID());
            ps.setString(2, u.getPaymentType().getCreditCardType());
            ps.setString(3, u.getPaymentType().getCardNumber());
            ps.setString(4, u.getPaymentType().getExpireMonth());
            ps.setString(5, u.getPaymentType().getExpireYear());
            ps.setInt(6, Integer.parseInt(u.getPaymentType().getCvv()));
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method inserts orderItem into orderItem table of jmansales db.
     * 
     * @param o
     * @return integer
     */
    public static int insert(OrderItems o) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        System.out.println("DBSetup @ LINE 101");
        String query
                = "INSERT INTO orderItem (orderNumber, productCode, quantity) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, o.getOrderNumber());
            ps.setString(2, o.getProduct().getProductCode());
            ps.setInt(3, o.getQuantity());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method inserts product into product table of jmansales db.
     * 
     * @param order
     * @return integer
     */
    public static int insert(Order order) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        System.out.println("DBSetup @ LINE 101");
        String query
                = "INSERT INTO orders (dateNow, userID, taxRate, totalCost, paid) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            //ps.setInt(1, order.getOrderNumber());
            ps.setTimestamp(1, order.getDate());
            ps.setInt(2, order.getUser().getUserID());
            System.out.println("order @ line 166 " + order.getUser().getUserID());
            ps.setFloat(3, order.getTaxRate());
            ps.setDouble(4, order.getTotal());
            ps.setBoolean(5, order.getPaid());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method updates existing product in jmansales db.
     * @param p
     * @return integer value
     */
    public static int update(Product p) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        System.out.println("DBSetup @ LINE 127");
        String query = "UPDATE product SET productCode = ?, productName = ?, catalogCategory = ?,"
                + "description = ?, price = ?, imageURL = ? "
                + " WHERE productCode = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, p.getProductCode());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getCatalogCategory());
            ps.setString(4, p.getDescription());
            ps.setFloat(5, p.getPrice());
            ps.setString(6, p.getImageURL());
            ps.setString(7, p.getProductCode());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method upates existing user in jmansales db.
     * @param user
     * @return integer value
     */
    public static int update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE users SET "
                + " uname = ?, pWord = ?, firstName = ?, lastName = ?, email = ?, addr1 = ?, addr2 = ?, city = ?, " 
                + " state = ?, zip = ?, country = ?, roleName = ? "
                + " WHERE userID = ?";
        try {
            ps = connection.prepareStatement(query);
            
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getAddr1());
            ps.setString(7, user.getAddr2());
            ps.setString(8, user.getCity());
            ps.setString(9, user.getStateRegion());
            ps.setInt(10, user.getZipcode());
            ps.setString(11, user.getCountry());
            ps.setString(12, user.getRoleName());
            ps.setInt(13, user.getUserID());
            System.out.println("DB@line353 " + user.getUserID());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method deletes existing user from jmasales db.
     * @param user
     * @return integer value
     */
    public static int delete(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM users "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method deletes existing product from jmasales db.
     * @param p
     * 
     * @return integer value
     */
    public static int delete(Product p) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM product "
                + "WHERE productCode = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, p.getProductCode());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method checks user info against db to login.
     * @param username
     * @param password
     * @return user
     */
    public static User userSignin(String username, String password) {
        System.out.println("dbsETUP @ LINE 422");
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ///For test purposes only //System.out.println("dbsETUP @ LINE 168");
        String query = "SELECT * FROM users "
                + "WHERE uname = ? AND pWord = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUsername(rs.getString("uname"));
                user.setPassword(rs.getString("pWord"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setAddr1(rs.getString("addr1"));
                user.setAddr2(rs.getString("addr2"));
                user.setCity(rs.getString("city"));
                user.setStateRegion(rs.getString("state"));
                user.setZipcode(rs.getInt("zip"));
                user.setCountry(rs.getString("country"));
                user.setRoleName(rs.getString("roleName"));                
            }
            ///For test purposes only ////System.out.println(user.getFirstName());
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    /**
     * Metho selects a user from jmansales db.
     * @param id
     * @return User object
     */
    
    public static User selectUser(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        System.out.println("db @ line 477" + id);

        String query = "SELECT * FROM users "
                + "WHERE userID = ?";      
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            System.out.println(query);
            rs = ps.executeQuery();
             User user = new User();
            if (rs.next()) {
                System.out.println("DB@line489 " + rs.getInt("userID"));
                user.setUserID(rs.getInt("userID"));
                user.setUsername(rs.getString("uname"));
                user.setPassword(rs.getString("pWord"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setAddr1(rs.getString("addr1"));
                user.setAddr2(rs.getString("addr2"));
                user.setCity(rs.getString("city"));
                user.setStateRegion(rs.getString("state"));
                user.setZipcode(rs.getInt("zip"));
                user.setCountry(rs.getString("country"));
                user.setRoleName(rs.getString("roleName"));
                
                System.out.println("DBsetup @ line 316 " + user.getUsername());
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method selects a product from jmansales db.
     * @param productCode
     * @return Product object
     */
    public static Product selectProduct(String productCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Product "
                + "WHERE productCode = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, productCode);
            rs = ps.executeQuery();
            Product p = null;
            if (rs.next()) {
                p = new Product();
                    p.setProductCode(rs.getString("productCode"));
                    p.setProductName(rs.getString("productName"));
                    p.setCatalogCategory(rs.getString("catalogCategory"));
                    p.setDescription(rs.getString("description"));
                    p.setPrice(Float.parseFloat(rs.getString("price")));
                    p.setImageURL(rs.getString("imageURL"));
                    ///For test purposes only //System.out.println("DBSetup @ line 323 " + p.getProductCode());
            }
            return p;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method gets the catalog categories to list as headings on catalog page.
     * @return ArrayList<String> object
     */
     public static ArrayList selectCatalogCategory() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ArrayList<String> p = new ArrayList();
        int count = 0;

        String query = "SELECT DISTINCT catalogCategory FROM Product "
                + " ORDER BY catalogCategory";
        
        String query2 = "SELECT DISTINCT catalogCategory FROM Product "
                + " ORDER BY catalogCategory";
        
        try {
            ps = connection.prepareStatement(query);
            ps2 = connection.prepareStatement(query2);
            rs = ps.executeQuery();
            rs2 = ps2.executeQuery();
            
            while( rs2.next() ) {
               ++count;
            }          
            ////////////For test purposes only //System.out.println("DBSetup @ line 363    " + count);            
            for ( int i = 0; i < count; i++ ) {
                if ( rs.next() ) {
                    
                    p.add(rs.getString("catalogCategory"));
                    System.out.println("DBSetup @ line 370     " +  p.get(i));
                }
            }
            System.out.println(p.size());
            return p;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * Method gets all users currently in the jmansales db.
     * @return ArrayList<Users> object.
     */
    public static ArrayList<User> selectUsers() {
        
        ArrayList<User> users = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM users ";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ResultSetMetaData mD = rs.getMetaData();
            int colCount = mD.getColumnCount();
            User user = null;
            
            for (int i = 0; i < colCount; i++) {
                if (rs.next()) {
                    user = new User();
                    user.setUserID(rs.getInt("userID"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setEmail(rs.getString("email"));
                    user.setAddr1(rs.getString("addr1"));
                    user.setAddr2(rs.getString("addr2"));
                    user.setCity(rs.getString("city"));
                    user.setStateRegion(rs.getString("state"));
                    user.setZipcode(rs.getInt("zip"));
                    user.setCountry(rs.getString("country"));
                    user.setRoleName(rs.getString("roleName"));
                    System.out.println(user.getFirstName());
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    } 
    /**
     * Method gets all of the products in the jmansales db.
     * @return ArrayList<Product> object
     */
    public static ArrayList<Product> selectProducts() {
        
        ArrayList<Product> products = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        int count = 0;

        String query = "SELECT * FROM product ORDER BY  catalogCategory, productName ASC ";
        
        String query2 = "SELECT * FROM product";    
        
        try {
            ps = connection.prepareStatement(query);
            ps2 = connection.prepareStatement(query2);
            rs = ps.executeQuery();
            rs2 = ps2.executeQuery();
            ResultSetMetaData mD = rs.getMetaData();
            int colCount = mD.getColumnCount();
            Product p = null;
            System.out.println("DBSetup@line448 " + colCount);
            
            while( rs2.next() ) {
               ++count;
            }       
            ///////For test purposes // System.out.println("DBSetup @ line 363    " + count);            
            for (int i = 0; i < count; i++) {
                if (rs.next()) {
                    p = new Product();
                    p.setProductCode(rs.getString("productCode"));
                    p.setProductName(rs.getString("productName"));
                    p.setCatalogCategory(rs.getString("catalogCategory"));
                    p.setDescription(rs.getString("description"));
                    p.setPrice(Float.parseFloat(rs.getString("price")));
                    p.setImageURL(rs.getString("imageURL"));
                    System.out.println("DBSetup @ line 461   " + p.getProductCode());
                    products.add(p);
                }
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    } 
    
    public static int selectOrder() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT orderNumber FROM orders";
        
        try {
            ps = connection.prepareStatement(query);            
            rs = ps.executeQuery();
            ArrayList<Integer> o = new ArrayList<>();
                while ( rs.next() ) {
                //o = new Order();
                    o.add(rs.getInt("orderNumber"));
                }
                    
                    ///For test purposes only //System.out.println("DBSetup @ line 323 " + p.getProductCode());
            
            System.out.println("DB @ line602 " + o);
            return o.size();
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Order> selectOrders(int userID) {
        
        ArrayList<Order> orders = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        System.out.println("order @ line 623 " + userID);
        String query = "SELECT * FROM orders WHERE userID = ?";   
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            ResultSetMetaData mD = rs.getMetaData();
            int colCount = mD.getColumnCount();
            Order o = null;
            System.out.println("DBSetup@line 663 " + colCount);          
            
                while (rs.next()) {
                    o = new Order();
                    o.setOrderNumber(rs.getInt("orderNumber"));
                    o.setDate(rs.getTimestamp("dateNow"));
                    o.setTaxRate(rs.getFloat("taxRate"));
                    o.setTotal(rs.getDouble("totalCost"));
                    o.setPaid(rs.getBoolean("paid"));
                    System.out.println("DBSetup @ line 644   " + o.getOrderNumber());
                    orders.add(o);
                }
           
            return orders;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    } 
    
    public static ArrayList<Order> selectAllOrders() {
        
        ArrayList<Order> orders = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        String query = "SELECT * FROM orders";   
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ResultSetMetaData mD = rs.getMetaData();
            int colCount = mD.getColumnCount();
            Order o = null;
            System.out.println("DBSetup@line 673 " + colCount);          
            
                while (rs.next()) {
                    o = new Order();
                    o.setOrderNumber(rs.getInt("orderNumber"));
                    o.setDate(rs.getTimestamp("dateNow"));
                    o.setUserID(rs.getInt("userID"));
                    o.setTaxRate(rs.getFloat("taxRate"));
                    o.setTotal(rs.getDouble("totalCost"));
                    o.setPaid(rs.getBoolean("paid"));
                    System.out.println("DBSetup @ line 683   " + o.getUserID());
                    orders.add(o);
                }
           
            return orders;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    } 
    
    public static ArrayList<OrderItems> selectOrderItems(int orderNumber) {
        
        ArrayList<OrderItems> items = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        int count = 0;

        String query = "SELECT * FROM orderItem WHERE orderNumber = ?"; 
        String query1 = "SELECT COUNT(*) FROM orderItem";
        
        try {
            ps = connection.prepareStatement(query);
            ps1 = connection.prepareStatement(query1);
            ps.setInt(1, orderNumber);
            rs = ps.executeQuery();
            rs1 = ps1.executeQuery();
            ResultSetMetaData mD = rs.getMetaData();
            int colCount = mD.getColumnCount();
            OrderItems item = null;
            System.out.println("DBSetup@line 722 " + colCount);
                 
            ///////For test purposes // System.out.println("DBSetup @ line 363    " + count);            
            while ( rs1.next() ) {
                count++;
            }
            //for(int i = 0; i < (count + 1); i++) {                
            
                while (rs.next()) {
                    item = new OrderItems();
                    item.setOrderNumber(rs.getInt("orderNumber"));
                    item.setProductCode(rs.getString("productCode"));
                    item.setQuantity(rs.getInt("quantity"));
                    System.out.println("DBSetup @ line 731   " + item.getProductCode());
                    items.add(item);
                }
            //}
            System.out.println("db @ line 735 " + items.size());
            return items;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    } 
}
