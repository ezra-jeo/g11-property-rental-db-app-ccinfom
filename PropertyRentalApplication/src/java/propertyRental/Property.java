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
public class Property {
    private int ID;
    private int hostID;
    private String listingName;
    private String description;
    private Double pricePerNight;
    private String street;
    private String city;
    private String province;
    private String country;
    private String status;
    
    // buffer attributes
    private ArrayList<Property> propertyList;
    
    public Property(int ID, int hostID, String listingName, String description, Double pricePerNight, 
                    String street, String city, String province, String country, String status) {
        this.ID = ID;
        this.hostID = hostID;
        this.listingName = listingName;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.status = status;
    }
    
    public Property() {
        
    }
    
    public int getProperties(String hostName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC", 
                "root", 
                "ethanaxl1"
            );
            
            PreparedStatement st = conn.prepareStatement("SELECT "
                    + "pl.propertyListingID, "
                    + "pl.hostID, "
                    + "pl.listingName, "
                    + "pl.description, "
                    + "pl.pricePerNight, "
                    + "pl.street, pl.city, "
                    + "pl.province, pl.country, "
                    + "pl.status "
                    + "FROM propertylisting pl "
                    + "JOIN host h ON h.hostID = pl.hostID "
                    + "WHERE h.userName = ?");
            
