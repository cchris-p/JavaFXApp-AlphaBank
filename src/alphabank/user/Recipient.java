package alphabank.user;

/**
 * Recipient info / data.
 * 
 * @author pyles
 */
public class Recipient {
    public String name;
    public int accountNumber;
    public String dateAdded;

    Recipient(String name, int accountNumber, String dateAdded) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.dateAdded = dateAdded;
    }
}
