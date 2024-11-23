/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package propertyRental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Ezra
 */
public class HostRating {
    private int reservationID;
    private String review;
    private Double rating;

    private ArrayList<HostRating> hostRatingList;
    
    public HostRating(int reservationID, String review, Double rating) {
        this.reservationID = reservationID;
        this.review = review;
        this.rating = rating;
    }
   
    public HostRating() {
        
    }
    
    public int addRating(int reservationID, String review, Double rating){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "ethanaxl1"
            );

            PreparedStatement checkReservation = conn.prepareStatement(
                "SELECT COUNT(*) AS count FROM reservation WHERE reservationID = ?"
            );
            checkReservation.setInt(1, this.reservationID);

            ResultSet rs = checkReservation.executeQuery();
            if (rs.next() && rs.getInt("count") == 0) {
                System.out.println("Error: Reservation does not exist.");
                checkReservation.close();
                conn.close();
                return 0; 
            }
            checkReservation.close();
            
            PreparedStatement checkIfRated = conn.prepareStatement(
               "SELECT COUNT(*) AS count FROM hostRating WHERE reservationID = ?"
            );
            checkIfRated.setInt(1, this.reservationID);

            rs = checkIfRated.executeQuery();
            if (rs.next() && rs.getInt("count") > 0) {
                System.out.println("Error: Reservation already has a host rating.");
                checkIfRated.close();
                conn.close();
                return 0; // Reservation is already rated
            }
            checkIfRated.close();
            
            PreparedStatement insertRating = conn.prepareStatement(
                "INSERT INTO hostRating (reservationID, review, rating) VALUES (?, ?, ?)"
            );
            insertRating.setInt(1, reservationID);
            insertRating.setString(2, review);
            insertRating.setDouble(3, rating);

            insertRating.executeUpdate();
            System.out.println("Success: Rating added.");

            insertRating.close();
            conn.close();
            return 1;
        } 
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0; 
        }
    }
    
    public int getRatingList(int hostID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC", 
                "root", 
                "Qryebfnevb_SQL2025"
            );

            String reservationQuery = "SELECT reservationID FROM reservation WHERE hostID = ?";
            PreparedStatement reservationStmt = conn.prepareStatement(reservationQuery);
            reservationStmt.setInt(1, hostID);
            ResultSet reservationRS = reservationStmt.executeQuery();

            ArrayList<Integer> reservationIDs = new ArrayList<>();
            while (reservationRS.next()) {
                reservationIDs.add(reservationRS.getInt("reservationID"));
            }

            reservationRS.close();
            reservationStmt.close();

            if (reservationIDs.isEmpty()) {
                System.out.println("No reservations found for hostID: " + hostID);
                return 0; 
            }

            PreparedStatement ratingsStmt = conn.prepareStatement("SELECT reservationID, review, rating "
                    + "FROM hostRating "
                    + "WHERE reservationID IN "
                    + "(SELECT reservationID "
                    + "FROM reservation "
                    + "WHERE hostID = ?)");
            ratingsStmt.setInt(1, hostID);
            ResultSet ratingsRS = ratingsStmt.executeQuery();

            hostRatingList = new ArrayList<>();
            while (ratingsRS.next()) {
                this.reservationID = ratingsRS.getInt("reservationID");
                this.review = ratingsRS.getString("review");
                this.rating = ratingsRS.getDouble("rating");

                this.hostRatingList.add(new HostRating(this.reservationID, this.review, this.rating));
            }

            ratingsRS.close();
            ratingsStmt.close();
            conn.close();

            return 1;
        } 
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return 0; 
        }
    }
    
    public String viewRating() {
        String result = String.format("%.2f - \"%s\"", this.rating, this.review);
        
        return result;
    }
    
    public static void main(String[] args) {
        HostRating test = new HostRating(10000010, "good", 4.5);
        int status = test.getRatingList(10000004);
//        System.out.println(test.viewRating(10000001));
//        int status = test.addRating();
        System.out.println(test.getHostRatingList().get(0).viewRating());
        System.out.println(status);
    }
    
    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getRating() {
        return this.rating;
    }
        
    public void setRating(Double rating) {
        this.rating = rating;
    }

    public ArrayList<HostRating> getHostRatingList() {
        return this.hostRatingList;
    }
    
}
