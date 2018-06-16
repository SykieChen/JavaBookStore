/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import entity.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BooksFacadeLocal;
import session.CartLocal;

/**
 *
 * @author sykie
 */
public class CtrlServlet extends HttpServlet {
    CartLocal cart;
    @EJB
    private BooksFacadeLocal booksFacade;

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
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            // identify source page
            String page = request.getParameter("page");
            switch(page){
                case "search":
                    ArrayList<ArrayList<String>> result = new ArrayList<>();
                    for (entity.Books i:this.booksFacade.search(request.getParameter("key"))){
                        ArrayList<String> line = new ArrayList<>();
                        line.add(i.getIsbn());
                        line.add(i.getTitle());
                        line.add(i.getAuthor());
                        line.add(i.getPress());
                        line.add(i.getPrice().toString());
                        result.add(line);
                    }
                    session.setAttribute("search_result", result);
                    session.setAttribute("keyword", request.getParameter("key"));
                    response.sendRedirect("Directory.jsp");
                    break;
                case "directory":
                    if ("search".equals(request.getParameter("submit"))){
                        response.sendRedirect("Search.jsp");
                    }
                    else{
                        // cart
                        if (request.getSession().getAttribute(request.getLocalAddr())==null){
                            cart = this.lookupCartLocal();
                            request.getSession().setAttribute(request.getLocalAddr(), this.cart);
                        }
                        else{
                            this.cart = (CartLocal)request.getSession().getAttribute(request.getLocalAddr());
                        }

                        String[] choices = request.getParameterValues("choose");

                        for (String c:choices){
                            this.cart.add(c);
                        }
                        ArrayList<ArrayList<String>> result2 = new ArrayList<>();
                        for (CartItem i:this.cart.getCartItems()){
                            if (i.getCount()<=0) {
                                continue;
                            }
                            ArrayList<String> line = new ArrayList<>();
                            line.add(i.getIsbn());
                            line.add(this.booksFacade.getByIsbn(i.getIsbn()).getTitle());
                            line.add(this.booksFacade.getByIsbn(i.getIsbn()).getPrice().toString());
                            line.add(String.valueOf(i.getCount()));
                            result2.add(line);
                        }
                        session.setAttribute("cart", result2);
                        response.sendRedirect("Cart.jsp");
                    }
                    break;
                case "cart":
                    out.println(request.getParameter("submit"));
                    if ("order".equals(request.getParameter("submit"))){
                        if (request.getSession().getAttribute(request.getLocalAddr())==null){
                            cart = this.lookupCartLocal();
                            request.getSession().setAttribute(request.getLocalAddr(), this.cart);
                        }
                        else{
                            this.cart = (CartLocal)request.getSession().getAttribute(request.getLocalAddr());
                        }
                        ArrayList<ArrayList<String>> result3 = new ArrayList<>();
                        for (CartItem i:this.cart.getCartItems()){
                            i.setCount(Integer.parseInt(request.getParameter(i.getIsbn())));
                            if(i.getCount()<=0){
                                continue;
                            }
                            ArrayList<String> line = new ArrayList<>();
                            line.add(i.getIsbn());
                            line.add(this.booksFacade.getByIsbn(i.getIsbn()).getTitle());
                            line.add(this.booksFacade.getByIsbn(i.getIsbn()).getPrice().toString());
                            line.add(String.valueOf(i.getCount()));
                            result3.add(line);
                        }
                        session.setAttribute("cart", result3);
                        session.setAttribute("address", request.getParameter("address"));
                        response.sendRedirect("Order.jsp");
                    }
                    else if ("return".equals(request.getParameter("submit"))){
                        response.sendRedirect("Directory.jsp");
                    }
                    else{
                        response.sendRedirect("Search.jsp");
                    }
                    break;
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
        processRequest(request, response);
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

    private CartLocal lookupCartLocal() {
        try {
            Context c = new InitialContext();
            return (CartLocal) c.lookup("java:global/BookShop/BookShop-ejb/Cart!session.CartLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
