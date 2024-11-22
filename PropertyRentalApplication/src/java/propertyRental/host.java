/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package propertyRental;

import java.util.*;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.*;

/**
 *
 * @author ethanaxlburayag
 */
public class host {
    
    public int    hostID;
    public String userName;
    public String password;
    public String firstName;
    public String lastName;
    public String description;
    public Date   joinDate;
    public String email;
    public String phoneNumber;
    
    public ArrayList<host> hostList = new ArrayList<>();
    
    public host(String userName, String password, String firstName, String lastName,
                String description, String email, String phoneNumber) {
        this.hostID = 0;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.joinDate = Date.valueOf(LocalDate.now());
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public int createHost() {
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT max(hostID) + 1 AS newHostID FROM host;");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                this.hostID = rs.getInt("newHostID");
            }
            
            ps = conn.prepareStatement("INSERT INTO host (hostID, userName, password,firstName, "
                    + "lastName, description, joinDate, email, phoneNumber) "
                    + "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            ps.setInt(1, this.hostID);
            ps.setString(2, this.userName);
            ps.setString(3, this.password);
            ps.setString(4, this.firstName);
            ps.setString(5, this.lastName);
            ps.setString(6, this.description);
            ps.setDate(7, this.joinDate);
            ps.setString(8, this.email);
            ps.setString(9, this.phoneNumber);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
        return 1;
    } 
    
    public static int checkHostUserName(String checkUserName) {
        int count = 0;
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(userName) AS identicalCount FROM host WHERE userName = ? ;");
            ps.setString(1, checkUserName);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                count = rs.getInt("identicalCount");
            }
            
            ps.close();
            conn.close();
            
            if (count > 0)
                return 0;
                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
        return 1;
    }
    
    public static int checkHostAccount(String checkUserName, String checkPassword) {
        int accountID = 0;
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT hostID FROM host WHERE userName = ? AND password = ? ;");
            ps.setString(1, checkUserName);
            ps.setString(2, checkPassword);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                accountID = rs.getInt("hostID");
            }
            
            ps.close();
            conn.close();
            
            if (accountID == 0)
                return 0;
                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
        return accountID;
    }
    
    public static void main (String args[]) {
        //host h = new host("A", "B", "C", "D","E", "F", "G");
        //h.createHost();
        
        //System.out.println(host.checkHostUserName("A"));
        
        System.out.println(host.checkHostAccount("A","C"));

    }
}