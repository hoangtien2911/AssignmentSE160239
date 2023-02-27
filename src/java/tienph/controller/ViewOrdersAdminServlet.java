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
public class ViewOrdersAdminServlet extends HttpServlet {

    private final String MANAGE_ORDERS_PAGE = "./admin/manageOrders.jsp";

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
            String txtStatus = request.getParameter("txtStatus");
            String from = request.getParameter("DateFrom");
            String to = request.getParameter("DateTo");
            int status = 0;
            if (txtStatus != null) {
                if (txtStatus.equals("Processing")) {
                    status = 1;
                } else if (txtStatus.equals("Completed")) {
                    status = 2;
                } else if (txtStatus.equals("Cancel")) {
                    status = 3;
                }
            }

            if ((from != null && !from.isEmpty()) && (to != null && !to.isEmpty())) {
                Date dateFrom = new Date(MyUtils.parse(from).getTime());
                Date dateTo = new Date(MyUtils.parse(to).getTime());
                ArrayList<OrderDTO> listOrder = OrderDAO.getAllOrdersByFilter(status, dateFrom, dateTo);
                if (listOrder != null) {
                    request.setAttribute("LIST_ORDERS", listOrder);
                }
            } else {                
                ArrayList<OrderDTO> listOrder = OrderDAO.getAllOrders(status);
                if (listOrder != null) {
                    request.setAttribute("LIST_ORDERS", listOrder);
                }
            }
        } catch (SQLException e) {
            log("ViewOrdersServlet - SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("ViewOrdersServlet - ClassNotFound" + e.getMessage());
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
