import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UpdateMatchStats {
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
            String sql = "{CALL UpdateMatchStats(?, ?, ?, ?, ?, ?, ?, ?, ?)}"; // change this to change the stored procedure
            try (CallableStatement stmt = conn.prepareCall(sql)) {
                // Set input parameters
                stmt.setString(1, args[0]);
                stmt.setString(2, args[1]);
                stmt.setString(3, args[2]);
                stmt.setString(4, args[3]);
                stmt.setString(5, args[4]);
                stmt.setString(6, args[5]);
                stmt.setString(7, args[6]);
                stmt.setString(8, args[7]);
                stmt.setString(9, args[8]);
                // Execute the stored procedure
                stmt.execute();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
