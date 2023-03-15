/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tienph.dao.AccountDAO;
import tienph.dao.ClothesDAO;
import tienph.dto.AccountDTO;
import tienph.dto.ClothesDTO;
import tienph.utils.MyUtils;
import tienph.utils.SecurityUtils;

/**
 *
 * @author Hp
 */
public class LoginServlet extends HttpServlet {
    private final String HOME_PAGE = "./user/home.jsp";
    private final String MANAGE_ACCOUNT_ADMIN_PAGE = "./admin/manageAccount.jsp";
    private final String INVALID_PAGE = "./user/login.jsp";
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
        String url = INVALID_PAGE;
        //1. get Parameters
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");      
        String chkRemember = request.getParameter("chkRemember");
        try {            
            String hashedPassword = SecurityUtils.getSecurePassword(password);               
            //2. Call DAO
            AccountDTO acc = AccountDAO.getAccount(email, hashedPassword);
            if (acc != null) {
                HttpSession session = request.getSession();
                //ADMIN
                if (chkRemember != null) {
                    String token = UUID.randomUUID().toString();
                    AccountDAO.updateToken(email, token);
                    Cookie cookie = new Cookie("token", token);
                    cookie.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookie);
                }
                if(acc.getRole() ==  1) {
                    ArrayList<AccountDTO> listAccount = AccountDAO.getAccounts();
                    session.setAttribute("USERNAME_ADMIN", MyUtils.splitFullname(acc.getFullname()));
                    session.setAttribute("ACCOUNT_ADMIN", acc);
                    request.setAttribute("LIST_ACCOUNT_USER", listAccount);
                    url = MANAGE_ACCOUNT_ADMIN_PAGE;
                }               
                //USER
                else {
                    int numberPage = (int) Math.ceil(ClothesDAO.countClothes() / 12.0); 
                    ArrayList<ClothesDTO> listClothes = ClothesDAO.getAllClothesByPageIndex(1);
                    ArrayList<ClothesDTO> listSpecial = ClothesDAO.getClothesByCategory("special");
                    request.setAttribute("NUMBER_PAGE", numberPage);
                    request.setAttribute("LIST_SPECIAL", listSpecial);
                    request.setAttribute("LIST_CLOTHES", listClothes);
                    session.setAttribute("USERNAME_USER", MyUtils.splitFullname(acc.getFullname()));
                    session.setAttribute("ACCOUNT_USER", acc);
                    url = HOME_PAGE;
                }
            } else {
                String errorMsg = "Username or Password entered isn't connected.";
                request.setAttribute("ERROR_MSG", errorMsg);
            }
        } catch (SQLException e) {
            log("LoginServlet - SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("LoginServlet - ClassNotFound" + e.getMessage());        
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
