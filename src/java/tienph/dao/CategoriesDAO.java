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
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<CategoriesDTO> dto = CategoriesDAO.getAllCategories();
        for (CategoriesDTO clothesDTO : dto) {
            System.out.println(clothesDTO.toString());
        }
//        ClothesDTO dto = ClothesDAO.getAClothing(1);
//        System.out.println(dto.toString());
    }
}
