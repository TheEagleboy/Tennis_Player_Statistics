import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;        
import java.sql.CallableStatement;

public class FindOverallMatchUp {
    public static String main (String[] args) {
        String connectionUrl = 
        "jdbc:sqlserver://cxp-sql-02\\gtk19;" 
        + "database=tennis player statistics;"
        + "user=dbuser;"
        + "password=csds341143dscs;"
        + "encrypt=true;"
        + "trustServerCertificate=true;"
        + "loginTimeout=15;" ;
       
        String player1fName = args[0];
        String player1lName = args[1];
        String player2fName = args[2];
        String player2lName = args[3];
        String callStoredProcedure = "{call dbo.FindMatchUps(?, ?, ?, ?)}";
        String returnString = "";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
        CallableStatement prepsStoredProcedure = connection.prepareCall(callStoredProcedure);)
        {
        connection.setAutoCommit(false);

        prepsStoredProcedure.setString(1, player1fName);
        prepsStoredProcedure.setString(2, player1lName);
        prepsStoredProcedure.setString(3, player2fName);
        prepsStoredProcedure.setString(4, player2lName);
        ResultSet resultSet = prepsStoredProcedure.executeQuery();
        connection.commit();
        while (resultSet.next()) {
            String tournamentWinner = resultSet.getString("winner_full_name");
            String tournamentLoser = resultSet.getString("runner_up_full_name");
            String matchID = resultSet.getString("MID");
            String score = resultSet.getString("score");
            returnString = String.format("%s beat %s in match %s with a score of %s", tournamentWinner, tournamentLoser, matchID, score);
            System.out.println(returnString);
            return returnString;
        }
        }
        catch (SQLException e) {
        e.printStackTrace();
        }
        return " ";
    }
}


