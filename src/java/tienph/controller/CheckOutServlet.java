/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tienph.dao.OrderDAO;
import tienph.dto.AccountDTO;
import tienph.dto.CartObject;
import tienph.dto.ClothesDTO;

/**
 *
 * @author Hp
 */
public class CheckOutServlet extends HttpServlet {
    private final String LOGIN_PAGE = "./user/login.html";
    private final String ORDER_PAGE = "DispatchController?btAction=OrderHistory" ;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            HttpSession session = request.getSession();
            AccountDTO user = (AccountDTO) session.getAttribute("ACCOUNT_USER");
            if (user != null) {
                url = ORDER_PAGE;
                int userId = user.getAccID();
                CartObject cart = (CartObject) session.getAttribute("CART");
                HashMap<ClothesDTO, Integer> cartList = (HashMap<ClothesDTO, Integer>) cart.getItems();
                Date dateInProcess = new Date(System.currentTimeMillis());
                int result = OrderDAO.insertOrder(userId, cartList, dateInProcess);
                if (result > 0) {                    
                    session.removeAttribute("CART_SIZE");
                    session.removeAttribute("CART");
                }                
            }
        } catch (SQLException e) {
            log("CheckOutServlet - SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("CheckOutServlet - ClassNotFound" + e.getMessage());
        }  finally {
            response.sendRedirect(url);
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
