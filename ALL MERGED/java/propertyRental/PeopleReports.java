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
public class PeopleReports {
    public int userID;
    public String userName;
    public String password;
    public String firstName;
    public String lastName;
    public String description;
    public String joinDate;
    public String email;
    public int number;
    
    public PeopleReports(int userID, String userName, String password, String firstName, String lastName, 
                       String description, String joinDate, String email, int number) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.joinDate = joinDate;
        this.email = email;
        this.number = number;
    }

    public PeopleReports() {}
    
    public String getHostMonth() {
        StringBuilder hostReport = new StringBuilder();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "Qryebfnevb_SQL2025"
            );
              
           PreparedStatement st = conn.prepareStatement(
                "SELECT h.hostID, h.userName, AVG(hr.rating), SUM(r.totalPrice), MONTHNAME(t.date)\n" +
                "FROM host h\n" +
                "LEFT JOIN reservation r ON h.hostID=r.hostID\n" +
                "LEFT JOIN hostrating hr ON r.reservationID=hr.reservationID\n" +
                "LEFT JOIN transaction t ON r.reservationID=t.reservationID\n" +
                "GROUP BY h.hostID, MONTHNAME(t.date)\n" +
                ";"
            );
            st.setInt(1, userID);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                String userNames = rs.getString("userName");
                double rating = rs.getDouble("AVG(hr.rating)");
                double totalearnings = rs.getDouble("SUM(r.totalPrice)");
                String monthName = rs.getString("MONTHNAME(t.date)");
                
                
                hostReport.append(userID)
                    .append(" - ")
                    .append(userNames)
                    .append(" - ")
                    .append(rating)
                    .append(" - ")
                    .append(totalearnings)
                    .append(" - ")
                    .append(monthName);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return hostReport.toString();
}
    
    
        public String getHostYear() {
        StringBuilder hostReport = new StringBuilder();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "Qryebfnevb_SQL2025"
            );
              
           PreparedStatement st = conn.prepareStatement(
                "SELECT h.hostID, h.userName, AVG(hr.rating), SUM(r.totalPrice), YEAR(t.date)\n" +
                "FROM host h\n" +
                "LEFT JOIN reservation r ON h.hostID=r.hostID\n" +
                "LEFT JOIN hostrating hr ON r.reservationID=hr.reservationID\n" +
                "LEFT JOIN transaction t ON r.reservationID=t.reservationID\n" +
                "GROUP BY h.hostID, YEAR(t.date)\n" +
                ";"
            );
            st.setInt(1, userID);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                String userNames = rs.getString("userName");
                double rating = rs.getDouble("AVG(hr.rating)");
                double totalearnings = rs.getDouble("SUM(r.totalPrice)");
                String year = rs.getString("YEAR(t.date)");
                
                
                hostReport.append(userID)
                    .append(" - ")
                    .append(userNames)
                    .append(" - ")
                    .append(rating)
                    .append(" - ")
                    .append(totalearnings)
                    .append(" - ")
                    .append(year);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return hostReport.toString();
}
      
         public String getGuestMonth() {
        StringBuilder guestReport = new StringBuilder();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "Qryebfnevb_SQL2025"
            );
              
           PreparedStatement st = conn.prepareStatement(
                "SELECT r.guestID, count(r.reservationID), AVG(gr.rating), SUM(r.totalPrice), MONTHNAME(t.date)\n" +
                "FROM reservation r\n" +
                "LEFT JOIN guest g ON r.guestID=g.guestID\n" +
                "LEFT JOIN guestrating gr ON r.reservationID=gr.reservationID\n" +
                "LEFT JOIN transaction t ON r.reservationID=t.reservationID\n" +
                "GROUP BY r.guestID, MONTHNAME(t.date)\n" +
                "ORDER BY r.guestID\n" +
                ";"
            );
            st.setInt(1, userID);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                int reservationCount = rs.getInt("count(r.reservationID)");
                double rating = rs.getDouble("AVG(gr.rating)");
                double totalspendings = rs.getDouble("SUM(r.totalPrice)");
                String month = rs.getString("MONTHNAME(t.date)");
                
                
                guestReport.append(userID)
                    .append(" - ")
                    .append(reservationCount)
                    .append(" reservations")
                    .append(" - ")
                    .append(rating)
                    .append(" - ")
                    .append(totalspendings)
                    .append(" - ")
                    .append(month);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return guestReport.toString();
}
         
         public String getGuestYear() {
        StringBuilder guestReport = new StringBuilder();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "Qryebfnevb_SQL2025"
            );
              
           PreparedStatement st = conn.prepareStatement(
                "SELECT r.guestID, count(r.reservationID), AVG(gr.rating), SUM(r.totalPrice), YAER(t.date)\n" +
                "FROM reservation r\n" +
                "LEFT JOIN guest g ON r.guestID=g.guestID\n" +
                "LEFT JOIN guestrating gr ON r.reservationID=gr.reservationID\n" +
                "LEFT JOIN transaction t ON r.reservationID=t.reservationID\n" +
                "GROUP BY r.guestID, YEAR(t.date)\n" +
                "ORDER BY r.guestID\n" +
                ";"
            );
            st.setInt(1, userID);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                int reservationCount = rs.getInt("count(r.reservationID)");
                double rating = rs.getDouble("AVG(gr.rating)");
                double totalspendings = rs.getDouble("SUM(r.totalPrice)");
                String year = rs.getString("YEAR(t.date)");
                
                
                guestReport.append(userID)
                    .append(" - ")
                    .append(reservationCount)
                    .append(" reservations")
                    .append(" - ")
                    .append(rating)
                    .append(" - ")
                    .append(totalspendings)
                    .append(" - ")
                    .append(year);
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

    
    

