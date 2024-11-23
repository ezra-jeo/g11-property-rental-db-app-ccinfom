/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package property_listing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ezra
 */
public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/propertyrental", "root", "Qryebfnevb_SQL");
            PreparedStatement st = conn.prepareStatement("SELECT * FROM host");
            ResultSet rs = st.executeQuery();
            int id = rs.getInt("propertyListingID");
            System.out.println(id);
            
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
