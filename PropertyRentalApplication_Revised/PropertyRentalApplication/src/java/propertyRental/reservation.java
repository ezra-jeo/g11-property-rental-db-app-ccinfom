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
public class reservation {

    public int reservationID;
    public int guestID;
    public int hostID;
    public int propertyListingID;
    public Date startDate;
    public Date endDate;
    public float totalPrice;
    
    public ArrayList<reservation> reservationList = new ArrayList<>();
    
    public reservation(int guestID, int hostID, int propertyListingID, Date startDate, Date endDate) {
        this.reservationID = 0;
        this.guestID = guestID;
        this.hostID = hostID;
        this.propertyListingID = propertyListingID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = getPrice(propertyListingID) * getDaysBetween(startDate, endDate);
    }
    
    public reservation(int reservationID, int guestID, int hostID, int propertyListingID, Date startDate, Date endDate, float totalPrice) {
        this.reservationID = reservationID;
        this.guestID = guestID;
        this.hostID = hostID;
        this.propertyListingID = propertyListingID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }
    
    public reservation() {
        
    }
    
    public static long getDaysBetween(Date startDate, Date endDate) {
        long start = startDate.getTime();
        long end = endDate.getTime();
        long diff = start - end;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return Math.abs(diffDays);
    }
    
    public static float getPrice(int propertyListingID) {
        float price = 0;
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT pricePerNight FROM propertyListing WHERE propertyListingID = ?;");
            ps.setInt(1, propertyListingID);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                price = rs.getFloat("pricePerNight");
            }
            
            ps.close();
            conn.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
        return price;

    }
    
    public int createReservation() {
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT max(reservationID) + 1 AS newReservationID FROM reservation;");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                this.reservationID = rs.getInt("newReservationID");
            }
            
            ps = conn.prepareStatement("INSERT INTO reservation (reservationID, guestID, hostID, propertyListingID, "
                    + "startDate, endDate, totalPrice) "
                    + "VALUE (?, ?, ?, ?, ?, ?, ?)");
            
            ps.setInt(1, this.reservationID);
            ps.setInt(2, this.guestID);
            ps.setInt(3, this.hostID);
            ps.setInt(4, this.propertyListingID);
            ps.setDate(5, this.startDate);
            ps.setDate(6, this.endDate);
            ps.setFloat(7, this.totalPrice);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
        return 1;
    }
    
    public static int checkAvailableForReservation(int propertyListingID, Date startDate, Date endDate) {
        int count = 0;
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(reservationID) AS count FROM reservation WHERE propertyListingID = ? AND NOT (endDate < ? OR startDate > ?);");
            ps.setInt(1, propertyListingID);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                count = rs.getInt("count");
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
    
        public static reservation getReservationRecord(int reservationID) {
        reservation reservationRecord = null;
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM reservation WHERE reservationID = ? ;");
            ps.setInt(1, reservationID);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                reservationRecord = new reservation(rs.getInt("reservationID"), rs.getInt("guestID"), rs.getInt("hostID"), rs.getInt("propertyListingID"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getFloat("totalPrice"));
            }
            
            ps.close();
            conn.close();
                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return reservationRecord;
    }

    public static ArrayList<reservation> getReservationHost(int hostID) {
        ArrayList<reservation> reservationList = new ArrayList<>();
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM reservation WHERE hostID = ?;");
            ps.setInt(1, hostID);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                reservationList.add(new reservation(rs.getInt("reservationID"), rs.getInt("guestID"), rs.getInt("hostID"), rs.getInt("propertyListingID"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getFloat("totalPrice")));
            }

            ps.close();
            conn.close();            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return reservationList;
    }
    
    public static ArrayList<reservation> getReservationGuest(int guestID) {
        ArrayList<reservation> reservationList = new ArrayList<>();
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM reservation WHERE guestID = ?;");
            ps.setInt(1, guestID);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                reservationList.add(new reservation(rs.getInt("reservationID"), rs.getInt("guestID"), rs.getInt("hostID"), rs.getInt("propertyListingID"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getFloat("totalPrice")));
            }

            ps.close();
            conn.close();            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return reservationList;
    }
    
    public static ArrayList<reservation> getReservationGuestHost(int guestID,int hostID) {
        ArrayList<reservation> reservationList = new ArrayList<>();
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM reservation WHERE guestID = ? AND hostID = ?;");
            ps.setInt(1, guestID);
            ps.setInt(2, hostID);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                reservationList.add(new reservation(rs.getInt("reservationID"), rs.getInt("guestID"), rs.getInt("hostID"), rs.getInt("propertyListingID"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getFloat("totalPrice")));
            }

            ps.close();
            conn.close();            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return reservationList;
    }
    
    public static ArrayList<reservation> getReservationProperty(int propertyID) {
        ArrayList<reservation> reservationList = new ArrayList<>();
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM reservation WHERE propertyListingID = ?;");
            ps.setInt(1, propertyID);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                reservationList.add(new reservation(rs.getInt("reservationID"), rs.getInt("guestID"), rs.getInt("hostID"), rs.getInt("propertyListingID"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getFloat("totalPrice")));
            }

            ps.close();
            conn.close();            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return reservationList;
    }
    
    public static void deleteReservationRecord(int reservationID) {
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("DELETE FROM reservation WHERE reservationID = ? ;");
            ps.setInt(1, reservationID);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String args[]) {
    }
}