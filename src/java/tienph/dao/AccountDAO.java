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
import tienph.dto.AccountDTO;
import tienph.utils.DBUtils;

/**
 *
 * @author Hp
 */
public class AccountDAO implements Serializable {

    public static AccountDTO getAccount(String email, String password)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO acc = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare sql statement String
                String sql = "SELECT accID, email, password, fullname, phone,"
                        + " status, role, token FROM dbo.Accounts WHERE status = 1 AND"
                        + " email = ? AND password = ? COLLATE Latin1_General_CS_AS";
                //3. Create sql statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null && rs.next()) {
                    //get field/ column
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    String Token = rs.getString("token");
                    acc = new AccountDTO(AccID, Email, Password, Fullname,
                            Phone, Status, Role, Token);
                }
            } // end if connection existed        
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
        return acc;
    }

    public static AccountDTO getAccountByToken(String token)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO acc = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare sql statement String
                String sql = "SELECT accID, email, password, fullname, phone,"
                        + " status, role, token FROM dbo.Accounts WHERE token = ?";
                //3. Create sql statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, token);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                if (rs != null && rs.next()) {
                    //get field/ column
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    String Token = rs.getString("token");
                    acc = new AccountDTO(AccID, Email, Password, Fullname,
                            Phone, Status, Role, Token);
                }
            } // end if connection existed        
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
        return acc;
    }

    public static ArrayList<AccountDTO> getAccounts()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<AccountDTO> accounts = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2.Prepare statement String
                String sql = "SELECT accID, email, password, fullname, phone,"
                        + " status, role, token FROM dbo.Accounts WHERE role = 0";
                //3. Create statement to set sql
                stm = con.prepareStatement(sql);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                while (rs.next()) {
                    //get field/ column
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    String Token = rs.getString("token");
                    //Create DTO instance
                    AccountDTO dto = new AccountDTO(AccID, Email, Password, Fullname,
                            Phone, Status, Role, Token);
                    //add account to list
                    if (accounts == null) {
                        accounts = new ArrayList<>();
                    }
                    accounts.add(dto);
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
        return accounts;
    }

    public static boolean updateAccountStatus(String email, int status)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect Database
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement string 
                String sql = "UPDATE dbo.Accounts SET status = ? WHERE email = ?";
                //3. Create statement to set sql
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setString(2, email);
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

    public static boolean updateAccount(String email, String newPassword,
            String newFullname, String newPhone)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String 
                String sql = "UPDATE dbo.Accounts SET password =  ?,"
                        + " fullname = ?, phone =? WHERE email = ?";
                //3. Prepare statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, newPassword);
                stm.setString(2, newFullname);
                stm.setString(3, newPhone);
                stm.setString(4, email);
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

    public static boolean insertAccount(String newEmail, String newPassword,
            String newFullname, String newPhone, int newStatus, int newRole)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String
                String sql = "INSERT INTO [dbo].[Accounts]\n"
                        + "           ([email]\n"
                        + "           ,[password]\n"
                        + "           ,[fullname]\n"
                        + "           ,[phone]\n"
                        + "           ,[status]\n"
                        + "           ,[role]\n"
                        + "           ,[token]) VALUES (?, ?, ?, ?, ?, ?, ?)";
                //3. Prepare statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, newEmail);
                stm.setString(2, newPassword);
                stm.setString(3, newFullname);
                stm.setString(4, newPhone);
                stm.setInt(5, newStatus);
                stm.setInt(6, newRole);
                stm.setString(7, "");
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

    public static boolean updateToken(String email, String token)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Prepare statement String
                String sql = "UPDATE dbo.Accounts SET token = ? WHERE email = ?";
                //3. Prepare statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, token);
                stm.setString(2, email);
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

}
