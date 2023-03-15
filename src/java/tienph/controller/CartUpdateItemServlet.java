/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tienph.dto.CartObject;
import tienph.dao.ClothesDAO;
import tienph.dto.ClothesDTO;

/**
 *
 * @author Hp
 */
public class CartUpdateItemServlet extends HttpServlet {
    private final String CART_DETAIL_PAGE = "./user/cartDetail.jsp";
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
        String url = CART_DETAIL_PAGE;
        try {
            //1. Customer gose to his/her cart place
            HttpSession session = request.getSession();
            if (session != null) {
                //2. Customer take cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {                    
                    //3. Get selected Item
                    int itemId = Integer.parseInt(request.getParameter("idProduct"));
                    int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
                    //4. Process
                    ClothesDTO dto = ClothesDAO.getAClothing(itemId, true);
                    cart.updateItemQuantity(dto, newQuantity);
                    int cartSize = cart.sizeCart();                    
                    session.setAttribute("CART_SIZE", cartSize);
                    session.setAttribute("CART", cart);                    
                    
                }
            }
        } catch (SQLException e) {
            log("CartUpdateItemServlet - SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("CartUpdateItemServlet - ClassNotFound" + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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

}
