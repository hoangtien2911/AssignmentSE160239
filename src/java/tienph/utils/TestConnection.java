/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import tienph.dao.AccountDAO;
import tienph.dto.AccountDTO;

/**
 *
 * @author Hp
 */
public class TestConnection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        System.out.println("1. Check getAccount");
        AccountDTO acc = AccountDAO.getAccount("hoangtienbmt2911@gmail.com", "123");
        if (acc != null) {
            if (acc.getRole() == 1) {
                System.out.println("hi tien");
            } else {
                System.out.println("hi han");
            }
        } else {
            System.out.println("Login fail");
        }
        
        
        System.out.println("----------------------------------------------------");
        System.out.println("2. Check getAccounts");
        ArrayList<AccountDTO> accounts = AccountDAO.getAccounts();        
        for (AccountDTO account : accounts) {            
            System.out.println(account.toString());
        }
        
        
        System.out.println("----------------------------------------------------");
        System.out.println("3. Check updateAccountStatus");
        boolean checkUpdateAccountStatus = false;
        checkUpdateAccountStatus = AccountDAO.updateAccountStatus("hoangtienbmt2911@gmail.com", 1);
        if (checkUpdateAccountStatus) {
            System.out.println("Update success");
        }
        
        System.out.println("----------------------------------------------------");
        System.out.println("4. Check updateAccount");
        boolean checkUpdateAccount = false;
        checkUpdateAccount = AccountDAO.updateAccount("hoangtienbmt2911@gmail.com",
                "12345", "Pham Hoang Tien", "0868363802");
        if (checkUpdateAccountStatus) {
            System.out.println("Update success");
        }
        
        
        System.out.println("----------------------------------------------------");
        System.out.println("4. Check insertAccount");
        boolean checkInsertAccount = false;
        checkInsertAccount = AccountDAO.insertAccount("ngochan0904@gmail.com",
                "123", "Do Nguyen Ngoc Han", "0766789772", 1, 0);
        if (checkInsertAccount) {
            System.out.println("Insert success");
        }
    }
    
//    <Resource name="HoangTienAssignment" type="javax.sql.DataSource" auth="Container"
//              driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
//              url="jdbc:sqlserver://localhost:1433;databaseName=PlantShop" 
//              username="sa" password="12345"/>
//</Context>

}
