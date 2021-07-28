package alphabank.login;

import alphabank.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
            System.out.println(e);
        }
    }

}
