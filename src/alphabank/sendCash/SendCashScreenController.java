package alphabank.sendCash;

import alphabank.App;
import alphabank.home.HomeScreenController;
import alphabank.login.LoginScreenController;
import alphabank.user.AccountData;
import alphabank.user.Recipient;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author pyles
 */
public class SendCashScreenController implements Initializable {

    @FXML
    private TextField areaInfo;

    @FXML
    private Text balanceInfo;

    @FXML
    private ListView contactsListView;

    @FXML
    private Text accountTypeInfo;

    // Get account data
    private HomeScreenController home = new HomeScreenController();
    private int id = LoginScreenController.id;
    private AccountData accountData = App.bankingSystem.bank.getAccountById(id).getData();

    // Set account data variables
    private String accountType = accountData.getAccountType();
    private ArrayList<Recipient> contactsList = App.bankingSystem.bank.getContactsList();
    private String selected;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int balance = accountData.getBalance();
        balanceInfo.setText("$" + balance);
        accountTypeInfo.setText(accountType + " Account");

        for (int i = 0; i < contactsList.size(); i++) {
            contactsListView.getItems().add(contactsList.get(i).name);
        }

        contactsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selected = (String) contactsListView.getSelectionModel().getSelectedItem();
            }
        });
    }

    /**
     * Sends cash to selected contact, subtracts it from current balance.
     *
     * @param event
     */
    public void sendCash(ActionEvent event) {
        try {
            int amount = Integer.parseInt(areaInfo.getText());
            int balance = getBalance(id);

            if (selected != null) {
                // Show alerts
                if (amount > balance) {
                    // Show fail alert
                    balance = getBalance(id);
                    failAlert();
                } else {
                    // Show success alert, update balance
                    App.bankingSystem.withdraw(amount);
                    balance = getBalance(id);
                    successAlert(selected, amount);
                }
            }
            else {
                invalidRecipientAlert();
            }

            // Render balance to screen
            balanceInfo.setText("$" + Integer.toString(balance));

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    /**
     * Goes back to HomePageScreen.
     *
     * @param event
     */
    public void goBack(ActionEvent event) {
        try {
            App.sceneController.renderHomeScreen(event);
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
     * Dialog box: Send cash action successful.
     */
    private void successAlert(String name, int amount) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Send Cash Successful");
        alert.setHeaderText(null);
        alert.setContentText("Successfully sent $" + amount + " to " + name + ".");
        alert.showAndWait();
    }

    /**
     * Dialog box: Send cash action failed.
     */
    private void failAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Send Cash Failed");
        alert.setHeaderText(null);
        alert.setContentText("Insufficient funds.");
        alert.showAndWait();
    }

    /**
     * Dialog box: Send cash action successful.
     */
    private void invalidRecipientAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Invalid Recipient");
        alert.setHeaderText(null);
        alert.setContentText("Please select a recipient to send cash to.");
        alert.showAndWait();
    }

}
