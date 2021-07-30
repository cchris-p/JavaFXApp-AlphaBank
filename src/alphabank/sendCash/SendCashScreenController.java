package alphabank.sendCash;

import alphabank.App;
import alphabank.login.LoginScreenController;
import alphabank.user.AccountData;
import alphabank.user.Recipient;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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
    private int id = LoginScreenController.id;
    private AccountData accountData = App.bankingSystem.bank.getAccountById(id).getData();

    // Set account data variables
    private String accountType = accountData.getAccountType();
    // private ArrayList<Recipient> contactsList = App.bankingSystem.bank.getContactsList();
    // ObservableList observableList = FXCollections.observableArrayList();

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
        contactsListView.getItems().addAll("Item 1", "Item 2", "Item 3");
        contactsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * Sends cash to selected contact, subtracts it from current balance.
     *
     * @param event
     */
    public void sendCash(ActionEvent event) {
        try {
            int amount = Integer.parseInt(areaInfo.getText());
            App.bankingSystem.withdraw(amount);

            int balance = getBalance(id);

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
}
