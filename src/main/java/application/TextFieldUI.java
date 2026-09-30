package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Basic example: form with empty TextFields (TextField() constructor).
 * Displays first name, last name, and a Submit button in a GridPane.
 */
public class TextFieldUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the GridPane container
        GridPane gridPane = new GridPane();

        // Create labels and text fields (empty content)
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameTextField = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameTextField = new TextField();

        // Create the button
        Button submitButton = new Button("Submit");

        // Add elements to the GridPane
        gridPane.addRow(0, firstNameLabel, firstNameTextField);
        gridPane.addRow(1, lastNameLabel, lastNameTextField);
        gridPane.addRow(2, submitButton);

        // Configure the scene
        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX TextField Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
