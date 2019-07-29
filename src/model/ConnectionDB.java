/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author fikriadriansa21
 */
public class ConnectionDB {
    public Connection conection;
    
    public boolean dbConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kontrakan","root","");
            conection = con;            
            return true;
        } catch (Exception e) {
            System.out.println("error");
            return false;
        }
    }
}
