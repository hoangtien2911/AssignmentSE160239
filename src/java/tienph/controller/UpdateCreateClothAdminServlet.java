/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tienph.dao.ClothesDAO;
import tienph.dto.ClothesDTO;

/**
 *
 * @author Hp
 */
public class UpdateCreateClothAdminServlet extends HttpServlet {

    private final String MANAGE_CLOTHES_PAGE = "AdminController?btAction=ViewClothes";

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
        String url = MANAGE_CLOTHES_PAGE;
        try {
            String filename;
            int numberAction = 0;
            ClothesDTO clothesDTO = new ClothesDTO();
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            List<FileItem> items = upload.parseRequest(request);

            // Process the uploaded items            
            for (FileItem item : items) {
                if (item.getFieldName().equals("numberAction")) {
                    numberAction = Integer.parseInt(item.getString());
                }
                if (item.getFieldName().equals("id")) {
                    clothesDTO.setId(Integer.parseInt(item.getString()));
                }
                if (item.getFieldName().equals("newName")) {
                    clothesDTO.setName(item.getString());
                }
                if (item.getFieldName().equals("newPrice")) {
                    clothesDTO.setPrice(Integer.parseInt(item.getString()));
                }
                if (item.getFieldName().equals("newDescription")) {
                    clothesDTO.setDescription(item.getString());
                }
                if (item.getFieldName().equals("newStatus")) {
                    clothesDTO.setStatus(Integer.parseInt(item.getString()));
                }
                if (item.getFieldName().equals("newCategory")) {
                    clothesDTO.setCateId(Integer.parseInt(item.getString()));
                }
                if (item.getFieldName().equals("oldPath")) {
                    clothesDTO.setImgPath(item.getString());
                }

                if (item.getFieldName().equals("newPath")) {

//                    System.out.println("filename: " + filename);
                    if (item.getSize() > 0) {
                        filename = item.getName();
                        clothesDTO.setImgPath("images/" + filename);
                        Path path = Paths.get(filename);
                        String storePath = servletContext.getRealPath("/images");
//                        System.out.println("storePath: " + storePath);
                        File uploadFileInBuild = new File(storePath + "/" + path.getFileName());
                        item.write(uploadFileInBuild);
                    }

//                        System.out.println(storePath + "/" + path.getFileName());
                }
            }

            //call DAO  
            if (numberAction == 1) {
                ClothesDAO.insertCloth(clothesDTO);
            } else if (numberAction == 2) {
                ClothesDAO.updateCloth(clothesDTO);
            }
        } catch (Exception e) {
            log("UpdateClothAdminServlet - Exeption" + e.getMessage());
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
