import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class App {
    public static void main(String[] args) throws Exception {
        // Connect to your database.
        // Replace server name, username, and password with your credentials
        String connectionUrl = "jdbc:sqlserver://cxp-sql-02\\gtk19;"
                + "database=Tennis Player Statistics;"
                + "user=dbuser;" // will probably make a new login
                + "password= csds341143sdsc;" // "password=scsd341143dscs;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=15;";
        ResultSet resultSet = null;
        try (Connection conn = DriverManager.getConnection(connectionUrl)) {
            // Use CallableStatement to call the stored procedure
            String sql = "{CALL insertStudent2(?, ?, ?, ?)}"; // change this to change the stored procedure
            try (CallableStatement stmt = conn.prepareCall(sql)) {
                // Set input parameters
                stmt.setString(1, "Bob");
                stmt.setString(2, "Biology");
                stmt.setInt(3, 30);
                // Register output parameter (for the auto-generated ID)
                stmt.registerOutParameter(4, Types.INTEGER);
                // Execute the stored procedure
                stmt.execute();
                // Retrieve the output parameter (newly generated ID)
                int newId = stmt.getInt(4);
                System.out.println("Newly inserted student ID: " + newId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
