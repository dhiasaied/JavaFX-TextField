package application;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Modern TextField form: elegant card, entrance animations,
 * and an animated welcome message on Submit.
 */
public class TextFieldExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Background décor (soft circles)
        Circle orb1 = new Circle(160, Color.web("#c9a96e", 0.18));
        Circle orb2 = new Circle(110, Color.web("#1a2e2a", 0.08));
        StackPane.setAlignment(orb1, Pos.TOP_RIGHT);
        StackPane.setAlignment(orb2, Pos.BOTTOM_LEFT);
        StackPane.setMargin(orb1, new Insets(-40, -50, 0, 0));
        StackPane.setMargin(orb2, new Insets(0, 0, -30, -40));

        // Header
        Region accentLine = new Region();
        accentLine.getStyleClass().add("accent-line");

        Label brand = new Label("COMPANY WELCOME");
        brand.getStyleClass().add("brand-mark");

        Label title = new Label("Registration");
        title.getStyleClass().add("title-label");

        Label subtitle = new Label("Please fill in your information to continue.");
        subtitle.getStyleClass().add("subtitle-label");
        subtitle.setWrapText(true);

        // Fields
        Label firstNameLabel = new Label("FIRST NAME");
        firstNameLabel.getStyleClass().add("field-label");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("Your first name");
        firstNameField.getStyleClass().add("modern-field");

        Label lastNameLabel = new Label("LAST NAME");
        lastNameLabel.getStyleClass().add("field-label");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Your last name");
        lastNameField.getStyleClass().add("modern-field");

        VBox firstNameGroup = new VBox(6, firstNameLabel, firstNameField);
        VBox lastNameGroup = new VBox(6, lastNameLabel, lastNameField);

        Button submitButton = new Button("Submit");
        submitButton.getStyleClass().add("submit-button");
        submitButton.setMaxWidth(Double.MAX_VALUE);
        submitButton.setOnAction(e -> showWelcomeMessage(
                primaryStage,
                firstNameField.getText().trim(),
                lastNameField.getText().trim()
        ));

        VBox formCard = new VBox(18);
        formCard.getStyleClass().add("form-card");
        formCard.setAlignment(Pos.CENTER_LEFT);
        formCard.setMaxWidth(420);
        formCard.getChildren().addAll(
                accentLine, brand, title, subtitle,
                firstNameGroup, lastNameGroup, submitButton
        );
        VBox.setMargin(submitButton, new Insets(8, 0, 0, 0));
        VBox.setMargin(firstNameGroup, new Insets(6, 0, 0, 0));

        StackPane root = new StackPane(orb1, orb2, formCard);
        root.getStyleClass().add("root-pane");
        StackPane.setAlignment(formCard, Pos.CENTER);

        Scene scene = new Scene(root, 560, 620);
        scene.getStylesheets().add(
                getClass().getResource("/application/styles.css").toExternalForm()
        );

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX TextField Example");
        primaryStage.setMinWidth(480);
        primaryStage.setMinHeight(560);
        primaryStage.show();

        playEntranceAnimation(formCard, brand, title, subtitle, firstNameGroup, lastNameGroup, submitButton);
    }

    private void playEntranceAnimation(Node card, Node... staggered) {
        card.setOpacity(0);
        card.setTranslateY(28);

        FadeTransition cardFade = new FadeTransition(Duration.millis(520), card);
        cardFade.setFromValue(0);
        cardFade.setToValue(1);

        TranslateTransition cardSlide = new TranslateTransition(Duration.millis(520), card);
        cardSlide.setFromY(28);
        cardSlide.setToY(0);

        new ParallelTransition(cardFade, cardSlide).play();

        for (int i = 0; i < staggered.length; i++) {
            Node node = staggered[i];
            node.setOpacity(0);
            node.setTranslateY(14);

            FadeTransition fade = new FadeTransition(Duration.millis(380), node);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.setDelay(Duration.millis(180 + i * 70L));

            TranslateTransition slide = new TranslateTransition(Duration.millis(380), node);
            slide.setFromY(14);
            slide.setToY(0);
            slide.setDelay(Duration.millis(180 + i * 70L));

            new ParallelTransition(fade, slide).play();
        }
    }

    private void showWelcomeMessage(Stage owner, String firstName, String lastName) {
        Stage dialog = new Stage(StageStyle.TRANSPARENT);
        dialog.initOwner(owner);
        dialog.initModality(Modality.WINDOW_MODAL);

        Rectangle background = new Rectangle(460, 340);
        background.setArcWidth(24);
        background.setArcHeight(24);
        background.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#1a2e2a")),
                new Stop(0.55, Color.web("#243d38")),
                new Stop(1, Color.web("#1e3530"))
        ));

        DropShadow shadow = new DropShadow();
        shadow.setRadius(28);
        shadow.setOffsetY(10);
        shadow.setColor(Color.rgb(0, 0, 0, 0.45));

        Rectangle accentBar = new Rectangle(80, 3);
        accentBar.setArcWidth(3);
        accentBar.setArcHeight(3);
        accentBar.setFill(Color.web("#c9a96e"));

        Label greeting = new Label("WELCOME");
        greeting.setFont(Font.font("Georgia", FontWeight.NORMAL, 12));
        greeting.setTextFill(Color.web("#c9a96e"));

        Label nameLabel = new Label(buildDisplayName(firstName, lastName));
        nameLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 26));
        nameLabel.setTextFill(Color.web("#f5f0e8"));
        nameLabel.setWrapText(true);
        nameLabel.setTextAlignment(TextAlignment.CENTER);
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setMaxWidth(380);

        Label message = new Label(
                "Welcome! We are delighted to have you with us\n"
                        + "and look forward to being part of your\n"
                        + "experience with our company."
        );
        message.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 15));
        message.setTextFill(Color.web("#d4ddd9"));
        message.setWrapText(true);
        message.setTextAlignment(TextAlignment.CENTER);
        message.setAlignment(Pos.CENTER);
        message.setLineSpacing(6);
        message.setMaxWidth(380);

        String btnBase =
                "-fx-background-color: #c9a96e;"
                        + "-fx-background-radius: 20;"
                        + "-fx-padding: 10 28 10 28;"
                        + "-fx-cursor: hand;";
        String btnHover =
                "-fx-background-color: #d4b87e;"
                        + "-fx-background-radius: 20;"
                        + "-fx-padding: 10 28 10 28;"
                        + "-fx-cursor: hand;";

        Button closeButton = new Button("Continue");
        closeButton.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));
        closeButton.setTextFill(Color.web("#1a2e2a"));
        closeButton.setStyle(btnBase);
        closeButton.setOnMouseEntered(e -> closeButton.setStyle(btnHover));
        closeButton.setOnMouseExited(e -> closeButton.setStyle(btnBase));
        closeButton.setOnAction(e -> {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(280), dialog.getScene().getRoot());
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(ev -> dialog.close());
            fadeOut.play();
        });

        VBox content = new VBox(16);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(44, 36, 40, 36));
        content.getChildren().addAll(accentBar, greeting, nameLabel, message, closeButton);
        VBox.setMargin(closeButton, new Insets(14, 0, 0, 0));

        StackPane root = new StackPane(background, content);
        root.setEffect(shadow);
        root.setOpacity(0);
        root.setScaleX(0.92);
        root.setScaleY(0.92);

        Scene dialogScene = new Scene(root);
        dialogScene.setFill(Color.TRANSPARENT);
        dialog.setScene(dialogScene);
        dialog.show();

        dialog.setX(owner.getX() + (owner.getWidth() - root.prefWidth(-1)) / 2);
        dialog.setY(owner.getY() + (owner.getHeight() - root.prefHeight(-1)) / 2);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(420), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(420), root);
        scaleIn.setFromX(0.92);
        scaleIn.setFromY(0.92);
        scaleIn.setToX(1);
        scaleIn.setToY(1);

        new ParallelTransition(fadeIn, scaleIn).play();
    }

    private String buildDisplayName(String firstName, String lastName) {
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            return capitalize(firstName) + " " + capitalize(lastName);
        }
        if (!firstName.isEmpty()) {
            return capitalize(firstName);
        }
        if (!lastName.isEmpty()) {
            return capitalize(lastName);
        }
        return "Dear Guest";
    }

    private String capitalize(String value) {
        if (value.isEmpty()) {
            return value;
        }
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
