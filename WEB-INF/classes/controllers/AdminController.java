/*
 * Document   : AdminController
 * Created on : Oct 2, 2017, 10:13:09 PM
 * Author     : Jarrod Norris
 * Class      : ITIS 4166-UOL
 */
package controllers;

import classes.Order;
import classes.OrderItems;
import classes.Product;
import classes.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.DB;


@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action"), url, messageAdmin;
        HttpSession session = request.getSession();
        
        if ( action != null ) {
        
            if( action.equals("Log-In") ) {

                String username = request.getParameter("username");
                String password = request.getParameter("password");
                User user = new User();
                user = (User) DB.userSignin(username, password);
                if (user == null ) {
                    messageAdmin = "Username and Password don't match, please try again or sign up for service.";
                    session.setAttribute("messageAdmin", messageAdmin);
                    url = "/WEB-INF/commonFiles/webmaster.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                } else {                    
                    if ( user.getRoleName().equals("administrator") ) {
                        session.setAttribute("theUser", user);
                        System.out.println("IOUserDB@line53" + session.getAttribute("action"));
                        url = "/WEB-INF/commonFiles/dashboard.jsp";
                        getServletContext().getRequestDispatcher(url).forward(request, response);
                    } else {
                        messageAdmin = "You are not authorized!";
                        session.setAttribute("messageAdmin", messageAdmin);
                        url = "/WEB-INF/commonFiles/webmaster.jsp";
                        getServletContext().getRequestDispatcher(url).forward(request, response);
                    }
                }     
            }
            // Creates an array of all users in database and directs to allusers view
            if ( action.equals("View All Users") ) {
                ArrayList<User> users = (ArrayList) DB.selectUsers();
                session.setAttribute("theUsers", users);
                url = "/WEB-INF/commonFiles/allusers.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            if ( action.equals("View All Products") ) {
                ArrayList<Product> products = (ArrayList) DB.selectProducts();
                session.setAttribute("products", products);
                System.out.println("admin @ line73 ");
                url = "/WEB-INF/commonFiles/allproducts.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            if ( action.equals("Dashboard") ) {
                session.removeAttribute("p");
                session.removeAttribute("u");
                url = "/WEB-INF/commonFiles/dashboard.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response); 
            }

            if ( action.equals("Catalog") ) {
                url = "/IOProductDB?action=loadCatalog";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            if ( action.equals("webmaster") ) {
                checkLoginStatus(request, response);                
            }
            
            if ( action.equals("Create Account") ) {
            
                int uID = 0;
                String uname = request.getParameter("username");
                String pWord = request.getParameter("pWord");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String addr1 = request.getParameter("addr1");
                String addr2 = request.getParameter("addr2");
                String city = request.getParameter("city");
                String stateRegion = request.getParameter("stateRegion");
                int zipcode = Integer.parseInt(request.getParameter("zipcode"));
                String country = request.getParameter("country");
                String roleName = request.getParameter("roleName");
                System.out.println(country);

                User user = new User(uID, uname, pWord, firstName, lastName, email, addr1, addr2, city, stateRegion, zipcode, country, roleName);
                DB.insert(user);

                url = "/WEB-INF/commonFiles/dashboard.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            
            if ( action.equals("getUser") ) {
                int uID = Integer.parseInt(request.getParameter("userID"));
                System.out.println("adminline 128 " + uID);
                User user = new User();
                user = (User) DB.selectUser(uID);
                System.out.println("Admin @ line 121 " + uID + " username: " + user.getUsername() );
                session.setAttribute("u", user);
                url = "/WEB-INF/commonFiles/dashboard.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
        
            if ( action.equals("Edit") ) {
                User u = (User) session.getAttribute("u");
                u.setUserID(Integer.parseInt(request.getParameter("userID")));
                u.setUsername(request.getParameter("username"));
                u.setPassword(request.getParameter("pWord"));
                u.setFirstName(request.getParameter("firstName"));
                u.setLastName(request.getParameter("lastName"));
                u.setEmail(request.getParameter("email"));
                u.setAddr1(request.getParameter("addr1"));
                u.setAddr2(request.getParameter("addr2"));
                u.setCity(request.getParameter("city"));
                u.setStateRegion(request.getParameter("stateRegion"));
                u.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
                u.setCountry(request.getParameter("country"));
                u.setRoleName(request.getParameter("roleName"));
                
                DB.update(u);
                
                url = "/admin?action=View All Users";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            if ( action.equals("Delete") ) {
                User u = (User) session.getAttribute("u");
                DB.delete(u);
                session.removeAttribute("u");
                url = "/WEB-INF/commonFiles/dashboard.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            
            if ( action.equals("View All Orders")) {
                ArrayList<Order> allOrders = (ArrayList) DB.selectAllOrders();
                session.setAttribute("allOrders", allOrders);
                url = "/WEB-INF/commonFiles/allorders.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            
            if ( action.equals("listItems") ) {
                System.out.println("admin @ line 173 " + Integer.parseInt(request.getParameter("orderNumber")));
                ArrayList<OrderItems> items = (ArrayList) DB.selectOrderItems(Integer.parseInt(request.getParameter("orderNumber")));
                System.out.println("admin @ line 175 " + items.get(0).getProductCode());
                //ArrayList<Product> products = new ArrayList<>();
                for ( int i = 0; i < items.size(); i++ ) {
                    items.get(i).setProduct(items.get(i).getProductCode());
                    System.out.println("admin @ line 179 " + items.get(i).getProduct().getProductName());
                    //products.add((Product) DB.selectProduct(items.get(i).getProductCode()));
                }
                //session.setAttribute("products", products);
                session.setAttribute("orderItems", items);
                url = "/WEB-INF/commonFiles/listItems.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            
            if ( action.equals("Sign Out") ) {
                session.removeAttribute("theUser");
                url = "/index.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="checkLoginStatus method. Click on the + sign on the left to edit the code.">
    /**
     * Method checks login status of administrator.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void checkLoginStatus(HttpServletRequest request, HttpServletResponse response) 
                                    throws ServletException, IOException {
        HttpSession session = request.getSession();
        String url, messageAdmin;
        User user = (User) session.getAttribute("theUser");
        System.out.println("We are @ AdminController line 157 " + user);
        if ( user == null || user.getFirstName().equals("") ) {
            url = "/WEB-INF/commonFiles/webmaster.jsp";
            /////For test purposes only // System.out.println("We are @ AdminController line 160 " + url);
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } 
        else if ( !user.getRoleName().equals("administrator") ) {
            System.out.println("We have a problem @ AdminController line 256"); 
            messageAdmin = "You are not a valid administrator, please contact the website owner if you feel " +
                            "you have received this message in error.";
            session.setAttribute("messageAdmin", messageAdmin);
            url = "/WEB-INF/views/error.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } else {      
            url = "/WEB-INF/commonFiles/dashboard.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
    }// </editor-fold>
}
