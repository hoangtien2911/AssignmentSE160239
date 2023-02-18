/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import tienph.dto.ClothesDTO;
import tienph.dto.OrderDTO;
import tienph.utils.DBUtils;
import tienph.utils.MyUtils;

/**
 *
 * @author Hp
 */
public class OrderDAO implements Serializable {

    public static int insertOrder(int accID, HashMap<ClothesDTO, Integer> cart, Date shipDate)
            throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                con.setAutoCommit(false);
                //2. Prepare statement String 
                String sql = "INSERT INTO Orders(OrdDate, shipdate, status, AccID) VALUES (?, ?, ?, ?)";
                //3. Prepare statement to set sql            
                stm = con.prepareStatement(sql);
                Date dateNow = new Date(System.currentTimeMillis());
                stm.setDate(1, dateNow);
                stm.setDate(2, shipDate);
                stm.setInt(3, 1);
                stm.setInt(4, accID);
                result = stm.executeUpdate();
                if (result > 0) {
                    sql = "SELECT TOP 1 OrderID FROM Orders ORDER BY OrderID DESC";
                    stm = con.prepareStatement(sql);
                    rs = stm.executeQuery();
                    if (rs != null && rs.next()) {
                        int orderID = rs.getInt("OrderID");
                        if (cart != null && cart.size() > 0) {
                            for (ClothesDTO dto : cart.keySet()) {
                                int quantity = cart.get(dto);
                                sql = "INSERT INTO OrderDetails(OrderID, CID, quantity) VALUES (?, ?, ?)";
                                stm = con.prepareStatement(sql);
                                stm.setInt(1, orderID);
                                stm.setInt(2, dto.getId());
                                stm.setInt(3, quantity);
                                result = stm.executeUpdate();
                            }
                        }
                    }
                }
                con.commit();
                con.setAutoCommit(true);
            } //end if connection is existed                        
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }            
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public static ArrayList<OrderDTO> getAllOrders(int accId, int status)
                        throws SQLException, ClassNotFoundException{
        ArrayList<OrderDTO> listOrder = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String
                String sql = "SELECT OrderID, OrdDate, shipdate, status FROM Orders\n" +
                                                        "WHERE Orders.AccID = ? \n";
                if (status != 0) {
                    sql = sql + "AND Orders.status = ?";
                }
                //3. Prepare statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setInt(1, accId);
                if (status != 0) {
                    stm.setInt(2, status);
                }
                //4. Execute query
                rs = stm.executeQuery();
                while(rs.next()) {
                    //get field/ column
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipdate = rs.getDate("shipdate");
                    int sta = rs.getInt("status");                                                           
                    //Create DTO instance
                    OrderDTO dto = new OrderDTO(orderID, ordDate, shipdate, sta, accId);
                    //add account to list
                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }                    
                    listOrder.add(dto);
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
        return listOrder;
    }
    
    public static ArrayList<OrderDTO> getAllOrdersByFilter(int accId, int status, Date from, Date to)
                        throws SQLException, ClassNotFoundException{
        ArrayList<OrderDTO> listOrder = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String
                String sql = "SELECT OrderID, OrdDate, shipdate, status FROM Orders\n" +
                            "WHERE Orders.AccID = ? AND Orders.OrdDate >= ? AND Orders.OrdDate <= ?\n";
                if (status != 0) {
                    sql = sql + " AND Orders.status = ?";
                }
                //3. Prepare statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setInt(1, accId);
                stm.setDate(2, from);
                stm.setDate(3, to);
                if (status != 0) {
                    stm.setInt(4, status);                    
                }                
                //4. Execute query
                rs = stm.executeQuery();
                while(rs.next()) {
                    //get field/ column
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipdate = rs.getDate("shipdate");
                    int sta = rs.getInt("status");                                                           
                    //Create DTO instance
                    OrderDTO dto = new OrderDTO(orderID, ordDate, shipdate, sta, accId);
                    //add account to list
                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }                    
                    listOrder.add(dto);
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
        return listOrder;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String from = "2023-02-17";
        String to = "2023-02-19";
        Date dateFrom = new Date(MyUtils.parse(from).getTime());
                    Date dateTo = new Date(MyUtils.parse(to).getTime());
        System.out.println(dateFrom);
        System.out.println(dateTo);
        ArrayList<OrderDTO> li = OrderDAO.getAllOrdersByFilter(2, 0, dateFrom, dateTo);
        li.forEach((orderDTO) -> {
            System.out.println(orderDTO.toString());
        });
    }
}
