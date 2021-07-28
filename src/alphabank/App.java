package alphabank;
import alphabank.SceneController;
import alphabank.user.Bank;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;



/**
 *
 * @author pyles
 */
public class App extends Application {
    
    public static SceneController sceneController = new SceneController();

    public static boolean isLoggedIn = false;
    
    public static BankingSystem bankingSystem = new BankingSystem(new Bank());
   
    public final String 
        LOGIN_SCREEN_FXML = "./login/LoginScreen.fxml";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Alpha Bank");
        primaryStage.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource(LOGIN_SCREEN_FXML));
        Scene loginScreen = new Scene(root);
        primaryStage.setScene(loginScreen);
        primaryStage.show();
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
