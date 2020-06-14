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
import classes.Payment;
import classes.User;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.DB;


@WebServlet(name = "OrderController", loadOnStartup = 1, urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

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
        
        String url, path = "", location, action = request.getParameter("action");
        HttpSession session = request.getSession();
        if ( action != null ) {
            if ( action.equals("Update")){
                    update(request, response);
                    path = "cart";
            }         
             // Sets url to go to static order.jsp.
            if ( action.equals("Confirm Order") ) {
                getPaymentType(request, response);
                setOrder(request,response);
                System.out.println("order @ line 67 " + ((Order) session.getAttribute("o")).getOrderNumber());                
                location = " => Catolog => item => Cart => Order";
                request.setAttribute("location", location);
                path = "order";
            }

            if ( action.equals("Back To Cart") ) {
                location = " => Catolog => item => Cart";
                request.setAttribute("location", location);
                path = "cart";
            } 

            if ( action.equals("Select Payment") ) {
                //getPaymentType(request, response);
                Payment pymt = new Payment();
                ArrayList<Integer> yr = pymt.getYear();
                ArrayList<Integer> mnth = pymt.getMonth();            
                session.setAttribute("mnth", mnth);
                session.setAttribute("yr", yr);
                path = "payment";
            }

            if ( action.equals("Purchase") ) {
                System.out.println(action);
                LocalDateTime date = LocalDateTime.now();
                Order o = (Order) session.getAttribute("o");
                int num = DB.selectOrder();
                ArrayList<OrderItems> items = (ArrayList) session.getAttribute("items");
                Timestamp t = Timestamp.valueOf(date);
                System.out.println("order @ line 87 " + num);
                session.setAttribute("date", t);
                DB.insert(o);
                for ( OrderItems item : items ) {
                    
                    item.setOrderNumber(num + 1);
                    DB.insert(item);
                    
                }
                location = " => Catolog => item => Cart => Order => Receipt";
                request.setAttribute("location", location);
                session.removeAttribute("theShoppingCart");
                session.removeAttribute("totals");
                path = "invoice";
            }

            if ( action.equals("History") ) {
                
                location = " => Order History";
                request.setAttribute("location", location);
                User user = (User) session.getAttribute("theUser");
                ArrayList<Order> orders = (ArrayList) DB.selectOrders(user.getUserID());
                session.setAttribute("orders", orders);
                path = "orderHistory";
            }
            
            if ( action.equals("listItems") ) {                
                ArrayList<OrderItems> items = (ArrayList) DB.selectOrderItems(Integer.parseInt(request.getParameter("orderNumber")));
                for ( int i = 0; i < items.size(); i++ ) {
                    items.get(i).setProduct(items.get(i).getProductCode());
                    System.out.println("order @ line 113 " + items.get(i).getProduct().getProductName());
                    //products.add((Product) DB.selectProduct(items.get(i).getProductCode()));
                }
                session.setAttribute("orderItems", items);
                path = "items";
            }
            
            
            url = "/WEB-INF/views/" + path + ".jsp";
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
    
    
    // <editor-fold defaultstate="collapsed" desc="totalCart method. Click on the + sign on the left to edit the code.">
    /**
     * Method to total the contents of the cart.
     * @param request
     * @param response 
     * @throws javax.servlet.ServletException 
     * @throws java.io.IOException 
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
    }//</editor-fold
    
    
    // <editor-fold defaultstate="collapsed" desc="Update method. Click on the + sign on the left to edit the code.">
    /**
     * Method to update the cart quantity, price, and totals.
     * @param request
     * @param response 
     * @throws javax.servlet.ServletException 
     * @throws java.io.IOException 
     */
    private void update(HttpServletRequest request, HttpServletResponse response) 
                        throws ServletException, IOException{
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("theShoppingCart"); 
        String[] pc = (request.getParameterValues("productList[]"));            
            
            System.out.print("OrderController @ line 194   " + pc.length);
            for (int i = 0; i < pc.length; i++) {
                String[] quanty = request.getParameterValues("quantity");
                //////////////////////////For testing purposes only //System.out.println("OrderController @ line 151   " + pc[i]);                
                if( pc[i].equals(cart.getItems().get(i).getProduct().getProductCode()) && 
                        quanty[i].equals(cart.getItems().get(i).getQuantity())) {
                } else {
                    if(quanty[i].equals("0")) {
                        System.out.println("OrderController @ line 202   " );
                        OrderItems item = cart.getItems(i);
                        session.removeAttribute("totals");
                        cart.getItems().remove(item);
                        System.out.println("OrderController @ line 206 " + cart.getItems().size());
                        //break;
                    } else {
                        cart.getItems().get(i).setQuantity(Integer.parseInt(quanty[i]));
                        totalCart(request, response);
                    }
                }
                
            }  
        }// </editor-fold>

    private void getPaymentType(HttpServletRequest request, HttpServletResponse response) 
                                    throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("theUser");
        Payment pymt = new Payment();
        pymt.setCreditCardType(request.getParameter("creditCardType"));
        pymt.setCardNumber(request.getParameter("cardNumber"));
        pymt.setExpireMonth(request.getParameter("month"));
        pymt.setExpireYear(request.getParameter("year"));
        pymt.setCvv(request.getParameter("cvv"));
        user.setPaymentType(pymt);
        System.out.println("order @ line 228 " + pymt.getCvv());
        DB.insertPayment(user);
    }
    
    private void setOrder(HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<OrderItems> items = (ArrayList<OrderItems>) ((Cart) session.getAttribute("theShoppingCart")).getItems();
        System.out.println("Order @ line 236 " + items.get(0).getQuantity());
        Order o = new Order();
        o.setOrderNumber( DB.selectOrder() + 1 );
        o.setItems(items);
        o.setTaxRate(7);
        o.setTotal((Double) session.getAttribute("plusTax"));
        o.setPaid(true);
        o.setUser((User) session.getAttribute("theUser"));
        session.setAttribute("o", o);
        session.setAttribute("items", items);
    }
}

