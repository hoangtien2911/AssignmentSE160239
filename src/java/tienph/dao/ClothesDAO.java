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
                String sql = "SELECT CID, CName, price, imgPath, description,\n"
                        + "status, Clothes.CateID AS 'CateID'\n"
                        + "FROM Clothes";
                //3. Prepare sql statement to set SQL
                stm = con.prepareStatement(sql);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null) {
                    while (rs.next()) {
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

    public static ArrayList<ClothesDTO> getAllClothesByPageIndex(int pageIndex)
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
                String sql = "SELECT CID, CName, price, imgPath, description,\n"
                        + "                   status, CateID FROM (\n"
                        + "				SELECT ROW_NUMBER() OVER (ORDER BY CID ASC) AS r, CID, CName, \n"
                        + "				price, imgPath, description, status, Clothes.CateID\n"
                        + "				FROM Clothes WHERE status = 1\n"
                        + "				) AS new WHERE r between ?*12-11 AND ?*12";
                //3. Prepare sql statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setInt(1, pageIndex);
                stm.setInt(2, pageIndex);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null) {
                    while (rs.next()) {
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
    
    public static int countClothes() 
            throws SQLException, ClassNotFoundException {
        int count = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1 Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(CID) AS countClothes FROM Clothes WHERE status = 1";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    count = rs.getInt("countClothes");
                }
            }
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
        return count;
    }

    public static ClothesDTO getAClothing(int id, boolean isActive)
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
                String sql = "SELECT CID, CName, price, imgPath, description,\n"
                        + "status, Clothes.CateID AS 'CateID'\n"
                        + "FROM Clothes                        \n"
                        + "WHERE CID = ?\n";
                if (isActive) {
                    sql = sql + "AND status = 1";
                }
                //3. Prepare sql statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null) {
                    while (rs.next()) {
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
                String sql = "SELECT CID, CName, price, imgPath, description,\n"
                        + "status, Clothes.CateID AS 'CateID'\n"
                        + "FROM Clothes JOIN Categories\n"
                        + "ON Clothes.CateID = Categories.CateID \n"
                        + "WHERE status = 1 AND CateName = ?";
                //3. Prepare sql statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, category);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null) {
                    while (rs.next()) {
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
                String sql = "SELECT CID, CName, price, imgPath, description,\n"
                        + "status, Clothes.CateID AS 'CateID'\n"
                        + "FROM Clothes JOIN Categories\n"
                        + "ON Clothes.CateID = Categories.CateID WHERE status = 1\n";
                if (searchby.equalsIgnoreCase("bycategory")) {
                    sql = sql + " AND CateName LIKE ?";
                } else {
                    sql = sql + " AND Clothes.CName LIKE ?";
                }
                //3. Prepare sql statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null) {
                    while (rs.next()) {
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

    public static boolean insertCloth(ClothesDTO cloth)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String
                String sql = "INSERT INTO dbo.Clothes(CName, price, imgPath, description, status, CateID)\n"
                        + " VALUES (?, ?, ?, ?, ?, ?)";
                //3. Prepare statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, cloth.getName());
                stm.setInt(2, cloth.getPrice());
                stm.setString(3, cloth.getImgPath());
                stm.setString(4, cloth.getDescription());
                stm.setInt(5, cloth.getStatus());
                stm.setInt(6, cloth.getCateId());
                //4. Execute query 
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                } // end if execute success
            }// end if connection is existed
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

    public static boolean updateCloth(ClothesDTO cloth)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String 
                String sql = "UPDATE dbo.Clothes SET CName = ?,\n"
                        + " price =?, imgPath = ?, description = ?, status = ?, cateID = ? WHERE CID = ?";
                //3. Prepare statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, cloth.getName());
                stm.setInt(2, cloth.getPrice());
                stm.setString(3, cloth.getImgPath());
                stm.setString(4, cloth.getDescription());
                stm.setInt(5, cloth.getStatus());
                stm.setInt(6, cloth.getCateId());
                stm.setInt(7, cloth.getId());
                //4. Execute query
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                } // end if execute success
            } // end if connection is existed
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
        ArrayList<ClothesDTO> dto = ClothesDAO.getClothes("shi", "byname");
        for (ClothesDTO clothesDTO : dto) {
            System.out.println(clothesDTO.toString());
        }
        System.out.println(ClothesDAO.countClothes() / 12.0);
        System.out.println((int) Math.ceil(ClothesDAO.countClothes() / 12.0));
//        ClothesDTO dto = ClothesDAO.getAClothing(1);
//        System.out.println(dto.toString());
    }
}
