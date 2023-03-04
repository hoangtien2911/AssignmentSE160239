/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tienph.dao.OrderDAO;
import tienph.dto.OrderDTO;
import tienph.utils.MyUtils;

/**
 *
 * @author Hp
 */
public class UpdateOrderAdminServlet extends HttpServlet {

    private final String MANAGE_ORDERS_PAGE = "AdminController?btAction=ViewOrders";

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
        String url = MANAGE_ORDERS_PAGE;
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            String newShipDate = request.getParameter("newShipDate");
            String status = request.getParameter("newStatus");
            int newStatus = 1;
            if (status != null) {
                if (status.equals("Processing")) {
                    newStatus = 1;
                } else if (status.equals("Completed")) {
                    newStatus = 2;
                }
            }

            //get Para for rewriting url
            String lastFilterAccId = request.getParameter("lastFilterAccId");
            String lastFilterStatus = request.getParameter("lastFilterStatus");
            String lastFilterDateFrom = request.getParameter("lastFilterDateFrom");
            String lastFilterDateTo = request.getParameter("lastFilterDateTo");
            //Call DAO
            Date deliveryDate = new Date(MyUtils.parse(newShipDate).getTime());
            OrderDAO.updateOrderById(orderId, deliveryDate, newStatus);
            
                url = url + "&DateFrom=" + lastFilterDateFrom + "&DateTo=" + lastFilterDateTo
                        + "&txtStatus=" + lastFilterStatus + "&accId=" + lastFilterAccId;            
        } catch (SQLException e) {
            log("ViewOrdersServlet - SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("ViewOrdersServlet - ClassNotFound" + e.getMessage());
        } finally {
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
