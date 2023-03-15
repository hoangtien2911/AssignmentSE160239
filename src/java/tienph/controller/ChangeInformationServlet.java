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
import tienph.utils.SecurityUtils;

/**
 *
 * @author Hp
 */
public class ChangeInformationServlet extends HttpServlet {

    private final String CHANGE_PROFILE_PAGE = "./user/changeProfile.jsp";
    private final String LOGIN_PAGE = "./user/login.jsp";

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
        String txtNewPassword = request.getParameter("txtNewPassword");
        try {            
                //2. Insert to DB                
                boolean result = AccountDAO.updateAccount(account.getEmail(),
                        SecurityUtils.getSecurePassword(txtNewPassword), txtNewFullname, txtNewPhone);
                if (result) {
                    //transfer to login page                    
                    session.invalidate();
                    url = LOGIN_PAGE;
                } //end account created
                request.setAttribute("MSG_LOGIN_AGAIN", "You must login again after change information.");
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
