package alphabank.home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import alphabank.App;
import alphabank.login.LoginScreenController;
import alphabank.user.AccountData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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

    @FXML
    private ListView recentList;

    // Set account data variables
    private int id = LoginScreenController.id;
    private AccountData accountData = App.bankingSystem.bank.getAccountById(id).getData();
    private String accountType = accountData.getAccountType();

    // List of transaction messages
    public List<String> transactions = new ArrayList();

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

        // Add message and refresh list
        if (transactions.isEmpty()) {
            transactions.add("Starting balance: " + balance);
            refreshTransactions();
        }
    }

    /**
     * Initializes the controller class.
     */
    public void withdraw(ActionEvent event) {
        int amount = Integer.parseInt(areaInfo.getText());
        App.bankingSystem.withdraw(amount);

        int balance = getBalance(id);

        // Render balance to screen
        balanceInfo.setText("$" + Integer.toString(balance));
        String message = "Withdrawal - $" + amount + " -- " + printDate();

        // Add message and refresh list
        transactions.add(message);
        refreshTransactions();
    }

    /**
     * Deposits specified amount.
     */
    public void deposit(ActionEvent event) {
        int amount = Integer.parseInt(areaInfo.getText());
        App.bankingSystem.deposit(amount);

        int balance = getBalance(id);

        // Render balance to screen
        balanceInfo.setText("$" + Integer.toString(balance));

        String message = "Deposit - $" + amount + " -- " + printDate();

        // Add message and refresh list
        transactions.add(message);
        refreshTransactions();
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

    /**
     * Prints current date and time.
     */
    public String printDate() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return "Time: " + date.format(formatDate);
    }

    /**
     * Refreshes ListView of all transactions;
     */
    private void refreshTransactions() {
        recentList.getItems().clear();

        for (int i = 0; i < transactions.size(); i++) {
            recentList.getItems().add(transactions.get(i));
        }
    }

}
