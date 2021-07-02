/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class CustomerValidation {
        public static boolean isCustomerPhoneExist(String phone){
        boolean isExist = false;
        Connection con = ConnectionProvider.getCon();
        PreparedStatement stmt;
        ResultSet rs;
        try{
            stmt = con.prepareStatement("SELECT * FROM `customer` WHERE `phone` = ?");
            stmt.setString(1, phone);
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
    public static boolean isCustomerEmailExist(String email){
        boolean isExist = false;
        Connection con = ConnectionProvider.getCon();
        PreparedStatement stmt;
        ResultSet rs;
        try{
            stmt = con.prepareStatement("SELECT * FROM `customer` WHERE `email` = ?");
            stmt.setString(1, email);
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
    
    public static boolean validateData(JTextField name, JTextField phone,JTextField email, JTextField address)
    {
        if(name.getText().equals("") || phone.getText().equals("") || email.getText().equals("") || address.getText().equals(""))
        {
            return false;
        }
        else return validatePhone(phone.getText());
    }
        public static boolean validateData(JTextField name, JTextField phone,JTextField email, JTextField address,JTextField gender )
    {
        if(name.getText().equals("") || phone.getText().equals("") || email.getText().equals("") || address.getText().equals("") || gender.getText().equals(""))
        {
            return false;
        }
        else
            return true;
    }
    public static void resetCustomerForm(JTextField name, JTextField phone,JTextField email, JTextField address, JComboBox gender)
    {
        name.setText("");
        phone.setText("");
        email.setText("");
        address.setText("");
        gender.setSelectedItem("Male");
    }
        public static void resetCustomerForm(JTextField name, JTextField phone,JTextField email, JTextField address, JTextField gender)
    {
        name.setText("");
        phone.setText("");
        email.setText("");
        address.setText("");
        gender.setText("");
    }
    public static boolean getCustomerByPhone(JTextField search, JTextField name, JTextField phone,JTextField email, JTextField address, JTextField gender)
    {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement stmt;
            ResultSet rs;
            try
            {
                stmt = con.prepareStatement("SELECT * FROM `customer` WHERE `phone` = ?");
                stmt.setString(1, search.getText());
                rs = stmt.executeQuery();
                if(rs.next())
                {
                  //search.setEditable(false);
                  name.setText(rs.getString("name"));
                  phone.setText(rs.getString("phone"));
                  email.setText(rs.getString("email"));
                  address.setText(rs.getString("address"));
                  gender.setText(rs.getString("Gender"));
                  return true;
                }
                else
                    JOptionPane.showMessageDialog(null, "Customer does not exist"); 
            }catch(HeadlessException | SQLException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage()); 
            }
            return false;
    }
    public static boolean validatePhone(String phone){
    boolean validated = true;
    Pattern regExPattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$");
    Matcher regExMatcher = regExPattern.matcher(phone);
    if(!regExMatcher.matches()){
        validated = false;
    }
    return validated;
}
    public static void styleTableHeader(JTable table)
    {
        JTableHeader header = table.getTableHeader();
//        header.setBackground(Color.black);
        header.setForeground(Color.white);
        header.setFont(new Font("tahoma", Font.BOLD, 14));
    }
    public static void customerSearch(String row, String rowText, JTextField name, JTextField contact, JTextField email, JTextField address)
    {
        try
        {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement stmt;
            ResultSet rs;
            stmt = con.prepareStatement("SELECT * FROM `customer` WHERE `"+row+"` LIKE '%"+rowText+"%'");
            rs = stmt.executeQuery();
            if(rs.next()){
                name.setText(rs.getString(2));
                contact.setText(rs.getString(3));
                email.setText(rs.getString(4));
                address.setText(rs.getString(5));
            }else{
                switch(row)
                {
                    case "name":
                        JOptionPane.showMessageDialog(null, "No customer with name '"+rowText+"'");
                        contact.setText("");
                        email.setText("");
                        address.setText("");
                        break;
                    case "email":
                        JOptionPane.showMessageDialog(null, "No customer with email '"+rowText+"'");
                        contact.setText("");
                        name.setText("");
                        address.setText("");
                        break;
                    case "address":
                        JOptionPane.showMessageDialog(null, "No customer with address '"+rowText+"'");
                        contact.setText("");
                        email.setText("");
                        name.setText("");
                        break;
                    case "phone":
                        JOptionPane.showMessageDialog(null, "No customer with phone '"+rowText+"'");
                        name.setText("");
                        email.setText("");
                        address.setText("");
                        break;
                }
            }
        }catch(HeadlessException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

