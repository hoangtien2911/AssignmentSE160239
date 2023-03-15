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
public class DispatchController extends HttpServlet {
    private final String HOME_PAGE = "./user/home.jsp";    
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String LOG_OUT_CONTROLLER = "LogoutServlet";
    private final String REGISTER_CONTROLLER = "RegistrationServlet";
    private final String CHANGE_INFORMATION_CONTROLLER = "ChangeInformationServlet";
    private final String SEARCH_CONTROLLER = "SearchServlet";
    private final String CLOTHING_DETAIL_CONTROLLER = "ClothingDetailServlet";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";    
    private final String CART_REMOVE_ITEM_CONTROLLER = "CartRemoveItemServlet";
    private final String CART_UPDATE_ITEM_CONTROLLER = "CartUpdateItemServlet";
    private final String CHECK_OUT_CONTROLLER = "CheckOutServlet";
    private final String PROCESS_REQUEST_CONTROLLER = "ProcessRequestServlet";
    private final String ORDER_HISTORY_CONTROLLER = "OrderHistoryServlet";
    private final String CHANGE_STATUS_ORDER_CONTROLLER = "ChangeStatusOrderServlet";    
    private final String DETAIL_ORDER_CONTROLLER = "DetailOrderServlet";        
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
        //which button does user click?
        String button = request.getParameter("btAction");
        try {            
            if (button == null) {
                url = PROCESS_REQUEST_CONTROLLER;                
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Logout")) {
                url = LOG_OUT_CONTROLLER;  
            } else if (button.equals("Register")) {
                url = REGISTER_CONTROLLER;
            } else if (button.equals("ChangeInformation")) {
                url = CHANGE_INFORMATION_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_CONTROLLER;
            } else if (button.equals("ClothingDetail")) {
                url = CLOTHING_DETAIL_CONTROLLER;
            } else if (button.equals("AddToCart")) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (button.equals("CartRemoveItem")) {
                url = CART_REMOVE_ITEM_CONTROLLER;
            } else if (button.equals("CartUpdateItem")) {
                url = CART_UPDATE_ITEM_CONTROLLER;
            } else if (button.equals("CheckOut")) {
                url = CHECK_OUT_CONTROLLER;
            } else if (button.equals("OrderHistory")) {
                url = ORDER_HISTORY_CONTROLLER;
            } else if (button.equals("ChangeStatusOrder")) {
                url = CHANGE_STATUS_ORDER_CONTROLLER;
            } else if(button.equals("ViewDetailOrder")) {
                url = DETAIL_ORDER_CONTROLLER;
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
