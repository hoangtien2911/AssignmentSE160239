/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
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
public class AddToCartServlet extends HttpServlet {
    private final String HOME_PAGE = "DispatchController?btAction";
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
        String url = HOME_PAGE;
        try {
            int id = Integer.parseInt(request.getParameter("clothingID"));            
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            ClothesDTO dto = ClothesDAO.getAClothing(id, true);
            HttpSession session = request.getSession();            
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }
            cart.addItemToCart(dto, quantity);            
            session.setAttribute("CART", cart);
            int cartSize = cart.sizeCart();            
            session.setAttribute("CART_SIZE", cartSize);
            request.setAttribute("MSG", dto.getName() + " has been added to your card!");            
//            HashMap<ClothesDTO, Integer> cart = (HashMap) session.getAttribute("CART");
//            for (ClothesDTO clothesDTO : cart.keySet()) {
//                System.out.println(clothesDTO.toString());
//            }
//            if (cart == null) {
//                cart = new HashMap<>();
//                cart.put(dto, quantity);
//            } else {
//                System.out.println(cart.size());
//                if (cart.containsKey(dto)) {
//                    System.out.println("hi");
//                    int newQuantity = cart.get(dto) + quantity;
//                    cart.put(dto, newQuantity);
//                } else {
//                    cart.put(dto, quantity);
//                    System.out.println("no");
//                }
//            }
        } catch (SQLException e) {
            log("AddToCartServlet - SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("AddToCartServlet - ClassNotFound" + e.getMessage());
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
