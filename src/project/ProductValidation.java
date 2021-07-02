/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ProductValidation {
    public static boolean isProductExist(String name){
        boolean isExist = false;
        Connection con = ConnectionProvider.getCon();
        PreparedStatement stmt;
        ResultSet rs;
        try{
            stmt = con.prepareStatement("SELECT * FROM `product` WHERE `name` = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if(rs.next()){
                isExist = true;
            }else{
                isExist = false;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return isExist;
    }
    
    public static boolean validateData(JTextField name, JTextField rate,JTextField description, JTextField stock)
    {
        if(name.getText().equals("") || rate.getText().equals("") || description.getText().equals("") || stock.getText().equals("") )
        {
            return false;
        }
        else
            return true;
    }
    public static boolean validateData(JTextField name, JTextField price,JTextField description, JTextField stock,JTextField activate )
    {
        if(name.getText().equals("") || price.getText().equals("") || description.getText().equals("") || stock.getText().equals("") || activate.getText().equals(""))
        {
            return false;
        }
        else
            return true;
    }
    public static void resetProductForm(JTextField name, JTextField rate,JTextField description, JTextField stock,JComboBox activate)
    {
        name.setText("");
        rate.setText("");
        description.setText("");
        stock.setText("");
        activate.setSelectedItem("YES");
    }
        public static void resetProductForm(JTextField name, JTextField price,JTextField description, JTextField stock, JTextField activate)
    {
        name.setText("");
        price.setText("");
        description.setText("");
        stock.setText("");
        activate.setText("");
    }
    public static boolean getProductById(JTextField search, JTextField name, JTextField price,JTextField description, JTextField stock, JTextField activate)
    {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement stmt;
            ResultSet rs;
            try
            {
                stmt = con.prepareStatement("SELECT * FROM `product` WHERE `id` = ?");
                stmt.setString(1, search.getText());
                rs = stmt.executeQuery();
                if(rs.next())
                {
                  //search.setEditable(false);
                  name.setText(rs.getString("name"));
                  price.setText(rs.getString("price"));
                  description.setText(rs.getString("description"));
                  stock.setText(rs.getString("stock"));
                  activate.setText(rs.getString("activate"));
                  return true;
                }
                else
                    JOptionPane.showMessageDialog(null, "Product does not exist"); 
            }catch(HeadlessException | SQLException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage()); 
            }
            return false;
    }
        public static void productSearch(String row, String rowText, JTextField pid, JTextField name, JTextField price, JTextField qty, JTextField description)
    {
        try
        {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement stmt;
            ResultSet rs;
            stmt = con.prepareStatement("SELECT * FROM `product` WHERE `"+row+"` LIKE '%"+rowText+"%'");
            rs = stmt.executeQuery();
            if(rs.next()){
                pid.setText(rs.getString(1));
                name.setText(rs.getString(2));
                price.setText(rs.getString(3));
                description.setText(rs.getString(4));
                qty.setText("1");
            }else{
                switch(row)
                {
                    case "name":
                        JOptionPane.showMessageDialog(null, "No product with name '"+rowText+"'");
                        pid.setText("");
                        price.setText("");
                        description.setText("");
                        qty.setText("");
                        break;
                    case "price":
                        JOptionPane.showMessageDialog(null, "No customer with Price '"+rowText+"'");
                        pid.setText("");
                        name.setText("");
                        description.setText("");
                        qty.setText("");
                        break;
                    case "description":
                        JOptionPane.showMessageDialog(null, "No customer with Description '"+rowText+"'");
                        pid.setText("");
                        price.setText("");
                        name.setText("");
                        qty.setText("");
                        break;
                    case "id":
                        JOptionPane.showMessageDialog(null, "No customer with ID '"+rowText+"'");
                        name.setText("");
                        price.setText("");
                        description.setText("");
                        qty.setText("");
                        break;
                }
            }
        }catch(HeadlessException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

