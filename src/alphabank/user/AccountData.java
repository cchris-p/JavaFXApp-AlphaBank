package alphabank.user;

/**
 *
 * @author pyles
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;
    private final int balance;
    private final String accountType;

    AccountData(int id, String name, String email, int balance, String accountType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "Account id: " + id + '\n'
                + "Name: " + name + '\n'
                + "Email: " + email + '\n'
                + "Balance: " + balance + '\n'
                + "AccountType: " + accountType;
    }
}
