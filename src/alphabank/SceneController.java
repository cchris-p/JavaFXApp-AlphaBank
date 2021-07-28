package alphabank;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author pyles
 */
public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public final String HOME_SCREEN_FXML = "./home/HomeScreen.fxml";
    public final String LOGIN_SCREEN_FXML = "./login/LoginScreen.fxml";
    public final String SEND_CASH_SCREEN_FXML = "./sendCash/SendCashScreen.fxml";

    public void renderHomeScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(HOME_SCREEN_FXML));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void renderLoginScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(LOGIN_SCREEN_FXML));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void renderSendCashScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(SEND_CASH_SCREEN_FXML));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
