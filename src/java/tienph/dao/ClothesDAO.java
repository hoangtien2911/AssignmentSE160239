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
import tienph.dto.ClothesDTO;
import tienph.utils.DBUtils;

/**
 *
 * @author Hp
 */
public class ClothesDAO implements Serializable {
    public static ArrayList<ClothesDTO> getAllClothes() 
        throws SQLException, ClassNotFoundException {
        ArrayList<ClothesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare sql statement String
                String sql = "SELECT CID, CName, price, imgPath, description,\n" +
                                          "status, Clothes.CateID AS 'CateID'\n" +
                                          "FROM Clothes";                
                //3. Prepare sql statement to set SQL
                stm = con.prepareStatement(sql);                
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null) {
                    while(rs.next()) {
                        int id = rs.getInt("CID");
                        String name = rs.getString("CName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateId = rs.getInt("CateID");                        
                        ClothesDTO dto = new ClothesDTO(id, name, price, imgPath, description, status, cateId);
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
    
    public static ClothesDTO getAClothing(int id) 
        throws SQLException, ClassNotFoundException {
        ClothesDTO dto = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare sql statement String
                String sql = "SELECT CID, CName, price, imgPath, description,\n" +
                                            "status, Clothes.CateID AS 'CateID'\n" +
                                            "FROM Clothes                        \n" +
                                            "WHERE CID = ?";                
                //3. Prepare sql statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null) {
                    while(rs.next()) {
                        int ID = rs.getInt("CID");
                        String name = rs.getString("CName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateId = rs.getInt("CateID");                        
                        dto = new ClothesDTO(ID, name, price, imgPath, description, status, cateId);                        
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
        return dto;
    }
    
    public static ArrayList<ClothesDTO> getClothesByCategory(String category) 
        throws SQLException, ClassNotFoundException {
        ArrayList<ClothesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare sql statement String
                String sql = "SELECT CID, CName, price, imgPath, description,\n" +
                                            "status, Clothes.CateID AS 'CateID'\n" +
                                            "FROM Clothes JOIN Categories\n" +
                                            "ON Clothes.CateID = Categories.CateID \n" +
                                            "WHERE CateName = ?";                
                //3. Prepare sql statement to set SQL
                stm = con.prepareStatement(sql);  
                stm.setString(1, category);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null) {
                    while(rs.next()) {
                        int id = rs.getInt("CID");
                        String name = rs.getString("CName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateId = rs.getInt("CateID");                        
                        ClothesDTO dto = new ClothesDTO(id, name, price, imgPath, description, status, cateId);
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
    
    public static ArrayList<ClothesDTO> getClothes(String keyword, String searchby) 
        throws SQLException, ClassNotFoundException {
        ArrayList<ClothesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare sql statement String
                String sql = "SELECT CID, CName, price, imgPath, description,\n" +
                                            "status, Clothes.CateID AS 'CateID'\n" +
                                            "FROM Clothes JOIN Categories\n" +
                                            "ON Clothes.CateID = Categories.CateID";
                if (searchby.equalsIgnoreCase("bycategory")) {
                    sql = sql + " WHERE CateName LIKE ?";
                } else {
                    sql = sql + " WHERE Clothes.CName LIKE ?";                    
                }
                //3. Prepare sql statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null) {
                    while(rs.next()) {
                        int id = rs.getInt("CID");
                        String name = rs.getString("CName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateId = rs.getInt("CateID");                        
                        ClothesDTO dto = new ClothesDTO(id, name, price, imgPath, description, status, cateId);
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
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<ClothesDTO> dto = ClothesDAO.getClothes("shi", "byname");
        for (ClothesDTO clothesDTO : dto) {
            System.out.println(clothesDTO.toString());
        }
//        ClothesDTO dto = ClothesDAO.getAClothing(1);
//        System.out.println(dto.toString());
    }
}
