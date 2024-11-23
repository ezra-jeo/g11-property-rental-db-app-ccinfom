import java.sql.*;
import java.util.ArrayList;

public class Payments {

    public int reservation_id;
    public int transaction_id;
    public String date;
    public String mode;
    public String type;
    public float amount;

    public ArrayList<Integer> reservation_idlist = new ArrayList<>();
    public ArrayList<Integer> transaction_idlist = new ArrayList<>();
    public ArrayList<String> datelist = new ArrayList<>();
    public ArrayList<String> modelist = new ArrayList<>();
    public ArrayList<String> typelist = new ArrayList<>();
    public ArrayList<Float> amountlist = new ArrayList<>();

    public Payments() {}

    public int register_payment() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/propertyrental?useSSL=false&serverTimezone=UTC",
                    "root",
                    "Qryebfnevb_SQL2025"
            );

            PreparedStatement pstst = conn.prepareStatement("INSERT INTO transaction (reservationID, amount, mode, date, type) VALUES (?,?,?,NOW(),?)");
            ResultSet rst = pstst.executeQuery();
            while (rst.next()) {
                reservation_id = rst.getInt("new_id");
            }


            pstst.setInt(1, reservation_id);
            pstst.setFloat(2, amount);
            pstst.setString(3, mode);
            pstst.setString(4, type);
            pstst.executeUpdate();

            pstst.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        return 1;

    }

    public static void main(String[] args) {
    }



}