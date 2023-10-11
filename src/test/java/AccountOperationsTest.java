import com.ptitsyn.Account;
import com.ptitsyn.AccountOperations;
import org.junit.Assert;
import org.junit.Test;

public class AccountOperationsTest {
    @Test
    public void transfer(){
        Account account1 = new Account("id1",0);
        Account account2 = new Account("id2",400);
        Assert.assertFalse(AccountOperations.transfer(account1,account2,400));
    }
}
