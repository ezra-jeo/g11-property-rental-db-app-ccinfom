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
 * @author Elisha
 */
public class PropertyReports {
    public int propertylistingID;
    public double totalEarnings;
    public int reservations;
    public String times;
    public int reviews;
    
    public String province;
    public int propertyCount;
    public double avgPpn;
    public int availableCount;


    
public PropertyReports(int propertylistingID, double totalEarnings, int reservations, String times, int reviews,
                           String province, int propertyCount, double avgPpn, int availableCount) {
        this.propertylistingID = propertylistingID;
        this.totalEarnings = totalEarnings;
        this.reservations = reservations;
        this.times = times;
        this.reviews = reviews;
        this.province = province;
        this.propertyCount = propertyCount;
        this.avgPpn = avgPpn;
        this.availableCount = availableCount;
    }

    public PropertyReports() {}
    
    public String getPropertyMonth() {
        StringBuilder propertyReport = new StringBuilder();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "Qryebfnevb_SQL2025"
            );
              
           PreparedStatement st = conn.prepareStatement(
                "SELECT r.propertyListingID, SUM(r.totalPrice), COUNT(r.reservationID), COUNT(pr.reservationID), MONTHNAME(t.date)\n" +
                "FROM reservation r\n" +
                "LEFT JOIN propertylisting p ON r.propertylistingID=p.propertylistingID\n" +
                "LEFT JOIN transaction t ON r.reservationID=t.reservationID\n" +
                "LEFT JOIN propertyrating pr ON r.reservationID=pr.reservationID\n" +
                "WHERE pr.rating is not null\n" +
                "GROUP BY r.propertyListingID, MONTHNAME(t.date)\n" +
                "ORDER BY r.propertyListingID\n" +
                ";"
            );
            st.setInt(1, propertylistingID);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                double totalEarnings = rs.getDouble("SUM(r.totalPrice)");
                int reservations = rs.getInt("COUNT(r.reservationID)");
                int reviews = rs.getInt("COUNT(pr.reservationID)");
                String times = rs.getString("MONTHNAME(t.date)");
                
                
                propertyReport.append(propertylistingID)
                    .append(" - ")
                    .append(totalEarnings)
                    .append(" - ")
                    .append(reservations)
                    .append(" reservations - ")
                    .append(reviews)
                    .append(" reviews - ")
                    .append(times);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return propertyReport.toString();
}
    
    public String getPropertyYear() {
        StringBuilder propertyReport = new StringBuilder();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "Qryebfnevb_SQL2025"
            );
              
           PreparedStatement st = conn.prepareStatement(
                "SELECT r.propertyListingID, SUM(r.totalPrice), COUNT(r.reservationID), COUNT(pr.reservationID), YEAR(t.date)\n" +
                "FROM reservation r\n" +
                "LEFT JOIN propertylisting p ON r.propertylistingID=p.propertylistingID\n" +
                "LEFT JOIN transaction t ON r.reservationID=t.reservationID\n" +
                "LEFT JOIN propertyrating pr ON r.reservationID=pr.reservationID\n" +
                "WHERE pr.rating is not null\n" +
                "GROUP BY r.propertyListingID, YEAR(t.date)\n" +
                "ORDER BY r.propertyListingID\n" +
                ";"
            );
            st.setInt(1, propertylistingID);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                double totalEarnings = rs.getDouble("SUM(r.totalPrice)");
                int reservations = rs.getInt("COUNT(r.reservationID)");
                int reviews = rs.getInt("COUNT(pr.reservationID)");
                String times = rs.getString("YEAR(t.date)");
                
                
                propertyReport.append(propertylistingID)
                    .append(" - ")
                    .append(totalEarnings)
                    .append(" - ")
                    .append(reservations)
                    .append(" reservations - ")
                    .append(reviews)
                    .append(" reviews - ")
                    .append(times);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return propertyReport.toString();
}
        public String getLocationReport() {
        StringBuilder propertyReport = new StringBuilder();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "Qryebfnevb_SQL2025"
            );
              
           PreparedStatement st = conn.prepareStatement(
                "SELECT province, COUNT(propertylistingID), AVG(pricePerNight), COUNT(CASE WHEN status = 'Available' THEN 1 END)\n" +
                "FROM propertylisting\n" +
                "GROUP BY province" +
                ";"
            );
            st.setString(1, province);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                int propertyCount = rs.getInt("COUNT(propertylistingID)");
                double avgPpn = rs.getDouble("AVG(pricePerNight)");
                int availableCount = rs.getInt("COUNT(CASE WHEN status = 'Available' THEN 1 END)");
                
                
                propertyReport.append(province)
                    .append(" - ")
                    .append(propertyCount)
                    .append(" properties - ")
                    .append(avgPpn)
                    .append(" - ")
                    .append(availableCount)
                    .append(" available places");
            }

            rs.close();
            st.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return propertyReport.toString();
}
}

    
    

