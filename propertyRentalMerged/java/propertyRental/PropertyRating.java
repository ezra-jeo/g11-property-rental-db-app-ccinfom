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

public class PropertyRating {
    private int reservationID;
    private String review;
    private Double rating;

    private ArrayList<PropertyRating> propertyRatingList;

    public PropertyRating(int reservationID, String review, Double rating) {
        this.reservationID = reservationID;
        this.review = review;
        this.rating = rating;
    }

    public PropertyRating() {
    }

    public int addRating(int reservationID, String review, Double rating) {
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
            checkReservation.setInt(1, reservationID);
            ResultSet rs = checkReservation.executeQuery();

            if (rs.next() && rs.getInt("count") == 0) {
                System.out.println("Error: Reservation does not exist.");
                checkReservation.close();
                conn.close();
                return 0;
            }
            checkReservation.close();

            PreparedStatement checkIfRated = conn.prepareStatement(
                "SELECT COUNT(*) AS count FROM propertyRating WHERE reservationID = ?"
            );
            checkIfRated.setInt(1, reservationID);
            rs = checkIfRated.executeQuery();

            if (rs.next() && rs.getInt("count") > 0) {
                System.out.println("Error: Reservation already has a property rating.");
                checkIfRated.close();
                conn.close();
                return 0;
            }
            checkIfRated.close();

            PreparedStatement insertRating = conn.prepareStatement(
                "INSERT INTO propertyRating (reservationID, review, rating) VALUES (?, ?, ?)"
            );
            insertRating.setInt(1, reservationID);
            insertRating.setString(2, review);
            insertRating.setDouble(3, rating);

            insertRating.executeUpdate();
            System.out.println("Success: Rating added.");

            insertRating.close();
            conn.close();
            return 1;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int getRatingList(int propertyListingID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "ethanaxl1"
            );

            PreparedStatement ratingsStmt = conn.prepareStatement("SELECT reservationID, review, rating "
                    + "FROM propertyRating "
                    + "WHERE reservationID IN "
                    + "(SELECT reservationID FROM reservation WHERE propertyListingID = ?)");
            ratingsStmt.setInt(1, propertyListingID);
            ResultSet ratingsRS = ratingsStmt.executeQuery();

            propertyRatingList = new ArrayList<>();
            while (ratingsRS.next()) {
                this.reservationID = ratingsRS.getInt("reservationID");
                this.review = ratingsRS.getString("review");
                this.rating = ratingsRS.getDouble("rating");

                propertyRatingList.add(new PropertyRating(this.reservationID, this.review, this.rating));
            }

            ratingsRS.close();
            ratingsStmt.close();
            conn.close();

            return 1;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    public String viewRating() {
        return String.format("%.2f - \"%s\"", this.rating, this.review);
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

    public ArrayList<PropertyRating> getPropertyRatingList() {
        return this.propertyRatingList;
    }
}

