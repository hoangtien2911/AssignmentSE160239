/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

/**
 *
 * @author Hp
 */
public class ProcessRequestServlet extends HttpServlet {

    private final String HOME_PAGE = "./user/home.jsp";
    private final String MANAGE_ACCOUNT_ADMIN_PAGE = "./admin/manageAccount.jsp";

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
        String url = HOME_PAGE;
        HttpSession session = request.getSession();
        try {
            if ((AccountDTO) session.getAttribute("ACCOUNT_ADMIN") != null) {
                ArrayList<AccountDTO> listAccount = AccountDAO.getAccounts();
                request.setAttribute("LIST_ACCOUNT_USER", listAccount);
                url = MANAGE_ACCOUNT_ADMIN_PAGE;
            } else {
                Cookie[] cookies = request.getCookies();
                String token = "";
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("token")) {
                            token = cookie.getValue();
                        }
                    }
                    AccountDTO account = AccountDAO.getAccountByToken(token);
                    if (account != null) {
                        if (account.getRole() == 1) {                            
                            ArrayList<AccountDTO> listAccount = AccountDAO.getAccounts();
                            session.setAttribute("USERNAME_ADMIN", MyUtils.splitFullname(account.getFullname()));
                            session.setAttribute("ACCOUNT_ADMIN", account);
                            request.setAttribute("LIST_ACCOUNT_USER", listAccount);
                            url = MANAGE_ACCOUNT_ADMIN_PAGE;
                        } else {
                            session.setAttribute("USERNAME_USER", MyUtils.splitFullname(account.getFullname()));
                            session.setAttribute("ACCOUNT_USER", account);
                        }
                    }
                }
                String pageCurrentString = request.getParameter("pageNumber");
                int pageCurrent = 1;                
                if (pageCurrentString != null) {
                    pageCurrent = Integer.parseInt(pageCurrentString);
                }                
                int numberPage = (int) Math.ceil(ClothesDAO.countClothes() / 12.0);                
                ArrayList<ClothesDTO> listClothes = ClothesDAO.getAllClothesByPageIndex(pageCurrent);
                ArrayList<ClothesDTO> listSpecial = ClothesDAO.getClothesByCategory("special");
                request.setAttribute("NUMBER_PAGE", numberPage);
                request.setAttribute("LIST_SPECIAL", listSpecial);
                request.setAttribute("LIST_CLOTHES", listClothes);
            }

        } catch (SQLException e) {
            log("PROCESS_REQUEST_SERVLET - SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log("PROCESS_REQUEST_SERVLET - ClassNotFound: " + e.getMessage());
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
