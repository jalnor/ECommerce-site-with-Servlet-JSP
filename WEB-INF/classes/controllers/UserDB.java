/*
 * Document   : item
 * Created on : Oct 2, 2017, 10:13:09 PM
 * Author     : Jarrod Norris
 * Class      : ITIS 4166-UOL
 */
package controllers;

import classes.SecurityQuestion;
import classes.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.DB;


@WebServlet(name = "IOUserDB", urlPatterns = {"/IOUserDB"})
public class UserDB extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String url, message;
        if( action != null ) {
            if( action.equals("Sign-In") ) {

                String username = request.getParameter("username");
                String password = request.getParameter("password");
                User user = (User) DB.userSignin(username, password);
                if (user == null ) {
                    message = "Username and Password don't match, please try again or sign up for service.";
                    session.setAttribute("message", message);
                    url = "/WEB-INF/views/Sign-In.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                } else {
                    session.setAttribute("theUser", user);
                    //System.out.println("UserDB@line 53 " + (Stringsession.getAttribute(action));
                    url = "/catalog?action=" + (String) session.getAttribute("action");
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                }            
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
                    String question = request.getParameter("securityQuestion");
                    String answer = request.getParameter("answer");
                    String roleName = "customer";
                    System.out.println(country);

                    User user = new User(uID, uname, pWord, firstName, lastName, email, addr1, addr2, city, stateRegion, zipcode, country, roleName);
                    SecurityQuestion fav = new SecurityQuestion(user.getUserID(), question, answer);
                    DB.insert(user);
                    DB.insertSecurityQuestion(fav);

                    url = "/WEB-INF/commonFiles/Sign-In.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                }

            if ( action.equals("Update") ) {
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
            
            if ( action.equals("Edit Account") ) {
                User u = new User();
                SecurityQuestion sq = new SecurityQuestion();
                System.out.println("WTF: " + (Integer.parseInt(request.getParameter("userID"))));
                u.setUserID(Integer.parseInt(request.getParameter("userID")));
                u.setUsername(request.getParameter("username"));
                u.setPassword(request.getParameter("password"));
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
                System.out.println("UserDB@line 125 " + u.getFirstName());
                sq.setUserID(Integer.parseInt(request.getParameter("userID")));
                sq.setQuestion(request.getParameter("securityQuestion"));
                sq.setAnswer(request.getParameter("answer"));
                DB.update(u);
                DB.updateSecurityQuestion(sq);
                
                url = "/catalog?action=viewAccountSettings";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            
            

            if ( action.equals("Go Back") ) {
                url = "/index.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            if ( action.equals("create") ) {            
                url = "/WEB-INF/commonFiles/signup.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);           
            }        

            if ( action.equals("Continue Shopping") ) {
                System.out.println("IOUserDB @ line 95");
                url = "/IOProductDB?action=loadCatalog";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            
            if (action.equals("pwordLookUp") ) {
                url = "/WEB-INF/views/lostPword.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            
            if ( action.equals("getHint") ) {
                String uname = request.getParameter("userName");
                String q = request.getParameter("securityQuestion");
                String p = request.getParameter("answer");
                String forgotten = (String) DB.getHint(uname, q, p);
                String begin = "Your password is: ";
                System.out.println("IOUser@line133 " + forgotten);
                if ( forgotten == null || forgotten.equals("")) {
                    
                    forgotten = "Your username &/or question &/or answer are incorrect.";
                    System.out.println("IOUser@line135 " + forgotten);
                    request.setAttribute("forgotten", forgotten); 
                } else { 
                    forgotten = begin + forgotten;
                    System.out.println("IOUser@line139 " + forgotten);
                    request.setAttribute("forgotten", forgotten);
                }
                
                url = "/WEB-INF/views/lostPword.jsp";
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

}
