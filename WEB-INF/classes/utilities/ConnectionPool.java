/**
 * @author      : Jarrod Norris
 * Class        : ITIS 4166-UOL
 * Date         : 9 Nov. 2017
 */
package utilities;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    /**
     * @Constructor creates initial context based on context.xml then 
     * loads dataSource
     */
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/jmansales");
            //System.out.println("I made it here!" + dataSource);
        } catch (NamingException e) {
            System.out.println(e);
        }
    }
    /**
     * Method creates a pool for connections.
     * @return pool
     */
    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }
    /**
     * Method returns the connection established through dataSource
     * @return dataSource
     */
    public Connection getConnection() {
        try {
            //System.out.println("I made it here!" + dataSource);
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    /**
     * Method frees up the connection on completion
     * @param c 
     */
    public void freeConnection(Connection c) {
        try {
            //System.out.println("I made it here!" + dataSource);
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}