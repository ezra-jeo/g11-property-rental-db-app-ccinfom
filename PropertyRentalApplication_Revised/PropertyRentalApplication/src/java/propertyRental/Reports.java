package propertyRental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reports {

    
     private static final String DB_URL = "jdbc:sqlserver://localhost:3306;databaseName=propertyrental;user=user;password=password;";
     
    public static List<String[]> getDataFromDatabase() {
        List<String[]> data = new ArrayList<>();

        String sql = "SELECT h.hostID, h.userName, AVG(hr.rating), SUM(r.totalPrice), MONTHNAME(t.date) " +
                     "FROM host h " +
                     "LEFT JOIN reservation r ON h.hostID = r.hostID " +
                     "LEFT JOIN hostrating hr ON r.reservationID = hr.reservationID " +
                     "LEFT JOIN transaction t ON r.reservationID = t.reservationID " +
                     "GROUP BY h.hostID, MONTHNAME(t.date);";

         try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                String hostID = resultSet.getString("hostID");
                String userName = resultSet.getString("userName");
                String avgRating = resultSet.getString("AVG(hr.rating)");
                String totalPrice = resultSet.getString("SUM(r.totalPrice)");
                String monthName = resultSet.getString("MONTHNAME(t.date)");

                data.add(new String[]{hostID, userName, avgRating, totalPrice, monthName});
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()); 
        }

        return data;
    }
}