/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.dto;

import java.io.Serializable;

/**
 *
 * @author Hp
 */
public class AccountDTO implements Serializable {
    private int accID;
    private String email;
    private String password;
    private String fullname;
    private String phone;
    private int status;    
    private int role;
    private String token;

    public AccountDTO() {
    }

    public AccountDTO(int accID, String email, String password, String fullname, String phone, int status, int role, String token) {
        this.accID = accID;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.status = status;
        this.role = role;
        this.token = token;
    }    

    /**
     * @return the accID
     */
    public int getAccID() {
        return accID;
    }

    /**
     * @param accID the accID to set
     */
    public void setAccID(int accID) {
        this.accID = accID;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }                      

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "accID=" + accID + ", email=" + email + ", password=" + password + ", fullname=" + fullname + ", status=" + status + ", phone=" + phone + ", role=" + role + ", token=" + token + '}';
    }
    
    
}
