/**
 * Edited by    : Jarrod Norris
 * Class        : ITIS 4166-UOL
 * Date         : 21 Sept 2017
 */



package utilities;

import javax.servlet.http.*;

public class CookieUtility {
    
    public static String getTheCookieValues (Cookie[] c, String cName) {
        
        String cValue = "";
        
        if ( c != null ) {
            for( Cookie cookie: c ) {
                if ( cName.equals ( cookie.getName() ) ) {
                   cValue = cookie.getValue();
                }
            }
        }
        return cValue;
    }   
}
