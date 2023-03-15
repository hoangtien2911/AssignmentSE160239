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
import tienph.dao.AccountDAO;
import tienph.dto.AccountInsertError;
import tienph.utils.SecurityUtils;

/**
 *
 * @author Hp
 */
public class RegistrationServlet extends HttpServlet {
    private final String LOGIN_PAGE = "./user/login.jsp";
    private final String ERROR_PAGE = "./user/registration.jsp";
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
        String url = ERROR_PAGE;
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String fullname = request.getParameter("txtFullname");
        String phone = request.getParameter("txtPhone");
        AccountInsertError errors = new AccountInsertError();
        try {            
            //2. Insert to DB             
            String hashedPassword = SecurityUtils.getSecurePassword(password);
            System.out.println("co nhay vo");
            boolean result = AccountDAO.insertAccount(email, hashedPassword, fullname, phone, 1, 0);
            if (result) {
                //transfer to login page
                url = LOGIN_PAGE;
                request.setAttribute("MSG_REGISTER_SUCCESS", "Registration Successful! Please Login.");
            } //end account created
        } catch (SQLException e) {
            String msg = e.getMessage();
            log("RegisterServlet - SQL: " + msg);
            if (msg.contains("duplicate")) {
                errors.setEmailIsExisted(email + " existed!!! Please try again.");
                request.setAttribute("INSERT_ERRORS", errors);
            }
        } catch (ClassNotFoundException e) {
            log("RegisterServlet - ClassNotFound: " + e.getMessage());
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
