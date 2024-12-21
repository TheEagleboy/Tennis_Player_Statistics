import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertTournament {
    public static String main(String[] args) throws Exception {
        boolean noWinner = false;
        if (args.length != 5) {
            if (args.length == 4) {
                noWinner = true;
            } else {
                throw new Exception();
            }
        }
        String connectionUrl = "jdbc:sqlserver://cxp-sql-02\\gtk19;"
                + "database=Tennis Player Statistics;"
                + "user=dbuser;"
                + "password=csds341143dscs;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=15;";

        String result = "Error";

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            System.out.println("Connected to SQL Server successfully!");

            // Prepare a callable statement for the InsertTournament procedure
            CallableStatement cstmt = connection.prepareCall("{CALL InsertTournament(?, ?, ?, ?, ?)}");

            // Set parameters for the stored procedure
            cstmt.setString(1, args[0]); // Tournament name
            cstmt.setString(2, args[1]); // Tournament location
            cstmt.setString(3, args[2]); // Tournament country
            cstmt.setString(4, args[3]); // Tournament year
            if (noWinner) {
                cstmt.setString(5, "");
            } else {
                cstmt.setString(5, args[4]); // Player ID of the winner

            }

            // Execute the stored procedure
            cstmt.execute();
            result = "Tournament '" + args[0] + ' ' + args[3] + "' successfully inserted into the database.";

        } catch (SQLException e) {
            return "Please Fill in all parameters";
        }
        return result;
    }
}
