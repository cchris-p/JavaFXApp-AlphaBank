package alphabank.home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import alphabank.App;
import alphabank.login.LoginScreenController;
import alphabank.user.AccountData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author pyles
 */
public class HomeScreenController implements Initializable {

    @FXML
    private TextArea areaInfo;

    @FXML
    private Text balanceInfo;

    @FXML
    private Text accountTypeInfo;

    @FXML
    private Button sendCashButton;

    // Set account data variables
    private int balance;
    private int id = LoginScreenController.id;
    private AccountData accountData = App.bankingSystem.bank.getAccountById(id).getData();
    private String accountType = accountData.getAccountType();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int balance = accountData.getBalance();
        balanceInfo.setText("$" + balance);
        accountTypeInfo.setText(accountType + " Account");

        if (accountType == "Premium") {
            sendCashButton.setVisible(true);
        } 
    }

    /**
     * Initializes the controller class.
     */
    public void withdraw(ActionEvent event) {
        try {
            int amount = Integer.parseInt(areaInfo.getText());
            App.bankingSystem.withdraw(amount);

            balance = getBalance(id);

            // Render balance to screen
            balanceInfo.setText("$" + Integer.toString(balance));
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    /**
     * Deposits specified amount.
     */
    public void deposit(ActionEvent event) {
        try {
            int amount = Integer.parseInt(areaInfo.getText());
            App.bankingSystem.deposit(amount);

            balance = getBalance(id);

            // Render balance to screen
            balanceInfo.setText("$" + Integer.toString(balance));

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    /**
     * Withdraws specified amount.
     */
    public void sendCash(ActionEvent event) {
        try {
            App.sceneController.renderSendCashScreen(event);

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    /**
     * Logs out of session and changes scene.
     */
    public void logOut(ActionEvent event) {
        try {
            App.bankingSystem.exit();
            App.sceneController.renderLoginScreen(event);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Gets current balance.
     */
    private int getBalance(int id) {
        AccountData accountData = App.bankingSystem.bank.getAccountById(id).getData();
        int balance = accountData.getBalance();

        return balance;
    }

}
