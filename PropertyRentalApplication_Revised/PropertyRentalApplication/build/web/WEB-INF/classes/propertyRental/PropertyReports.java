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

/**
 *
 * @author Elisha
 */
public class PropertyReports {
    public int propertylistingID;
    public double totalEarnings;
    public int reservations;
    public String month;
    public int year;
    public int reviews;
    public String country;
    public String province;
    public int propertyCount;
    public double avgPpn;
    public int availableCount;


    
public PropertyReports(int propertylistingID, double totalEarnings, int reservations, String month, int year, int reviews, String country,
                           String province, int propertyCount, double avgPpn, int availableCount) {
        this.propertylistingID = propertylistingID;
        this.totalEarnings = totalEarnings;
        this.reservations = reservations;
        this.month = month;
        this.year = year;
        this.reviews = reviews;
        this.province = province;
        this.country = country;
        this.propertyCount = propertyCount;
        this.avgPpn = avgPpn;
        this.availableCount = availableCount;
    }

    public PropertyReports() {}
    
    public String getPropertyReport() {
        StringBuilder propertyReport = new StringBuilder();
        
        propertyReport.append("propertylistingID")
                    .append(" - ")
                    .append("total earnings")
                    .append(" - ")
                    .append("# of reservations")
                    .append(" - ")
                    .append("reviews")
                    .append(" - ")
                    .append("date")
                    .append("\n");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyRental",
                "root",
                "ethanaxl1"
            );
              
           PreparedStatement st = conn.prepareStatement(
                "SELECT r.propertyListingID, SUM(r.totalPrice), COUNT(r.reservationID), COUNT(pr.reservationID), MONTHNAME(t.date), YEAR(t.date)\n" +
                "FROM reservation r\n" +
                "LEFT JOIN propertylisting p ON r.propertylistingID=p.propertylistingID\n" +
                "LEFT JOIN transaction t ON r.reservationID=t.reservationID\n" +
                "LEFT JOIN propertyrating pr ON r.reservationID=pr.reservationID\n" +
                "WHERE pr.rating != \"\"\n" +
                "GROUP BY r.propertyListingID, MONTHNAME(t.date), YEAR(t.date)\n" +
                "ORDER BY r.propertyListingID, YEAR(t.date)\n" +
                ";"
            );

            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                int propertylistingID = rs.getInt("propertyListingID");
                double totalEarnings = rs.getDouble("SUM(r.totalPrice)");
                int reservations = rs.getInt("COUNT(r.reservationID)");
                int reviews = rs.getInt("COUNT(pr.reservationID)");
                String month = rs.getString("MONTHNAME(t.date)");
                int year = rs.getInt("YEAR(t.date)");
                
                propertyReport
                    .append(propertylistingID)
                    .append(" - ")
                    .append(totalEarnings)
                    .append(" - ")
                    .append(reservations)
                    .append(" reservations - ")
                    .append(reviews)
                    .append(" reviews - ")
                    .append(month)
                    .append(" ")
                    .append(year)
                    .append("\n");
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
        
        propertyReport.append("country")
                    .append(" - ")
                    .append("province")
                    .append(" - ")
                    .append("# of properties")
                    .append(" - ")
                    .append("AVG price per night")
                    .append(" - ")
                    .append("# of available property")
                    .append("\n");
        
        
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyRental",
                "root",
                "ethanaxl1"
            );
              
            String query = "SELECT country, province, COUNT(propertylistingID) AS propertyCount, " +
                           "AVG(pricePerNight) AS avgPpn, " +
                           "COUNT(CASE WHEN status = 'Available' THEN 1 END) AS availableCount " +
                           "FROM propertylisting " +
                           "GROUP BY province, country";

            PreparedStatement st = conn.prepareStatement(query);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String country = rs.getString("country");
                String province = rs.getString("province");
                int propertyCount = rs.getInt("propertyCount");
                double avgPpn = rs.getDouble("avgPpn");
                int availableCount = rs.getInt("availableCount");

                propertyReport.append(country)
                    .append(" - ")
                    .append(province)
                    .append(" - ")
                    .append(propertyCount)
                    .append(" properties - ")
                    .append(String.format("%.2f", avgPpn))
                    .append(" avg per night - ")
                    .append(availableCount)
                    .append(" available properties")
                    .append("\n");
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

    
    

