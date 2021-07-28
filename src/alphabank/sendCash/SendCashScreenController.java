package alphabank.sendCash;

import alphabank.App;
import alphabank.login.LoginScreenController;
import alphabank.user.AccountData;
import alphabank.user.Recipient;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author pyles
 */
public class SendCashScreenController {

    @FXML
    private TextArea areaInfo;

    @FXML
    private Text balanceInfo;

    @FXML
    private ListView contactsListView;

    @FXML
    private Text accountTypeInfo;

    // Set AccountData variables
    private int balance;
    private int id = LoginScreenController.id;
    private AccountData accountData = App.bankingSystem.bank.getAccountById(id).getData();
    private String accountType = accountData.getAccountType();
    private ArrayList<Recipient> contactsList = App.bankingSystem.bank.getContactsList();

    // Set variables for ListView functionality
    private Set<String> stringSet;
    ObservableList observableList = FXCollections.observableArrayList();

    String contacts;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        int balance = accountData.getBalance();
        balanceInfo.setText("$" + balance);
        accountTypeInfo.setText(accountType + " Account");

        contactsListView.getItems().addAll("Item 1", "Item 2", "Item 3");
        contactsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setListView();
        // listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        //     @Override
        //     public void changed(ObservableValue<? extends String> ov, String t, String t1) {
        //         // contacts = listView.getSelectionModel().getSelectedItem();
        // 
        //     }
        // });
    }

    /**
     * Sends cash to selected contact, subtracts it from current balance.
     */
    public void sendCash(ActionEvent event) {
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
     * Goes back to HomePageScreen.
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
        // Get updated balance
        AccountData accountData = App.bankingSystem.bank.getAccountById(id).getData();
        int balance = accountData.getBalance();

        return balance;
    }

    public void setListView() {
        stringSet.add("String 1");
        stringSet.add("String 2");
        stringSet.add("String 3");
        stringSet.add("String 4");
        observableList.setAll(stringSet);
        contactsListView.setItems(observableList);
        contactsListView.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListViewCell();
            }
        });
    }

}
