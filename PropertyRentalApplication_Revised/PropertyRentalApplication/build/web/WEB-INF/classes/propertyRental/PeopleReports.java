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
public class PeopleReports {
    public int userID;
    public String userName;
    public double rating;
    public double totalearnings;
    public String monthName;
    public int years;
    
    public PeopleReports(int userID, String userName, double rating, double totalearnings, String monthName, int years ) {
        this.userID = userID;
        this.userName = userName;
        this.rating = rating;
        this.totalearnings = totalearnings;
        this.monthName = monthName;
        this.years = years;
    }

    public PeopleReports() {}
    
    public String getHostReport() {
        StringBuilder hostReport = new StringBuilder();
        
        hostReport.append("hostID")
                    .append(" - ")
                    .append("userName")
                    .append(" - ")
                    .append("rating")
                    .append(" - ")
                    .append("total earnings")
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
                "SELECT h.hostID, h.userName, AVG(hr.rating), SUM(r.totalPrice), MONTHNAME(t.date), YEAR(t.date)\n" +
                "FROM host h\n" +
                "LEFT JOIN reservation r ON h.hostID=r.hostID\n" +
                "LEFT JOIN hostrating hr ON r.reservationID=hr.reservationID\n" +
                "LEFT JOIN transaction t ON r.reservationID=t.reservationID\n" +
                "GROUP BY h.hostID, MONTHNAME(t.date), YEAR(t.date)\n" +
                "ORDER BY h.hostID, YEAR(t.date)\n" +
                ";"
            );

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                int userID = rs.getInt("hostID");
                String userName = rs.getString("userName");
                double rating = rs.getDouble("AVG(hr.rating)");
                double totalearnings = rs.getDouble("SUM(r.totalPrice)");
                String monthName = rs.getString("MONTHNAME(t.date)");
                int years = rs.getInt("YEAR(t.date)");
                
                hostReport.append(userID)
                    .append(" - ")
                    .append(userName)
                    .append(" - ")
                    .append(rating)
                    .append(" - ")
                    .append(totalearnings)
                    .append(" - ")
                    .append(monthName)
                    .append(" ")
                    .append(years)
                    .append("\n");
            }

            rs.close();
            st.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return hostReport.toString();
}
      
        public String getGuestReport() {
        StringBuilder guestReport = new StringBuilder();
        
        guestReport.append("guestID")
                    .append(" - ")
                    .append("# of reservations")
                    .append(" - ")
                    .append("rating")
                    .append(" - ")
                    .append("total spendings")
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
                "SELECT r.guestID, count(r.reservationID), AVG(gr.rating), SUM(r.totalPrice), MONTHNAME(t.date), YEAR(t.date)\n" +
                "FROM reservation r\n" +
                "LEFT JOIN guest g ON r.guestID=g.guestID\n" +
                "LEFT JOIN guestrating gr ON r.reservationID=gr.reservationID\n" +
                "LEFT JOIN transaction t ON r.reservationID=t.reservationID\n" +
                "GROUP BY r.guestID, MONTHNAME(t.date), YEAR(t.date)\n" +
                "ORDER BY r.guestID, YEAR(t.date)\n" +
                ";"
            );
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                int userID = rs.getInt("guestID");
                int reservationCount = rs.getInt("count(r.reservationID)");
                double rating = rs.getDouble("AVG(gr.rating)");
                double totalspendings = rs.getDouble("SUM(r.totalPrice)");
                String month = rs.getString("MONTHNAME(t.date)");
                int years = rs.getInt("YEAR(t.date)");
                
                
                guestReport.append(userID)
                    .append(" - ")
                    .append(reservationCount)
                    .append(" reservations")
                    .append(" - ")
                    .append(rating)
                    .append(" - ")
                    .append(totalspendings)
                    .append(" - ")
                    .append(month)
                    .append(" ")
                    .append(years)
                    .append("\n");
            }

            rs.close();
            st.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return guestReport.toString();
}
       
}


    
    

