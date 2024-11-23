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
import java.util.ArrayList;
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
    
    
    private ArrayList<String> infoList;
    
//    public Property(int ID, int hostID, String listingName, String description, Double pricePerNight, 
//                    String street, String city, String province, String country, String status) {
//        this.ID = ID;
//        this.hostID = hostID;
//        this.listingName = listingName;
//        this.description = description;
//        this.pricePerNight = pricePerNight;
//        this.street = street;
//        this.city = city;
//        this.province = province;
//        this.country = country;
//        this.status = status;
//    }
    
    public Property() {
        
    }
    
    public int getProperties(String hostName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC", "root", "Qryebfnevb_SQL2025");
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
            this.infoList = new ArrayList<>();
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
                
                String result = String.format(
                        "Listing Details:\n" +
                                "Listing Name: %s\n" +
                                "Description: %s\n" +
                                "Host ID: %d\n" +
                                "Price Per Night: %.2f\n" +
                                "Street: %s\n" +
                                "City: %s\n" +
                                "Province: %s\n" +
                                "Country: %s\n" +
                                "Status: %s\n" +
                                "----------------------------------------",
                        listingName, description, hostID, pricePerNight, street, city, province, country, status
                );
                this.infoList.add(result);
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
    
    public int registerListing() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC", "root", "Qryebfnevb_SQL2025");
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
//            st.setInt(1, this.ID); // propertyListingID
//            st.setInt(2, this.hostID);
//            st.setString(3, this.listingName);
//            st.setString(4, this.description);
//            st.setDouble(5, this.pricePerNight);
//            st.setString(6, this.street);
//            st.setString(7, this.city);
//            st.setString(8, this.province);
//            st.setString(9, this.country);
//            st.setString(10, this.status);
            // Use the setter functions directly in place of getters
            // Assign unique sample values to each parameter
            st.setInt(1, this.ID); // Unique propertyListingID
            st.setInt(2, 10000002); // Unique hostID
            st.setString(3, "Coastal Retreat"); // Unique listingName
            st.setString(4, "A cozy retreat with ocean views."); // Unique description
            st.setDouble(5, 250.00); // Unique pricePerNight
            st.setString(6, "456 Beachside Ave"); // Unique street
            st.setString(7, "Santa Monica"); // Unique city
            st.setString(8, "CA"); // Unique province/state
            st.setString(9, "USA"); // Unique country
            st.setString(10, "Booked"); // Unique status

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
    
    public static void main(String[] args) {
        Property test = new Property();
        int status = test.registerListing();//test.getProperties("hostUser2");
        test.setListingName("Baler");
        System.out.println(status);
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
    
    public ArrayList<String> getInfoList() {
        return this.infoList;
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

}