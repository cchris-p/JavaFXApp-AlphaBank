package alphabank.user;

import alphabank.ActionResult;
import java.util.HashMap;
import java.util.Map;
import alphabank.App;
import java.util.ArrayList;

/**
 * Creates bank accounts with existing data.
 * 
 * @author pyles
 */
public class Bank {

    public Map<Integer, Account> accounts = new HashMap<>();
    


    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", 500, "Basic"
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", 200, "Premium"
        ), tempContacts));
    }

    static ArrayList<Recipient> tempContacts = new ArrayList<Recipient>();

    static {
        tempContacts.add(new Recipient("David", 1132, "11/22/2020"));
        tempContacts.add(new Recipient("Sarah", 3494, "04/19/2019"));
        tempContacts.add(new Recipient("Franklin", 7172, "12/30/2020"));
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            App.isLoggedIn = true;
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id);
        }
    }

    public ArrayList<Recipient> getContactsList() {
        return tempContacts;
    }

    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
}
