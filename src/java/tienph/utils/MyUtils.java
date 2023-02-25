/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hp
 */
public class MyUtils implements Serializable{

    public static Date parse(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException ex) {
            Logger.getLogger(MyUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }

    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = null;
        try {
            dateString = sdf.format(date);
        } catch (Exception ex) {
            Logger.getLogger(MyUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateString;
    }

    public static String splitFullname(String fullname) {
        String[] splitFullName = fullname.split(" ");
        int count = 0;
        String name = "";
        for (String word : splitFullName) {
            count++;
            if (count > splitFullName.length / 2) {
                name = name + " " + word;
            }
        }
        return name.trim();
    }

    public static void main(String[] args) {
        Date d = MyUtils.parse("2003-12-2");
        System.out.println(d);
    }
}
