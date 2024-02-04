package tsundere.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tsundere.Tsundere;
import tsundere.ui.Ui;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;



/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image USER_IMAGE = new Image(Objects.requireNonNull(this.getClass().
            getResourceAsStream("/images/ok.png")));
    private final Image TSUN_IMAGE = new Image(Objects.requireNonNull(this.getClass().
            getResourceAsStream("/images/chitoge.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getTsundereDialog("Don't get the wrong idea!\n"
                        + "I'm not doing this to help you or anything!\n", TSUN_IMAGE)
        );
    }

    public void setTsundere(Tsundere t) {
    }

    private void exit() throws InterruptedException, IOException {
        Tsundere.storage.saveTasksToFile();
        TimeUnit.SECONDS.sleep(1);
        Platform.exit();
        System.exit(0);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {


        String input = userInput.getText();

        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, USER_IMAGE),
                    DialogBox.getTsundereDialog("Don't forget about me!\n"
                            + "You better come back soon!\n", TSUN_IMAGE)
            );
            try {
                this.exit();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            Ui ui = new Ui();
            String response = ui.chat(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, USER_IMAGE),
                    DialogBox.getTsundereDialog(response, TSUN_IMAGE)
            );
            userInput.clear();
        }
    }
}
