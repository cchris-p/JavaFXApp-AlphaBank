/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabank.home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import alphabank.App;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author pyles
 */
public class HomeScreenController implements Initializable {
    
    private TextField field = new TextField();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
        

    // might have to delete or refactor this
    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        Image icon = new Image(getClass().getResource("alpha.png").toString(), true);
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(130);
        imageView.setFitWidth(130);
        
        TextArea areaInfo = new TextArea();        

        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            App.bankingSystem.login(id);

            areaInfo.setText(App.bankingSystem.toString());
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            App.bankingSystem.deposit(amount);

            areaInfo.setText(App.bankingSystem.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            App.bankingSystem.withdraw(amount);

            areaInfo.setText(App.bankingSystem.toString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            App.bankingSystem.exit();

            areaInfo.setText(App.bankingSystem.toString());
        });

        vbox.getChildren().addAll(imageView, field, btnSubmit, btnDeposit, btnWithdraw, btnExit, areaInfo);

        return vbox;
    }

    
}
