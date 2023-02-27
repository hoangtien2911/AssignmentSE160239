/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tienph.dto.CategoriesDTO;
import tienph.utils.DBUtils;

/**
 *
 * @author Hp
 */
public class CategoriesDAO implements Serializable{
    public static ArrayList<CategoriesDTO> getAllCategories()
            throws SQLException, ClassNotFoundException {
        ArrayList<CategoriesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare sql statement String
                String sql = "SELECT CateID, CateName FROM Categories";                
                //3. Prepare sql statement to set SQL
                stm = con.prepareStatement(sql);                
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null) {
                    while(rs.next()) {
                        int id = rs.getInt("CateID");
                        String name = rs.getString("CateName");                                              
                        CategoriesDTO dto = new CategoriesDTO(id, name);
                        list.add(dto);
                    }
                }
            } //end if connection is existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
    
    public static boolean updateCategory(int cateId, String cateName)
                    throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;        
        try {
            //1. Connect Database
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement string 
                String sql = "UPDATE dbo.Categories SET CateName = ? WHERE CateID = ?";                
                //3. Create statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, cateName);                
                stm.setInt(2, cateId);                
                //4. Execute query
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                } // end if execute success
            } //end if connection is existed
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }                        
        }
        return false;
    }
    
    public static boolean createNewCategory(String cateName)
                    throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;        
        try {
            //1. Connect Database
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement string 
                String sql = "INSERT INTO dbo.Categories(CateName) VALUES (?)";                
                //3. Create statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, cateName);                                             
                //4. Execute query
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                } // end if execute success
            } //end if connection is existed
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }                        
        }
        return false;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<CategoriesDTO> dto = CategoriesDAO.getAllCategories();
        for (CategoriesDTO clothesDTO : dto) {
            System.out.println(clothesDTO.toString());
        }
//        ClothesDTO dto = ClothesDAO.getAClothing(1);
//        System.out.println(dto.toString());
    }
}
