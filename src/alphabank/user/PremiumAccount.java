package alphabank.user;

import java.util.ArrayList;

/**
 *
 * @author pyles
 */
public class PremiumAccount extends Account {

    private static final int OVERDRAFT_LIMIT = 100;

    public PremiumAccount(AccountData accountData, ArrayList<Recipient> contactsList) {
        super(accountData);
    }
    @Override
    protected boolean canWithdraw(int amount) {
        return getBalance() + OVERDRAFT_LIMIT >= amount;
    }

}
