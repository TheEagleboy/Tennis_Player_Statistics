import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertMatch {
    public static void main(String[] args) throws Exception {
        // Connect to your database.
        // Replace server name, username, and password with your credentials
        String connectionUrl = "jdbc:sqlserver://cxp-sql-02\\gtk19;"
                + "database=Tennis Player Statistics;"
                + "user=dbuser;" // will probably make a new login
                + "password=csds341143dscs;" // "password=scsd341143dscs;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=15;";
        
        try (Connection conn = DriverManager.getConnection(connectionUrl)) {
            // Use CallableStatement to call the stored procedure
            String sql = "{CALL AddMatchWithStats(?, ?, ?, ?, ?, ?)}"; // change this to change the stored procedure
            try (CallableStatement stmt = conn.prepareCall(sql)) {
                // Set input parameters
                stmt.setString(1, args[0]);
                stmt.setString(2, args[1]);
                stmt.setString(3, args[2]);
                stmt.setString(4, args[3]);
                stmt.setString(5, args[4]);
                stmt.setString(6, args[5]);
                // Execute the stored procedure
                stmt.execute();
                // Retrieve the output parameter (newly generated ID)
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
