
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class test {
    public static void main(String args[]) {
        String className = "com.mysql.jdbc.Driver";
        try {
            Class.forName(className);
            System.out.println("Drive loaded successfuly");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not loaded successfuly");
            System.out.println(ex.getMessage());
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
