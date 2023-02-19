/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tienph.dao.AccountDAO;
import tienph.dto.AccountDTO;
import tienph.dto.AccountInsertError;

/**
 *
 * @author Hp
 */
public class ChangeInformationServlet extends HttpServlet {

    private final String CHANGE_PROFILE_PAGE = "changeProfile.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        String url = CHANGE_PROFILE_PAGE;
        HttpSession session = request.getSession();
        AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT_USER");
        String txtNewFullname = request.getParameter("txtNewFullName");
        String txtNewPhone = request.getParameter("txtNewPhone");
        String txtOldPassword = request.getParameter("txtOldPassword");
        String txtNewPassword = request.getParameter("txtNewPassword");
        String txtConfirmPassword = request.getParameter("txtConfirmPassword");
        AccountInsertError errors = new AccountInsertError();
        boolean foundError = false;
        try {
            //1. Check all user errors
            if (txtNewFullname.trim().length() < 8 || txtNewFullname.trim().length() > 50) {
                foundError = true;
                errors.setFullNameLengthErr("Fullname is required from 8 to 50 chars."
                        + " Please try again.");
            }
            if (txtNewPhone.trim().length() < 10 || txtNewPhone.trim().length() > 12) {
                foundError = true;
                errors.setPhoneLengthErr("Phone is required from 10 to 12 chars."
                        + " Please try again.");
            }
            
            if (txtNewPassword.length() < 8 || txtNewPassword.length() > 30) {
                foundError = true;
                errors.setPasswordLengthErr("Password is required from 8 to 30 chars."
                        + " Please try again.");                
            } else if (!txtConfirmPassword.equals(txtNewPassword)) {
                foundError = true;
                errors.setConfirmNotMatch("Confirm did not match password."
                        + " Please try again.");
            } else if (!txtOldPassword.equals(account.getPassword())) {
                foundError = true;
                errors.setPasswordOldNotMatch("Old password did not match."
                        + " Please try again.");                
            }                  
            if (foundError) {
                request.setAttribute("INSERT_ERRORS", errors);
            } else {
                //2. Insert to DB
                boolean result = AccountDAO.updateAccount(account.getEmail(), txtNewPassword, txtNewFullname, txtNewPhone);
                if (result) {
                    //transfer to login page
                    url = LOGIN_PAGE;
                } //end account created
            }
        } catch (SQLException e) {
            log("ChangeInformationServlet - SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("ChangeInformationServlet - ClassNotFound" + e.getMessage());
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
