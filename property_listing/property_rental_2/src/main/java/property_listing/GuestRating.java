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
import java.util.*;

/**
 *
 * @author Ezra
 */

public class GuestRating {
    private int reservationID;
    private String review;
    private Double rating;

    public GuestRating(int reservationID, String review, Double rating) {
        this.reservationID = reservationID;
        this.review = review;
        this.rating = rating;
    }

    public GuestRating() {
    }

    public int addRating() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "Qryebfnevb_SQL2025"
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
               "SELECT COUNT(*) AS count FROM guestRating WHERE reservationID = ?"
            );
            checkIfRated.setInt(1, this.reservationID);

            rs = checkIfRated.executeQuery();
            if (rs.next() && rs.getInt("count") > 0) {
                System.out.println("Error: Reservation already rated.");
                checkIfRated.close();
                conn.close();
                return 0; // Reservation is already rated
            }
            checkIfRated.close();
            
            PreparedStatement insertRating = conn.prepareStatement(
                "INSERT INTO guestRating (reservationID, review, rating) VALUES (?, ?, ?)"
            );
            insertRating.setInt(1, this.reservationID);
            insertRating.setString(2, this.review);
            insertRating.setDouble(3, this.rating);

            insertRating.executeUpdate();
            System.out.println("Success: Guest Rating added.");

            insertRating.close();
            conn.close();
            return 1;
        } 
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0; 
        }
    }

    public String viewRating(int reservationID) {
        StringBuilder ratingDetails = new StringBuilder();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "Qryebfnevb_SQL2025"
            );

            PreparedStatement st = conn.prepareStatement(
                "SELECT rating, review FROM guestRating WHERE reservationID = ?"
            );
            st.setInt(1, reservationID);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                double rating = rs.getDouble("rating");
                String review = rs.getString("review");

                ratingDetails.append(reservationID)
                    .append(" - ")
                    .append(rating)
                    .append(" - ")
                    .append(review);
            } else {
                ratingDetails.append("No guest rating found for reservation ID: ").append(reservationID);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return "Error retrieving guest rating.";
        }

        return ratingDetails.toString();
    }

    // Getters and setters
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
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
