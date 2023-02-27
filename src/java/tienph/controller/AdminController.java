/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hp
 */
public class AdminController extends HttpServlet {
    private final String PROCESS_REQUEST_CONTROLLER = "ProcessRequestServlet";
    private final String CHANGE_ACCOUNT_STATUS_CONTROLLER = "ChangeAccountStatusAdminServlet";
    private final String VIEW_CATEGORIES_CONTROLLER = "ViewCategoriesAdminServlet";
    private final String UPDATE_CATEGORY_CONTROLLER = "UpdateCategoryAdminServlet";
    private final String CREATE_CATEGORY_CONTROLLER = "CreateCategoryAdminServlet";
    private final String VIEW_CLOTHES_CONTROLLER = "ViewClothesAdminServlet";
    private final String UPDATE_CLOTHES_CONTROLLER = "UpdateClothesAdminServlet";
    private final String CREATE_CLOTHES_CONTROLLER = "CreateClothesAdminServlet";
    private final String VIEW_ORDERS_CONTROLLER = "ViewOrdersAdminServlet";
    private final String UPDATE_ORDER_CONTROLLER = "UpdateOrderAdminServlet";
    private final String VIEW_ORDER_DETAIL_CONTROLLER = "ViewOrderDetailAdminServlet";
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
        String url = PROCESS_REQUEST_CONTROLLER;
        //which button does user click?
        String button = request.getParameter("btAction");
        try {
            if (button == null) {
                url = PROCESS_REQUEST_CONTROLLER;                
            } else if (button.equals("ChangeAccountStatus")) {
                url = CHANGE_ACCOUNT_STATUS_CONTROLLER;            
            } else if (button.equals("ViewCategories")) {
                url = VIEW_CATEGORIES_CONTROLLER;
            } else if (button.equals("UpdateCategory")) {
                url = UPDATE_CATEGORY_CONTROLLER;
            } else if (button.equals("CreateNewCategory")) {
                url = CREATE_CATEGORY_CONTROLLER;
            } else if (button.equals("ViewClothes")) {
                url = VIEW_CLOTHES_CONTROLLER;
            } else if (button.equals("ViewOrders")) {
                url = VIEW_ORDERS_CONTROLLER;
            } else if (button.equals("UpdateOrder")) {
                url = UPDATE_ORDER_CONTROLLER;
            } else if (button.equals("ViewOrderDetail")) {
                url = VIEW_ORDER_DETAIL_CONTROLLER;
            } else {
                url = PROCESS_REQUEST_CONTROLLER;
            }
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
