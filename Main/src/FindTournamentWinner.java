import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;        
import java.sql.CallableStatement;

public class FindTournamentWinner {
    public static String main (String[] args) {
        String connectionUrl = 
        "jdbc:sqlserver://cxp-sql-02\\gtk19;" 
        + "database=tennis player statistics;"
        + "user=dbuser;"
        + "password=csds341143dscs;"
        + "encrypt=true;"
        + "trustServerCertificate=true;"
        + "loginTimeout=15;" ;
       
        String tournamentName = args[0];
        String year = args[1];
        String callStoredProcedure = "{call dbo.FindTournamentWinner(?, ?)}";
        String returnString = "";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
        CallableStatement prepsStoredProcedure = connection.prepareCall(callStoredProcedure);)
        {
        connection.setAutoCommit(false);

        prepsStoredProcedure.setString(1, tournamentName);
        prepsStoredProcedure.setString(2, year);
        ResultSet resultSet = prepsStoredProcedure.executeQuery();
        connection.commit();
        if (resultSet.next()) {
         String tournamentWinner = resultSet.getString("full_name");
         returnString = String.format("%s won %s in %s", tournamentWinner, tournamentName, year);
        }
        }
        catch (SQLException e) {
        e.printStackTrace();
        }
        return returnString;
    }
}
