/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabank;

import alphabank.bank.Bank;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author pyles
 */
public class App extends Application {

    private BankingSystem bankingSystem = new BankingSystem(new Bank());
    private TextField field = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

//        Image icon = new Image("alpha.png");
//        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Alpha Bank");
//        primaryStage.setScene(new Scene(root));
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();

        primaryStage.setWidth(420);
        primaryStage.setHeight(420);
        primaryStage.setResizable(false);
        primaryStage.setX(50);
        primaryStage.setY(50);

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();
        Text balanceText = new Text();
        
        balanceText.setText("Balance");

        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            bankingSystem.login(id);

            areaInfo.setText(bankingSystem.toString());
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            bankingSystem.deposit(amount);

            areaInfo.setText(bankingSystem.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            bankingSystem.withdraw(amount);

            areaInfo.setText(bankingSystem.toString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            bankingSystem.exit();

            areaInfo.setText(bankingSystem.toString());
        });

        vbox.getChildren().addAll(balanceText, field, btnSubmit, btnDeposit, btnWithdraw, btnExit, areaInfo);

        return vbox;
    }
    
    private Child balanceWindow() {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
