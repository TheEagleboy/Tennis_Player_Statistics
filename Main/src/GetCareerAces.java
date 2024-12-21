import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCareerAces {

    public static String main(String args[]) {
        // Database connection URL
        String connectionUrl = "jdbc:sqlserver://cxp-sql-02\\gtk19;"
                + "database=Tennis Player Statistics;"
                + "user=dbuser;"
                + "password=csds341143dscs;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=15;";

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            System.out.println("Connected to SQL Server successfully!");

            // Prepare a callable statement for GetCareerAces
            CallableStatement cstmt = connection.prepareCall("{CALL GetCareerAces(?, ?)}");
            cstmt.setString(1, args[0]); // Pass first name
            cstmt.setString(2, args[1]); // Pass last name

            // Execute and retrieve results
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                String fname = rs.getString("FirstName");
                String lname = rs.getString("LastName");
                int totalAces = rs.getInt("TotalAces");

                String Final = (fname + " " + lname + " has scored " + totalAces + " aces in their career.");
                return Final;
            }
        } catch (SQLException e) {
            System.out.println("oops");
        }
        return null;
    }
}