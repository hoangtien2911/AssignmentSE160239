/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tienph.dao.ClothesDAO;
import tienph.dao.OrderDAO;
import tienph.dto.ClothesDTO;
import tienph.dto.OrderDTO;
import tienph.dto.OrderDetailDTO;

/**
 *
 * @author Hp
 */
public class DetailOrderServlet extends HttpServlet {

    private final String DETAIL_ORDER_PAGE = "orderDetail.jsp";

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
        String url = DETAIL_ORDER_PAGE;
        try {
            //Get parameters            
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            //Get order by order id call DAO
            OrderDTO orderDto = OrderDAO.getOrderByOrderId(orderId);            
            //Get list order detail by order id  Call DAO
            ArrayList<OrderDetailDTO> listDetailOrder = OrderDAO.getAllOrderDetailsById(orderId);            
            
            //Create list product have in list detail
            ArrayList<ClothesDTO> listClothes = new ArrayList<>();

            for (OrderDetailDTO orderDetailDTO : listDetailOrder) {
                //CALL DAO of clothes and add product to list with each detailID                 
                ClothesDTO dtoCloth = ClothesDAO.getAClothing(orderDetailDTO.getClothID());                
                listClothes.add(dtoCloth);
            }            

            request.setAttribute("ORDER_INFOR", orderDto);
            request.setAttribute("LIST_DETAIL_ORDER", listDetailOrder);
            request.setAttribute("LIST_CLOTHES_OF_DETAIL", listClothes);
            request.setAttribute("SIZE_OF_LIST", listClothes.size());
        } catch (SQLException e) {
            log("DetailOrderServlet - SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("DetailOrderServlet - ClassNotFound: " + e.getMessage());
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
