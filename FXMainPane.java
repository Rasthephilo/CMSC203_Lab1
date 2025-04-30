package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FXMainPane extends VBox {

    // Task 2: Declare components
    Button helloBtn, howdyBtn, chineseBtn, clearBtn, exitBtn;
    Label feedbackLabel;
    TextField feedbackField;
    HBox topBox, bottomBox;

    // Task 4: DataManager
    DataManager manager;

    FXMainPane() {
        // Instantiate buttons
        helloBtn = new Button("Hello");
        howdyBtn = new Button("Howdy");
        chineseBtn = new Button("Chinese");
        clearBtn = new Button("Clear");
        exitBtn = new Button("Exit");

        // Instantiate label and text field
        feedbackLabel = new Label("Feedback:");
        feedbackField = new TextField();

        // Instantiate HBoxes
        topBox = new HBox();
        bottomBox = new HBox();

        // Set alignment and spacing
        topBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        HBox.setMargin(helloBtn, new Insets(10));
        HBox.setMargin(howdyBtn, new Insets(10));
        HBox.setMargin(chineseBtn, new Insets(10));
        HBox.setMargin(clearBtn, new Insets(10));
        HBox.setMargin(exitBtn, new Insets(10));
        HBox.setMargin(feedbackLabel, new Insets(10));
        HBox.setMargin(feedbackField, new Insets(10));

        // Add components to HBoxes
        topBox.getChildren().addAll(feedbackLabel, feedbackField);
        bottomBox.getChildren().addAll(helloBtn, howdyBtn, chineseBtn, clearBtn, exitBtn);

        // Add HBoxes to VBox
        this.getChildren().addAll(topBox, bottomBox);

        // Instantiate manager
        manager = new DataManager();

        // Set button actions
        ButtonHandler handler = new ButtonHandler();
        helloBtn.setOnAction(handler);
        howdyBtn.setOnAction(handler);
        chineseBtn.setOnAction(handler);
        clearBtn.setOnAction(handler);
        exitBtn.setOnAction(handler);
    }

    // Task 4: Handle button clicks
    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Object source = event.getSource();
            if (source == helloBtn) {
                feedbackField.setText(manager.getHello());
            } else if (source == howdyBtn) {
                feedbackField.setText(manager.getHowdy());
            } else if (source == chineseBtn) {
                feedbackField.setText(manager.getChinese());
            } else if (source == clearBtn) {
                feedbackField.setText("");
            } else if (source == exitBtn) {
                Platform.exit();
                System.exit(0);
            }
        }
    }
}
