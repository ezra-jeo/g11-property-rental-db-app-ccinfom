package propertyRental;

import java.sql.*;
import java.util.ArrayList;

public class Payments {

    public int reservation_id;
    public int transaction_id;
    public String date;
    public String mode;
    public float amount;

    public ArrayList<Integer> reservation_idlist = new ArrayList<>();
    public ArrayList<Integer> transaction_idlist = new ArrayList<>();
    public ArrayList<String> datelist = new ArrayList<>();
    public ArrayList<String> modelist = new ArrayList<>();
    public ArrayList<Float> amountlist = new ArrayList<>();

    public Payments() {}

    public int register_payment() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "ethanaxl1"
            );
            
            
            PreparedStatement pstst = conn.prepareStatement("SELECT max(transactionID) + 1 AS newID FROM transaction;");
            ResultSet rs = pstst.executeQuery();
            
            int id = 0;
            
            while (rs.next()) {
                id = rs.getInt("newID");
            }

            pstst = conn.prepareStatement("INSERT INTO transaction (transactionID, reservationID, amount, mode, date) VALUES (?,?,?,?,NOW())");
            pstst.setInt(1, id);
            pstst.setInt(2, reservation_id);
            pstst.setFloat(3, amount);
            pstst.setString(4, mode);
            pstst.executeUpdate();

            pstst.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        return 1;
    }
    
    public Payments getPaymentRecord(int reservationID) {
        Payments p = null;
        float vamount = 0;
        String vmode = "";
                
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                "root",
                "ethanaxl1"
            );
            
            
            PreparedStatement pstst = conn.prepareStatement("SELECT amount, mode FROM transaction WHERE reservationID = ?;");
            pstst.setInt(1, reservationID);
            ResultSet rs = pstst.executeQuery();
           
            
            while (rs.next()) {
                vamount = rs.getFloat("amount");
                vmode = rs.getString("mode");
            }
            
            p = new Payments();
            p.amount = vamount;
            p.mode = vmode;
           
            pstst.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return p;
    }

    public static void main(String[] args) {
    }
}