            st.setString(1, hostName);
            ResultSet rs = st.executeQuery();
            this.propertyList = new ArrayList<>();
            while (rs.next()) {
                this.ID = rs.getInt("propertyListingID");  // Assuming column name in DB is propertyListingID
                this.hostID = rs.getInt("hostID");
                this.listingName = rs.getString("listingName");
                this.description = rs.getString("description");
                this.pricePerNight = rs.getDouble("pricePerNight");  // Use getDouble for Double fields
                this.street = rs.getString("street");
                this.city = rs.getString("city");
                this.province = rs.getString("province");
                this.country = rs.getString("country");
                this.status = rs.getString("status");
                
                Property newProperty = new Property(this.ID, this.hostID, this.listingName, this.description, 
                        this.pricePerNight, this.street, this.city, this.province, this.country, this.status);
                this.propertyList.add(newProperty);
            }
            System.out.println("Success");
            st.close();
            conn.close();
            return 1;
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int filterProperties(String column, String operator, String value) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC", 
                "root", 
                "ethanaxl1"
            );
            
            Map<String, String> columnTypeMap = new HashMap<>();
            columnTypeMap.put("propertyListingID", "Integer");
            columnTypeMap.put("hostID", "Integer");
            columnTypeMap.put("pricePerNight", "Double");
            columnTypeMap.put("listingName", "String");
            columnTypeMap.put("description", "String");
            columnTypeMap.put("status", "String");
            columnTypeMap.put("street", "String");
            columnTypeMap.put("city", "String");
            columnTypeMap.put("province", "String");
            columnTypeMap.put("country", "String");

            List<String> validOps = Arrays.asList("=", "<", "<=", ">", ">=");

            if (!columnTypeMap.containsKey(column)) {
                throw new IllegalArgumentException("Invalid column name: " + column);
            }

            if (!validOps.contains(operator)) {
                throw new IllegalArgumentException("Invalid operator: " + operator);
            }

            String columnType = columnTypeMap.get(column);
            if ((operator.equals("<") || operator.equals("<=") || operator.equals(">") || operator.equals(">=")) && columnType.equals("String")) {
                throw new IllegalArgumentException("Operator " + operator + " is not valid for String column: " + column);
            }

            PreparedStatement st = conn.prepareStatement("SELECT * FROM propertylisting pl WHERE pl." + column + operator + "?");

            try {
                // Handle typecasting with exception handling
                if (column.equals("propertyListingID") || column.equals("hostID")) {
                    st.setInt(1, Integer.parseInt(value));
                } 
                
                else if (column.equals("pricePerNight")) {
                    st.setDouble(1, Double.parseDouble(value));
                } 
                
                else {
                    // Default case: Treat value as a string
                    st.setString(1, value);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for column " + column + ": " + value);
                return 0; // Or handle appropriately
            }

            ResultSet rs = st.executeQuery();
            this.propertyList = new ArrayList<>();
            while (rs.next()) {
                this.ID = rs.getInt("propertyListingID");  // Assuming column name in DB is propertyListingID
                this.hostID = rs.getInt("hostID");
                this.listingName = rs.getString("listingName");
                this.description = rs.getString("description");
                this.pricePerNight = rs.getDouble("pricePerNight");  // Use getDouble for Double fields
                this.street = rs.getString("street");
                this.city = rs.getString("city");
                this.province = rs.getString("province");
                this.country = rs.getString("country");
                this.status = rs.getString("status");
                
                Property newProperty = new Property(this.ID, this.hostID, this.listingName, this.description, 
                        this.pricePerNight, this.street, this.city, this.province, this.country, this.status);
                this.propertyList.add(newProperty);
            }
            System.out.println("Success");
            st.close();
            conn.close();
            return 1;
        }
        catch (ClassNotFoundException | SQLException  | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int getAllProperties() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC", 
                "root", 
                "ethanaxl1"
            );
            
            PreparedStatement st = conn.prepareStatement("SELECT * FROM propertylisting pl");
                    
            ResultSet rs = st.executeQuery();
            this.propertyList = new ArrayList<>();
            while (rs.next()) {
                this.ID = rs.getInt("propertyListingID");  // Assuming column name in DB is propertyListingID
                this.hostID = rs.getInt("hostID");
                this.listingName = rs.getString("listingName");
                this.description = rs.getString("description");
                this.pricePerNight = rs.getDouble("pricePerNight");  // Use getDouble for Double fields
                this.street = rs.getString("street");
                this.city = rs.getString("city");
                this.province = rs.getString("province");
                this.country = rs.getString("country");
                this.status = rs.getString("status");
                
                Property newProperty = new Property(this.ID, this.hostID, this.listingName, this.description, 
                        this.pricePerNight, this.street, this.city, this.province, this.country, this.status);
                this.propertyList.add(newProperty);
            }
            System.out.println("Success");
            st.close();
            conn.close();
            return 1;
        }
        catch (ClassNotFoundException | SQLException  | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int registerListing(
        int hostID,
        String listingName, 
        String description, 
        double pricePerNight, 
        String street, 
        String city, 
        String province, 
        String country, 
        String status
    ){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC", 
                "root", 
                "ethanaxl1"
            );
            
            PreparedStatement st = conn.prepareStatement("SELECT MAX(propertyListingID)+1 newID FROM propertyListing");
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                this.ID = rs.getInt("newID");
            }

            st = conn.prepareStatement(
                "INSERT INTO propertyListing (propertyListingID, hostID, listingName, "
                        + "description, pricePerNight, street, city, province, country, status) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            // Set the parameters
             st.setInt(1, this.ID); // propertyListingID
             st.setInt(2, hostID);
             st.setString(3, listingName);
             st.setString(4, description);
             st.setDouble(5, pricePerNight);
             st.setString(6, street);
             st.setString(7, city);
             st.setString(8, province);
             st.setString(9, country);
             st.setString(10, status);

            // Execute the statement
            st.executeUpdate(); 
            System.out.println("Success");
            st.close();
            conn.close();
            return 1;
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int deleteListing(int hostID, int propertyListingID) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC", 
            "root", 
            "ethanaxl1"
        );

        String checkQuery = "SELECT COUNT(*) AS pendingCount FROM reservation WHERE propertyListingID = ? AND startDate >= NOW()";
        PreparedStatement checkSt = conn.prepareStatement(checkQuery);
        checkSt.setInt(1, propertyListingID);
        ResultSet rs = checkSt.executeQuery();

        if (rs.next() && rs.getInt("pendingCount") > 0) {
            System.out.println("Property cannot be deleted. There are pending reservations.");
            return -2;
        }
        
        PreparedStatement deleteSt = conn.prepareStatement("DELETE FROM propertyListing WHERE hostID = ? AND propertyListingID = ?");
        deleteSt.setInt(1, hostID); 
        deleteSt.setInt(2, propertyListingID);

        int rowsAffected = deleteSt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Listing deleted successfully.");
            return 1;
        } else {
            System.out.println("No matching listing found for deletion.");
            return 0; 
        }
    } 
    catch (ClassNotFoundException | SQLException e) {
        System.out.println("Error: " + e.getMessage());
        return -1;
    }
}

    
    public int adaptListing(int propertyListingID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC", 
                "root", 
                "ethanaxl1"
            );
            
            PreparedStatement st = conn.prepareStatement("SELECT * "
                    + "FROM propertylisting "
                    + "WHERE propertyListingID = ?");

            st.setInt(1, propertyListingID);
            ResultSet rs = st.executeQuery();
        
            if (!rs.next()) {
                System.out.println("No listing found with ID: " + propertyListingID);
                return 0; 
            }

            this.propertyList = new ArrayList<>();
            
            this.ID = rs.getInt("propertyListingID");  
            this.hostID = rs.getInt("hostID");
            this.listingName = rs.getString("listingName");
            this.description = rs.getString("description");
            this.pricePerNight = rs.getDouble("pricePerNight");
            this.street = rs.getString("street");
            this.city = rs.getString("city");
            this.province = rs.getString("province");
            this.country = rs.getString("country");
            this.status = rs.getString("status");
            
            System.out.println("Success");
            st.close();
            conn.close();
            return 1;
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    
    public int updateListing(
        int propertyListingID,
        int hostID,
        String listingName, 
        String description, 
        double pricePerNight, 
        String street, 
        String city, 
        String province, 
        String country, 
        String status
    ) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC", 
                "root", 
                "ethanaxl1"
            );

            String sql = "UPDATE propertyListing SET "
                        + "hostID = ?, "
                        + "listingName = ?, "
                        + "description = ?, "
                        + "pricePerNight = ?, "
                        + "street = ?, "
                        + "city = ?, "
                        + "province = ?, "
                        + "country = ?, "
                        + "status = ? "
                        + "WHERE propertyListingID = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            // Set the parameters for the statement
            st.setInt(1, hostID); // Set hostID
            st.setString(2, listingName); // Set listingName
            st.setString(3, description); // Set description
            st.setDouble(4, pricePerNight); // Set pricePerNight
            st.setString(5, street); // Set street
            st.setString(6, city); // Set city
            st.setString(7, province); // Set province
            st.setString(8, country); // Set country
            st.setString(9, status); // Set status
            st.setInt(10, propertyListingID); // Set the propertyListingID to identify the record to update

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("No rows updated. The propertyListingID may not exist.");
            }
            
            st.close();
            conn.close();
            return 1;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    
    public static void main(String[] args) { 
        Property test = new Property();
          int status = test.adaptListing(20000001);
          System.out.println(status);
          System.out.println(test.getInfo());
        
    }
    public int getID() {
        return ID;
    }
    
    public int getHostID() {
        return hostID;
    }

    public String getListingName() {
        return listingName;
    }
    
    public String getDescription() {
        return description;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getStatus() {
        return status;
    }
    
    public ArrayList<Property> getPropertyList() {
        return this.propertyList;
    }
    
    public String getInfo() {
        String result = String.format(
        "Listing Details:<br>" +
                "Listing Name: %s<br>" +
                "Description: %s<br>" +
                "Host ID: %d<br>" +
                "Price Per Night: %.2f<br>" +
                "Street: %s<br>" +
                "City: %s<br>" +
                "Province: %s<br>" +
                "Country: %s<br>" +
                "Status: %s<br>" +
                "----------------------------------------",
        this.listingName, this.description, this.hostID, this.pricePerNight, this.street, this.city, this.province, this.country, this.status
        );
        
        return result;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setHostID(int hostID) {
        this.hostID = hostID;
    }

    public void setListingName(String listingName) {
        this.listingName = listingName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
     public static int getHostIDProperty(int propertyID) {
        int p = 0;
        
        String url = "jdbc:mysql://localhost:3306/propertyRental";
        String userDB = "root";
        String passwordDB = "ethanaxl1";
        
        Connection conn = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, userDB, passwordDB);
            
            PreparedStatement ps = conn.prepareStatement("SELECT hostID FROM propertyListing WHERE propertyListingID = ? ;");
            ps.setInt(1, propertyID);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                p = rs.getInt("hostID");
            }
            
            ps.close();
            conn.close();
                       
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
        return p;
     }
}