/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.utils;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Hp
 */
public class SecurityUtils implements Serializable{
    private static String salt;
    
    // Add salt
//    private static String getSalt()
//            throws NoSuchAlgorithmException, NoSuchProviderException 
//    {
//        // Always use a SecureRandom generator
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
//
//        // Create array for salt
//        byte[] saltByte = new byte[16];
//
//        // Get a random salt
//        sr.nextBytes(saltByte);
//
//        // return salt
//        return saltByte.toString();
//    }
    
    public static String getSecurePassword(String passwordToHash) {
        String generatedPassword = null;
        try {    
            salt = "[B@4b6e3a1d";
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Add password bytes to digest
            md.update(salt.getBytes());
            
            // Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
            
        return generatedPassword;
    }
}
