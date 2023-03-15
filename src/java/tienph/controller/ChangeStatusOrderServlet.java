/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tienph.dao.OrderDAO;

/**
 *
 * @author Hp
 */
public class ChangeStatusOrderServlet extends HttpServlet {

    private final String ORDER_HISTORY_PAGE = "DispatchController?btAction=OrderHistory";
    private final String ORDER_DETAIL_PAGE = "DispatchController?btAction=ViewDetailOrder";

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
        String url = ORDER_HISTORY_PAGE;
        try {
            String page = request.getParameter("page");
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int status = Integer.parseInt(request.getParameter("status"));
            if (page != null) {
                url = ORDER_DETAIL_PAGE + "&orderId=" + orderId;
            } else {
                String lastFilterStatus = request.getParameter("lastFilterStatus");
                String lastFilterDateFrom = request.getParameter("lastFilterDateFrom");
                String lastFilterDateTo = request.getParameter("lastFilterDateTo");
                if ((lastFilterStatus != null && !lastFilterStatus.isEmpty())
                        && (lastFilterDateFrom != null && !lastFilterDateFrom.isEmpty())
                        && (lastFilterDateTo != null && !lastFilterDateTo.isEmpty())) {
                    url = url + "&DateFrom=" + lastFilterDateFrom + "&DateTo=" + lastFilterDateTo + "&txtStatus=" + lastFilterStatus;
                } else if ((lastFilterStatus != null && !lastFilterStatus.isEmpty())
                        && (lastFilterDateFrom == null || lastFilterDateFrom.isEmpty())
                        && (lastFilterDateTo == null || lastFilterDateTo.isEmpty())) {
                    url = url + "&txtStatus=" + lastFilterStatus;
                }
            }

            //Call DAO
            OrderDAO.changeStatusOrderById(orderId, status);
        } catch (SQLException e) {
            log("CancelOrderServlet - SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("CancelOrderServlet - ClassNotFound: " + e.getMessage());
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
