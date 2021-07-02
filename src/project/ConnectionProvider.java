/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.sql.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ConnectionProvider {
    public static Connection getCon()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pos", "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException e) 
        {
            System.out.println("Driver not loaded successfuly");
            System.out.println(e.getMessage()); 
            //Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
