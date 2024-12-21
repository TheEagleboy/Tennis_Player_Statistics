import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXApp extends Application {

    private VBox mainContent;

    @Override
    public void start(Stage primaryStage) {
        // Create the MenuBar
        MenuBar menuBar = new MenuBar();

        // Create Menus
        Menu fileMenu = new Menu("File");
        Menu queriesMenu = new Menu("Queries");
        Menu inputDataMenu = new Menu("Input Data");
        Menu helpMenu = new Menu("Help");

        // Create Menu Items for "File"
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());
        // Add items to the "File" menu
        fileMenu.getItems().addAll(exitItem);

        // Create Menu Items for "Queries"
        MenuItem timesWon = new MenuItem("Tournaments Won");
        MenuItem inputTournament = new MenuItem("Tournament");
        MenuItem tournamentWinner = new MenuItem("Tournament Winner");
        MenuItem overallMatchUp = new MenuItem("Overall Match Up");
        MenuItem careerAces = new MenuItem("Career Aces");

        // Action for "timesWon"
        timesWon.setOnAction(e -> timesWon());

        // Action for 'careerAces'
        careerAces.setOnAction(e -> getCareerAces());

        // Action for "Tournament"
        inputTournament.setOnAction(e -> openInputDialog("Tournament Input", "Enter tournament details:"));

        // Action for "Tournament Winner"
        tournamentWinner.setOnAction(e -> findTournamentWinner());

        // Action for "Overall Match Up"
        overallMatchUp.setOnAction(e -> findOverallMatchUp());

        // Add items to the "Queries" menu
        queriesMenu.getItems().addAll(timesWon, inputTournament, tournamentWinner, careerAces, overallMatchUp);

        // Create menu items for input data
        MenuItem insertMatch = new MenuItem("Insert Match");
        insertMatch.setOnAction(e -> insertMatch());

        MenuItem updateMatchStats = new MenuItem("Update MatchStats");
        updateMatchStats.setOnAction(e -> updateMatchStats());

        // Insert Tournament
        MenuItem insertTournment = new MenuItem("Insert Tournament");
        insertTournment.setOnAction(e -> insertTournament());

        // Add items to "Input Data" menu
        inputDataMenu.getItems().addAll(insertMatch, updateMatchStats, insertTournment);

        // Create Menu Items for "Help"
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> showAlert("About", "This is a simple JavaFX menu application."));

        // Add items to the "Help" menu
        helpMenu.getItems().addAll(aboutItem);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, queriesMenu, inputDataMenu, helpMenu);

        // Main layout with the menu bar
        mainContent = new VBox();
        VBox root = new VBox(menuBar, mainContent);

        // Scene setup
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Simple JavaFX Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void findOverallMatchUp() {
        // Clear mainContent before adding new input fields
        mainContent.getChildren().clear();

        // Add labels and text fields before adding new input fields
        Label player1fName = new Label("Player 1 First Name:");
        TextField player1fNameField = new TextField();

        Label player1lName = new Label("Player 1 Last Name:");
        TextField player1lNameField = new TextField();
        
        Label player2fName = new Label("Player 2 First Name:");
        TextField player2fNameField = new TextField();

        Label player2lName = new Label("Player 2 Last Name:");
        TextField player2lNameField = new TextField();
       
        Label outputText = new Label("Result:");
        TextField outputTextField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            outputTextField.clear();
            // Handle input submissions
            String player1fname = player1fNameField.getText();
            String player1lname = player1lNameField.getText();
            String player2fname = player2fNameField.getText();
            String player2lname = player2lNameField.getText();
            String[] args = {player1fname, player1lname, player2fname, player2lname};

            String outputData = FindOverallMatchUp.main(args);
            outputTextField.setText(outputData);

        });

        // Add input elements to mainContent
        mainContent.getChildren().addAll(player1fName, player1fNameField, player1lName, player1lNameField, player2fName,
         player2fNameField, player2lName, player2lNameField, submitButton, outputText, outputTextField);

    }

    private void findTournamentWinner() {
        // Clear mainContent before adding new input fields
        mainContent.getChildren().clear();

        // Add labels and text fields before adding new input fields
        Label tournamentName = new Label("Tournament Name:");
        TextField tournamentNameField = new TextField();

        Label year = new Label("Year:");
        TextField yearField = new TextField();

        Label outputText = new Label("Result:");
        TextField outputTextField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            outputTextField.clear();
            // Handle input submissions
            String tournamentname = tournamentNameField.getText();
            String Year = yearField.getText();
            String[] args = { tournamentname, Year };

            String outputData = FindTournamentWinner.main(args);
            outputTextField.setText(outputData);

        });

        // Add input elements to mainContent
        mainContent.getChildren().addAll(tournamentName, tournamentNameField, year, yearField,
                submitButton, outputText, outputTextField);

    }

    private void timesWon() {
        // Clear mainContent before adding new input fields
        mainContent.getChildren().clear();

        // Add labels and text fields for player input
        Label fName = new Label("Player First Name:");
        TextField fNameField = new TextField();

        Label lName = new Label("Player Last Name:");
        TextField lNameField = new TextField();

        Label tourName = new Label("Tournament Name:");
        TextField tourNameField = new TextField();

        Label outputText = new Label("Output Text:");
        TextField outputTextField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            outputTextField.clear();
            // Handle input submissions
            String fname = fNameField.getText();
            String lname = lNameField.getText();
            String tourname = tourNameField.getText();
            String[] args = { fname, lname, tourname };

            String outputData = TimesWon.main(args);
            outputTextField.setText(outputData);

        });

        // Add input elements to mainContent
        mainContent.getChildren().addAll(fName, fNameField, lName, lNameField, tourName, tourNameField,
                submitButton, outputText, outputTextField);
    }

    private void insertMatch() {
        // Clear mainContent before adding new input fields
        mainContent.getChildren().clear();

        // Add labels and text fields for player input
        Label fName1 = new Label("Player 1 First Name:");
        TextField fNameField1 = new TextField();

        Label lName1 = new Label("Player 1 Last Name:");
        TextField lNameField1 = new TextField();

        Label fName2 = new Label("Player 2 First Name:");
        TextField fNameField2 = new TextField();

        Label lName2 = new Label("Player 2 Last Name:");
        TextField lNameField2 = new TextField();

        Label tourName = new Label("Tournament Name:");
        TextField tourNameField = new TextField();

        Label year = new Label("Year:");
        TextField yearField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            // Handle input submissions
            String fname1 = fNameField1.getText();
            String lname1 = lNameField1.getText();
            String fname2 = fNameField2.getText();
            String lname2 = lNameField2.getText();
            String tourname = tourNameField.getText();
            String yearString = yearField.getText();
            String[] args = { fname1, lname1, fname2, lname2, tourname, yearString };

            try {
                InsertMatch.main(args);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });

        // Add input elements to mainContent
        mainContent.getChildren().addAll(fName1, fNameField1, lName1, lNameField1, fName2, fNameField2, lName2,
                lNameField2, tourName, tourNameField, year, yearField,
                submitButton);
    }

    private void updateMatchStats() {
        // Clear mainContent before adding new input fields
        mainContent.getChildren().clear();

        // Add labels and text fields for player input
        Label fName1 = new Label("Player 1 First Name:");
        TextField fNameField1 = new TextField();

        Label lName1 = new Label("Player 1 Last Name:");
        TextField lNameField1 = new TextField();

        Label fName2 = new Label("Player 2 First Name:");
        TextField fNameField2 = new TextField();

        Label lName2 = new Label("Player 2 Last Name:");
        TextField lNameField2 = new TextField();

        Label tourName = new Label("Tournament Name:");
        TextField tourNameField = new TextField();

        Label year = new Label("Year:");
        TextField yearField = new TextField();

        Label aces = new Label("Player 1 aces:");
        TextField acesField = new TextField();

        Label breakPoints = new Label("Player 1 break points:");
        TextField breakPointsField = new TextField();

        Label deuces = new Label("Player 1 deuces:");
        TextField deucesField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            // Handle input submissions
            String fname1 = fNameField1.getText();
            String lname1 = lNameField1.getText();
            String fname2 = fNameField2.getText();
            String lname2 = lNameField2.getText();
            String tourname = tourNameField.getText();
            String yearString = yearField.getText();
            String ace = acesField.getText();
            String breakPoint = breakPointsField.getText();
            String deuceString = deucesField.getText();

            String[] args = { fname1, lname1, fname2, lname2, tourname, yearString, ace, breakPoint, deuceString };

            try {
                UpdateMatchStats.main(args);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });

        // Add input elements to mainContent
        mainContent.getChildren().addAll(fName1, fNameField1, lName1, lNameField1, fName2, fNameField2, lName2,
                lNameField2, tourName, tourNameField, year, yearField, aces, acesField, breakPoints, breakPointsField,
                deuces, deucesField,
                submitButton);
    }

    private void insertTournament() {
        // Clear mainContent before adding new input fields
        mainContent.getChildren().clear();

        // Add labels and text fields for tournament input
        Label name = new Label("Tournament Name");
        TextField nameField = new TextField();

        Label location = new Label("Location");
        TextField locationField = new TextField();

        Label country = new Label("Country");
        TextField countryField = new TextField();

        Label year = new Label("Year");
        TextField yearField = new TextField();

        Label winner = new Label("Winner");
        TextField winnerField = new TextField();

        Label outputText = new Label("Output Text:");
        TextField outputTextField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            outputTextField.clear();

            // Handle input submissions
            String tName = nameField.getText();
            String tLocation = locationField.getText();
            String tCountry = countryField.getText();
            String tYear = yearField.getText();
            String tWinner = winnerField.getText();

            String[] args = { tName, tLocation, tCountry, tYear, tWinner };

            String outputData;
            try {
                outputData = InsertTournament.main(args);
                outputTextField.setText(outputData);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });

        // Add input elements to mainContent
        mainContent.getChildren().addAll(
                name, nameField,
                location, locationField,
                country, countryField,
                year, yearField,
                winner, winnerField,
                submitButton, outputText, outputTextField);
    }

    private void getCareerAces() {
        // Clear mainContent before adding new input fields
        mainContent.getChildren().clear();

        // Add labels and text fields for input
        Label fName = new Label("Player First Name:");
        TextField fNameField = new TextField();

        Label lName = new Label("Player Last Name:");
        TextField lNameField = new TextField();

        Label outputLabel = new Label("Output Text:");
        TextField outputTextField = new TextField();
        outputTextField.setEditable(false); // Make output field read-only

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            outputTextField.clear(); // Clear the output field before showing new result

            // Handle input submissions
            String fname = fNameField.getText().trim();
            String lname = lNameField.getText().trim();

            // Validate input
            if (fname.isEmpty() || lname.isEmpty()) {
                outputTextField.setText("Error: Both fields are required.");
                return;
            }

            // Call the GetCareerAces method
            try {
                String[] args = { fname, lname };
                String outputData = GetCareerAces.main(args);

                // Display result
                if (outputData != null) {
                    outputTextField.setText(outputData);
                } else {
                    outputTextField.setText("No data found for the given player.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                outputTextField.setText("An error occurred while retrieving data.");
            }
        });

        // Add input elements to mainContent
        mainContent.getChildren().addAll(
                fName, fNameField,
                lName, lNameField,
                submitButton,
                outputLabel, outputTextField);
    }

    private void openInputDialog(String title, String headerText) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.setContentText("Enter data:");

        // Show the dialog and capture input
        dialog.showAndWait().ifPresent(input -> {
            System.out.println("User entered: " + input);
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
