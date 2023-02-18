/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hp
 */
public class DBUtils implements Serializable{

    public static Connection makeConnection()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        String IP = "localhost";
        String instanceName = "DESKTOP-4JUVRKE\\HOANGTIEN";
        String port = "1433";
        String uid = "sa";
        String pwd = "12345";
        String db = "ClothesShop";
        //1. Load driver
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        Class.forName(driver);
        //2. Create Connection String
        String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port
                + ";databasename=" + db + ";user=" + uid + ";password=" + pwd;        
        //3. Open Connection
        con = DriverManager.getConnection(url);
        return con;
    }        
}
