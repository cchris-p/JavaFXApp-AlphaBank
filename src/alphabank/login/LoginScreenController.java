package alphabank.login;

import alphabank.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pyles
 */
public class LoginScreenController {

    @FXML
    private TextField myTextField;

    public static int id;

    /**
     * Submits LoginID and changes scene to HomeScreen.
     */
    public void submit(ActionEvent event) {
        try {
            id = Integer.parseInt(myTextField.getText());
            App.bankingSystem.login(id);
            App.sceneController.renderHomeScreen(event);
        } catch (Exception e) {
            invalidIdAlert(id);
            System.out.println(e);
        }
    }

    /**
     * Dialog box: Send cash action successful.
     */
    private void invalidIdAlert(int id) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid ID Alert");
        alert.setHeaderText(null);
        alert.setContentText("No existing ID: " + id + "\n\n" + "Hint: Enter Test IDs: 1000 or 2000.");
        alert.showAndWait();
    }

}
