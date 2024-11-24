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
public class guest {
    
    public int    guestID;
    public String userName;
    public String password;
    public String firstName;
    public String lastName;
    public String description;
    public Date   joinDate;
    public String email;
    public String phoneNumber;
    
    public ArrayList<guest> guestList = new ArrayList<>();

    public guest(String userName, String password, String firstName, String lastName, String description, String email, String phoneNumber) {
        this.guestID = 0;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.joinDate = Date.valueOf(LocalDate.now());
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public guest(int guestID, String userName, String password, String firstName, String lastName, String description, Date joinDate, String email, String phoneNumber) {
        this.guestID = guestID;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.joinDate = joinDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public int createGuest() {
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT max(guestID) + 1 AS newGuestID FROM guest;");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                this.guestID = rs.getInt("newGuestID");
            }
            
            ps = conn.prepareStatement("INSERT INTO guest (guestID, userName, password,firstName, "
                    + "lastName, description, joinDate, email, phoneNumber) "
                    + "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            ps.setInt(1, this.guestID);
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

    public static int checkGuestUserName(String checkUserName) {
        int count = 0;
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(userName) AS identicalCount FROM guest WHERE userName = ? ;");
            ps.setString(1, checkUserName);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                count = rs.getInt("identicalCount");
            }
            
            ps.close();
            conn.close();

                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
        return count;
    }


    public static int checkGuestAccount(String checkUserName, String checkPassword) {
        int accountID = -1;
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT guestID FROM guest WHERE userName = ? AND password = ? ;");
            ps.setString(1, checkUserName);
            ps.setString(2, checkPassword);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                accountID = rs.getInt("guestID");
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
        
    public static guest getGuestRecord(int guestID) {
        guest guestRecord = null;
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM guest WHERE guestID = ? ;");
            ps.setInt(1, guestID);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                guestRecord = new guest(rs.getInt("guestID"), rs.getString("userName"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("description"), rs.getDate("joinDate"), rs.getString("email"), rs.getString("phoneNumber"));
            }
            
            ps.close();
            conn.close();
                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return guestRecord;
    }
    
    public static ArrayList<guest> getGuestRecords() {
        ArrayList<guest> guestRecords = new ArrayList<>();
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM guest;");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                guestRecords.add(new guest(rs.getInt("guestID"), rs.getString("userName"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("description"), rs.getDate("joinDate"), rs.getString("email"), rs.getString("phoneNumber")));
            }
            
            ps.close();
            conn.close();
                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return guestRecords;
    }
    
    public static void updateGuestRecord(int guestID, String userName, String password, String firstName, String lastName, String description, String email, String phoneNumber) {
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
                
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("UPDATE guest SET userName = ?, password = ?, firstName = ?, lastName = ?, description = ?, email = ?, phoneNumber = ? WHERE guestID = ?");
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, description);
            ps.setString(6, email);
            ps.setString(7, phoneNumber);
            ps.setInt(8, guestID);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
    public static void deleteGuestRecord(int guestID) {
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("DELETE FROM guest WHERE guestID = ? ;");
            ps.setInt(1, guestID);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
    public static void main (String args[]) {
    }
}

