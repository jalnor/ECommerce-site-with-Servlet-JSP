/*
 * Document   : item
 * Created on : Nov 9, 2017, 10:13:09 PM
 * Author     : Jarrod Norris
 * Class      : ITIS 4166-UOL
 */
package controllers;

import classes.Product;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.DB;


@WebServlet(name = "IOProductDB", urlPatterns = {"/IOProductDB"})
public class ProductDB extends HttpServlet {

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
        
        String action = request.getParameter("action"), location;
        HttpSession session = request.getSession();
        String url;
        
        if (action != null && action.equals("loadCatalog") || action.equals("Continue Shopping")) {
            
            location = "=> Catalog";
            request.setAttribute("location", location);
            ArrayList<Product> t  = (ArrayList) DB.selectProducts();
            ArrayList<String> y  = (ArrayList) DB.selectCatalogCategory();
            session.setAttribute("t", t);
            session.setAttribute("y", y);
            url = "/WEB-INF/views/catalog.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
       
        if ( action.equals("Add")) {
            addProduct(request, response);
            url = "/WEB-INF/commonFiles/dashboard.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
        if ( action.equals("getProduct") ) {
                String pCode = request.getParameter("productCode");
                System.out.println("ProductDB @ line 79 " + pCode);
                Product p = DB.selectProduct(pCode);
                System.out.println("ProductDB @ line 79 " + p.getProductName());
                session.setAttribute("p", p);
                url = "/WEB-INF/commonFiles/dashboard.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
        }            
            
        if ( action.equals("Edit") ) {
                Product p = (Product) session.getAttribute("p");
                p.setProductCode(request.getParameter("pCode"));
                p.setProductName(request.getParameter("pName"));
                p.setCatalogCategory(request.getParameter("cCat"));
                p.setDescription(request.getParameter("desc"));
                p.setPrice(Float.parseFloat(request.getParameter("price")));
                p.setImageURL(request.getParameter("imageurl"));
                DB.update(p);
                url = "/admin?action=View All Products";
                getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
        if ( action.equals("Delete") ) {
            Product p = (Product) session.getAttribute("p");            
            DB.delete(p);
            session.removeAttribute("p");
            url = "/WEB-INF/commonFiles/dashboard.jsp";
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
    
    // <editor-fold defaultstate="collapsed" desc="loadArray method for catalog. Click on the + sign on the left to edit the code.">
    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void addProduct(HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException{
              
            String pcode = request.getParameter("pCode");
            String pname = request.getParameter("pName");
            String cCat = request.getParameter("cCat");
            String desc = request.getParameter("desc");
            float price = Float.parseFloat(request.getParameter("price"));
            String image = request.getParameter("imageurl");
            System.out.println("IOProductDB @ line 105" + pcode);
            Product p = new Product(pcode, pname, cCat, desc, price, image);
            
            System.out.println("IOProductDB @ line 108" + p.getProductCode());
            DB.insert(p);           
    }// </editor-fold>
       
}
