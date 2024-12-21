import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tennis {
    /*
     * Basic Structure for statements
     */
    public static void showPlayers() {
        // Database connection URL
        String connectionUrl = "jdbc:sqlserver://cxp-sql-02\\gtk19;"
                + "database=Tennis Player Statistics;"
                + "user=dbuser;"
                + "password=csds341143dscs;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=15;";

        // SQL query to retrieve fName from the Player table
        String query = "SELECT fName FROM Player";

        // Initialize JDBC objects
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(connectionUrl);

            // Create a statement
            statement = connection.createStatement();

            // Execute the query
            resultSet = statement.executeQuery(query);

            // Process the results
            System.out.println("First Names of Players:");
            while (resultSet.next()) {
                String fName = resultSet.getString("fName");
                System.out.println(fName);
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
     * Shows the number of aces of a player against another player.
     * 
     * Utilizes GetPlayerMatchAces
     */

    public static void showPlayerMatchupAces(String p1_fname, String p1_lname, String p2_fname, String p2_lname) {
        // ("Carlos", "Alcaraz", "Novak", "Djokovic")

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

            // Call stored procedure
            CallableStatement cstmt = connection.prepareCall("{CALL GetMatchupAces(?, ?, ?, ?)}");
            cstmt.setString(1, p1_fname);
            cstmt.setString(2, p1_lname);
            cstmt.setString(3, p2_fname);
            cstmt.setString(4, p2_lname);

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                String player1 = rs.getString("Player1");
                String player2 = rs.getString("Player2");
                int totalAces = rs.getInt("total_aces");
                System.out.println(player1 + " scored " + totalAces + " aces against " + player2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getPlayerCareerAces(String firstName, String lastName) {
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
            cstmt.setString(1, firstName); // Pass first name
            cstmt.setString(2, lastName); // Pass last name

            // Execute and retrieve results
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                String fname = rs.getString("FirstName");
                String lname = rs.getString("LastName");
                int totalAces = rs.getInt("TotalAces");

                System.out.println(fname + " " + lname + " has scored " + totalAces + " aces in their career.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getPlayerMatchAces(String firstName, String lastName, int matchId) {
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

            // Prepare a callable statement for GetMatchAces
            CallableStatement cstmt = connection.prepareCall("{CALL GetMatchAces(?, ?, ?)}");
            cstmt.setString(1, firstName); // Pass first name
            cstmt.setString(2, lastName); // Pass last name
            cstmt.setInt(3, matchId); // Pass match ID

            // Execute and retrieve results
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                String fname = rs.getString("FirstName");
                String lname = rs.getString("LastName");
                int totalAces = rs.getInt("TotalAces");

                System.out
                        .println(fname + " " + lname + " has scored " + totalAces + " aces in match " + matchId + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertTournament(String name, String location, String country, int year, int winnerPID) {
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

            // Prepare a callable statement for the InsertTournament procedure
            CallableStatement cstmt = connection.prepareCall("{CALL InsertTournament(?, ?, ?, ?, ?)}");

            // Set parameters for the stored procedure
            cstmt.setString(1, name); // Tournament name
            cstmt.setString(2, location); // Tournament location
            cstmt.setString(3, country); // Tournament country
            cstmt.setInt(4, year); // Tournament year
            cstmt.setInt(5, winnerPID); // Player ID of the winner

            // Execute the stored procedure
            cstmt.execute();
            System.out.println("Tournament '" + name + ' ' + year + "' successfully inserted into the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /// showPlayerMatchupAces("Carlos", "Alcaraz", "Novak", "Djokovic");
        // getPlayerCareerAces("Carlos", "Alcaraz");
        // getPlayerMatchAces("Carlos", "Alcaraz", 3); // Match ID is 101
        // insertTournament("Wimbledon", "London", "United Kingdom", 2016, 3);

    }
}
