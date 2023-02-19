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
public class AccountInsertError implements Serializable{
    private String emailMatchErr;
    private String passwordLengthErr;
    private String confirmNotMatch;
    private String fullNameLengthErr;
    private String phoneLengthErr;
    private String agreeTermErr;
    private String emailIsExisted;    
    private String passwordOldNotMatch;
    public AccountInsertError() {
    }

    /**
     * @return the emailMatchErr
     */
    public String getEmailMatchErr() {
        return emailMatchErr;
    }

    /**
     * @param emailMatchErr the emailMatchErr to set
     */
    public void setEmailMatchErr(String emailMatchErr) {
        this.emailMatchErr = emailMatchErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the confirmNotMatch
     */
    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    /**
     * @param confirmNotMatch the confirmNotMatch to set
     */
    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    /**
     * @return the fullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLengthErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the phoneLengthErr
     */
    public String getPhoneLengthErr() {
        return phoneLengthErr;
    }

    /**
     * @param phoneLengthErr the phoneLengthErr to set
     */
    public void setPhoneLengthErr(String phoneLengthErr) {
        this.phoneLengthErr = phoneLengthErr;
    }
    
    /**
     * @return the agreeTermErr
     */
    public String getAgreeTermErr() {
        return agreeTermErr;
    }

    /**
     * @param agreeTermErr the agreeTermErr to set
     */
    public void setAgreeTermErr(String agreeTermErr) {
        this.agreeTermErr = agreeTermErr;
    }

    /**
     * @return the emailIsExisted
     */
    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    /**
     * @param emailIsExisted the emailIsExisted to set
     */
    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    } 

    /**
     * @return the passwordOldNotMatch
     */
    public String getPasswordOldNotMatch() {
        return passwordOldNotMatch;
    }

    /**
     * @param passwordOldNotMatch the passwordOldNotMatch to set
     */
    public void setPasswordOldNotMatch(String passwordOldNotMatch) {
        this.passwordOldNotMatch = passwordOldNotMatch;
    }
    
    
}
