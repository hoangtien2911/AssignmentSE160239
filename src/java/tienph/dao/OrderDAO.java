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
import tienph.dto.OrderDetailDTO;
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
            throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> listOrder = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String
                String sql = "SELECT OrderID, OrdDate, shipdate, status FROM Orders\n"
                        + "WHERE Orders.AccID = ? \n";
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
                while (rs.next()) {
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

    public static ArrayList<OrderDTO> getAllOrdersAdmin(int status, int accId)
            throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> listOrder = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String
                String sql = "SELECT OrderID, OrdDate, shipdate, status, AccID FROM Orders\n";
                if (status != 0) {
                    sql = sql + "WHERE Orders.status = ?\n";                    
                }
                if (status == 0 && accId != 0) {
                    sql = sql + "WHERE Orders.AccID = ?\n";                                     
                } else if (status != 0 && accId != 0) {
                    sql = sql + " AND Orders.AccID = ?\n";                    
                }
                //3. Prepare statement to set SQL
                stm = con.prepareStatement(sql);
                if (status != 0) {
                    stm.setInt(1, status);
                }
                if (status == 0 && accId != 0) {
                    stm.setInt(1, accId);
                } else if (status != 0 && accId != 0) {
                    stm.setInt(2, accId);
                }                
                //4. Execute query
                rs = stm.executeQuery();
                while (rs.next()) {
                    //get field/ column
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipdate = rs.getDate("shipdate");
                    int sta = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    //Create DTO instance
                    OrderDTO dto = new OrderDTO(orderID, ordDate, shipdate, sta, accID);
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

    public static OrderDTO getOrderByOrderId(int orderId)
            throws SQLException, ClassNotFoundException {
        OrderDTO dto = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String
                String sql = "SELECT OrderID, OrdDate, shipdate, status,\n"
                        + " AccID FROM Orders WHERE OrderID = ?";
                //3. Prepare statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                //4. Execute query
                rs = stm.executeQuery();
                while (rs.next()) {
                    //get field/ column
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipdate = rs.getDate("shipdate");
                    int status = rs.getInt("status");
                    int accId = rs.getInt("AccID");
                    //Create DTO instance
                    dto = new OrderDTO(orderID, ordDate, shipdate, status, accId);
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

    public static ArrayList<OrderDTO> getAllOrdersByFilter(int accId, int status, Date from, Date to)
            throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> listOrder = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String
                String sql = "SELECT OrderID, OrdDate, shipdate, status FROM Orders\n"
                        + "WHERE Orders.AccID = ? AND Orders.OrdDate >= ? AND Orders.OrdDate <= ?\n";
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
                while (rs.next()) {
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

    public static ArrayList<OrderDTO> getAllOrdersByFilter(int status, Date from, Date to, int accId)
            throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> listOrder = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String
                String sql = "SELECT OrderID, OrdDate, shipdate, status, AccID FROM Orders\n"
                        + "WHERE Orders.OrdDate >= ? AND Orders.OrdDate <= ?\n";
                if (status != 0) {
                    sql = sql + " AND Orders.status = ?\n";
                }
                if (accId != 0) {
                    sql = sql + " AND Orders.AccID = ?\n";
                }
                //3. Prepare statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setDate(1, from);
                stm.setDate(2, to);
                if (status != 0) {
                    stm.setInt(3, status);
                }
                if (status != 0 && accId != 0) {
                    stm.setInt(4, accId);
                }
                if (status == 0 && accId != 0) {
                    stm.setInt(3, accId);
                }
                //4. Execute query
                rs = stm.executeQuery();
                while (rs.next()) {
                    //get field/ column
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipdate = rs.getDate("shipdate");
                    int sta = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    //Create DTO instance
                    OrderDTO dto = new OrderDTO(orderID, ordDate, shipdate, sta, accID);
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

    public static ArrayList<OrderDetailDTO> getAllOrderDetailsById(int orderId)
            throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailDTO> listOrderDetail = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String
                String sql = "SELECT DetailId, OrderID, CID, quantity FROM OrderDetails WHERE OrderID = ? ORDER BY DetailId ASC";
                //3. Prepare statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                //4. Execute query
                rs = stm.executeQuery();
                while (rs.next()) {
                    //get field/ column
                    int detailId = rs.getInt("DetailId");
                    int orderID = rs.getInt("OrderID");
                    int clothID = rs.getInt("CID");
                    int quantity = rs.getInt("quantity");
                    //Create DTO instance
                    OrderDetailDTO dto = new OrderDetailDTO(detailId, orderID, clothID, quantity);
                    //add account to list
                    if (listOrderDetail == null) {
                        listOrderDetail = new ArrayList<>();
                    }
                    listOrderDetail.add(dto);
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
        return listOrderDetail;
    }

    public static boolean changeStatusOrderById(int orderId, int status)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare sql statement String
                String sql = "UPDATE Orders SET status = ? WHERE orderID = ?";
                //3. Create sql statement to set sql
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, orderId);
                //4. Execute query
                int row = stm.executeUpdate();
                //5. Process             
                if (row > 0) {
                    return true;
                } //end if execute success
            }// end if connection success
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

    public static boolean updateOrderById(int orderId, Date deliveryDate, int status)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare sql statement String
                String sql = "UPDATE Orders SET shipdate = ?, status = ? WHERE orderID = ?";
                //3. Create sql statement to set sql
                stm = con.prepareStatement(sql);
                stm.setDate(1, deliveryDate);
                stm.setInt(2, status);
                stm.setInt(3, orderId);
                //4. Execute query
                int row = stm.executeUpdate();
                //5. Process             
                if (row > 0) {
                    return true;
                } //end if execute success
            }// end if connection success
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
        String from = "2023-02-17";
        String to = "2023-02-19";
        Date dateFrom = new Date(MyUtils.parse(from).getTime());
        Date dateTo = new Date(MyUtils.parse(to).getTime());
        System.out.println(dateFrom);
        System.out.println(dateTo);
//        ArrayList<OrderDTO> li = OrderDAO.getAllOrdersByFilter(2, 0, dateFrom, dateTo);
        ArrayList<OrderDetailDTO> li = OrderDAO.getAllOrderDetailsById(9);
        li.forEach((OrderDetailDTO) -> {
            System.out.println(OrderDetailDTO.toString());
        });

        System.out.println(OrderDAO.getOrderByOrderId(9).toString());
    }
}
