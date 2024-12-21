import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimesWon {
    public static String main(String[] args) {
        // Database connection variables
        String connectionUrl =
        "jdbc:sqlserver://cxp-sql-02\\gtk19;"
        + "database=tennis player statistics;"
        + "user=dbuser;"
        + "password=csds341143dscs;" //D1pIkyHTgozlW5;" //"password=scsd341143dscs;" 
        + "encrypt=true;"
        + "trustServerCertificate=true;"
        + "loginTimeout=15;";

        // The name to filter players by
        String fName = args[0]; // Replace with the desired name
        String lName = args[1]; // Replace with the desired name
        String tournamentName = args[2];
        // SQL query with a placeholder for the prepared statement
        String sqlQuery = "SELECT COUNT(fname) as count FROM Player AS p JOIN tournament as t on t.winner = p.PID WHERE fName = ? AND lname = ? AND t.name = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            // Set the value for the placeholder
            preparedStatement.setString(1, fName);
            preparedStatement.setString(2, lName);
            preparedStatement.setString(3, tournamentName);
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set
            //System.out.println("Players with the name '" + fName + "':");
            while (resultSet.next()) {
                int numWins = resultSet.getInt("count");
                String returnString = String.format("%s %s won %s %d times", fName, lName, tournamentName, numWins);

                return returnString;
                //System.out.printf("%s won %s %d times", fName, tournamentName, numWins);
                /*
                int pid = resultSet.getInt("PID");
                String fName = resultSet.getString("fName");
                String lName = resultSet.getString("lName");
                int yearsPlaying =int resultSet.getInt("yearsPlaying");
                String status = resultSet.getString("status");
                Date dob = resultSet.getDate("dob");

                System.out.printf("PID: %d, Name: %s %s, Years Playing: %d, Status: %s, DOB: %s%n",
                        pid, fName, lName, yearsPlaying, status, dob);
                 */
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return e.toString();
        }

        return "Failure to output data";
    }
}
