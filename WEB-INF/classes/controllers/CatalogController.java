/*
 * Document   : item
 * Created on : Oct 2, 2017, 10:13:09 PM
 * Author     : Jarrod Norris
 * Class      : ITIS 4166-UOL
 */
package controllers;

import classes.Cart;
import classes.Order;
import classes.OrderItems;
import classes.Product;
import classes.SecurityQuestion;
import classes.User;
import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.DB;


@WebServlet(name = "CatalogController", loadOnStartup = 1, urlPatterns = {"/catalog"})
public class CatalogController extends HttpServlet {

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
        
        // This obtains the value of action from the request object.
        String url, path = "", location, action = request.getParameter("action");
        HttpSession session = request.getSession();        
        
        if ( action != null ) {
        // This section calls the itemDisplay method to display the information 
        // of the selected item in the item.jsp view.
            if ( action.equals("displayItem") ) {
                location = " => Catalog => Item";
                request.setAttribute("location", location);
                path = "item";
                itemDisplay(request, response);            
            }
            // Sets url to stay on cart page after update of quantity.
            if ( action.equals("Add") ) {            
                // Currently just changes value but will add functionality in future iterations.
                session.setAttribute("action", action);
                checkLoginStatus(request, response);
                User user = (User) session.getAttribute("theUser");
                System.out.println("We are @ catalogController line 149 " + user.getLastName());
                if ( !user.getFirstName().equals("") ) {
                    System.out.println("catalogController @ line 65 " );
                    location = " => Catolog => item => Cart";
                    request.setAttribute("location", location);
                    add(request, response);
                    path = "cart";
                }             
            }
            // Directs to about, contact, or index
            if ( action.equals("about") ) {
                location = " => About Us";
                request.setAttribute("location", location);
                path = "about";            
            }
            if ( action.equals("contact") ) {
                location = " => Contact Us";
                request.setAttribute("location", location);
                path = "contact";            
            }
            if ( action.equals("index") ) {
                url = "/index.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);

            } 
            // Handles user sign in from user navigation bar
            if ( action.equals("Sign-In") ) {
                session.setAttribute("action", action);
                checkLoginStatus(request, response);  // Calls checkLoginStatus          
                User user = (User) session.getAttribute("theUser");
                //String fun = (String) session.getAttribute("action");
                if( user != null && !user.getFirstName().equals("") ) {                
                    url = "/index.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                } 
//                else {
//                    url = "/catalog?action=" + fun ;
//                    System.out.println( url);
//                    getServletContext().getRequestDispatcher(url).forward(request, response);
//                }
            }
            
            if ( action.equals("viewAccountSettings") ) {
                User user = (User) session.getAttribute("theUser");
                //System.out.println("order@viewAllAccoounts @ line114 with user = " + user.getUsername());
                if ( user == null ) {
                    System.out.println("order@viewAllAccoounts with user == null");
                    session.setAttribute("action", action);
                    url = "/WEB-INF/views/Sign-In.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);  
                } else {
                    
                    SecurityQuestion fav = (SecurityQuestion) DB.getQA(user.getUsername());
                    session.setAttribute("fav", fav);
                    url = "/WEB-INF/commonFiles/signup.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response); 
                }
            }
            // Removes user when signing out && Cleans out cart??? Will ad to cookie and store on user computer in future iteration.
            if ( action.equals("Sign-Out") ) {
                session.removeAttribute("theUser");
                if( session.getAttribute("theShoppingCart") != null) {
                    session.removeAttribute("theShoppingCart");
                    session.removeAttribute("totals");
                }
                url = "/index.jsp";
                
            } else {      
                url = "/WEB-INF/views/" + path + ".jsp";
            }
            getServletContext().getRequestDispatcher(url).forward(request, response);
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
     * Method to check login status
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void checkLoginStatus(HttpServletRequest request, HttpServletResponse response) 
                                    throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("theUser");
        System.out.println("We are @ catalogController line 147 " + user);
        if ( user == null || user.getFirstName().equals("") ) {
            String url = "/WEB-INF/views/Sign-In.jsp";
            ///////////////////////////////////////////////////////For test purposes only // System.out.println("We are @ catalogController line 149 " + url);
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } 
        else {
            System.out.println("We have a problem @ catalogController line 156");            
        }
    }//</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="itemDisplay method. Click on the + sign on the left to edit the code.">
    /**
     * Method displays the selected product on items page view.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void itemDisplay(HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        // Gets current product code to run through switch and select proper Product object.
        String pC = request.getParameter("productCode");
        HttpSession session = request.getSession();
        ///////////////////////////////////////////////////////////////For test purposes //System.out.println(pC);
        Product p1 = new Product();
        ArrayList<Product> s = (ArrayList) session.getAttribute("t");
        // For loop to select proper Product object to set for current request object.
        for (int i = 0; i < s.size(); i++ ) {
            if(pC != null && pC.equals(s.get(i).getProductCode())){
                p1 = s.get(i);
            }
        }
                session.setAttribute("p1", p1);
                session.setAttribute("pC", pC);
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="add method. Click on the + sign on the left to edit the code.">
    /**
     * Method adds items to the cart, checks if already in cart and updates quantity and price.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void add(HttpServletRequest request, HttpServletResponse response) 
                        throws ServletException, IOException{
        HttpSession session = request.getSession(); 
        boolean isEqual = false, isNull = false;
        String url, path, action = request.getParameter("action");
        int quant = 1, position = 0;
        OrderItems item = new OrderItems();        
        Product p = (Product) session.getAttribute("p1");
        Cart cart = (Cart) session.getAttribute("theShoppingCart");
        if ( ((Cart) session.getAttribute("theShoppingCart")) == null ) {
            isNull = true;
        }
        ///////////////////System.out.println("catalogController @ line 216 " + isNull);
        /////For test purposes only //System.out.println( ( (ArrayList) ((Cart) session.getAttribute("theShoppingCart")).getItems()).isEmpty() );
        if ( isNull ) {
            
            if ( action != null && action.equals("Add") ) {
                cart = new Cart();
                item.setQuantity(quant);
                item.setProduct(p);
                cart.addItem(item);
                session.setAttribute("theShoppingCart", cart);
                totalCart(request, response);
                url = "/WEB-INF/views/cart.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);                
                
            } else {
                path = "cart";
            }                       
                      
        } else {
            if ( action != null && action.equals("Add") ) {
            System.out.println("catalogController @ line 239 " + cart);
            for ( int i = 0; i < cart.getItems().size(); i++ ) {
                 if(cart.getItems().get(i).getProduct().getProductCode().equals(p.getProductCode())){
                    isEqual = true;
                    position = i;
                    path = "cart";
                    break;
                 } 
            }
            if ( isEqual ) {
                cart.getItems().get(position).setQuantity( (cart.getItems().get(position).getQuantity()) + 1);
                session.setAttribute("theShoppingCart", cart);
                totalCart(request, response);
                path = "cart";
            } else {          
                item.setQuantity(quant);
                item.setProduct(p);
                cart.addItem(item);                
                session.setAttribute("theShoppingCart", cart);
                totalCart(request, response);
                url = "/WEB-INF/views/cart.jsp";
                System.out.println(url);
                getServletContext().getRequestDispatcher(url).forward(request, response);    
            }
            /////For test purposes only //System.out.println("catalogController @ line 260 " + p.getProductCode());            
            }
        }        
    }//</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="totalCart method. Click on the + sign on the left to edit the code.">
    /**
     * Method totals all items in cart for grand sub-totaL, tax, and grand total.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void totalCart(HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException{
        
        HttpSession session = request.getSession();
        double totalCart = 0.0, plusTax = 0.0;
        Order order = new Order();
        float taxRate = order.getTaxRate();
        String totals, total, tax;
        Cart cart = (Cart) session.getAttribute("theShoppingCart");
        //System.out.println(cart.getItems().size());       
        for(int i = 0; i < cart.getItems().size(); i++) {
            totalCart += (cart.getItems().get(i).getTotal());
        }
        tax = cart.getTax(totalCart * (taxRate * .01));
        total = cart.getTotal((totalCart * (taxRate * .01)) + totalCart);
        plusTax = (totalCart * (taxRate * .01)) + totalCart;
        System.out.println(taxRate * .01);
        totals = cart.getCartTotal(totalCart);
        session.setAttribute("plusTax", plusTax);
        session.setAttribute("totals", totals);
        session.setAttribute("total", total);
        session.setAttribute("tax", tax);        
    }//</editor-fold>
} 
    
