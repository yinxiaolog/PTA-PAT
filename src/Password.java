import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Password {
    private static class Account{
        String user;
        String passwd;

        public Account(String user, String passwd) {
            this.user = user;
            this.passwd = passwd;
        }

        @Override
        public String toString() {
            return user + " " + passwd;
        }
    }

    private static List<Account> modifyPasswd(List<Account> accounts) {
        List<Account> ans = new ArrayList<>();

        for (Account account : accounts) {
            String passwd = replace(account.passwd);
            if (!passwd.equals(account.passwd)) {
                account.passwd = passwd;
                ans.add(account);
            }
        }
        return ans;
    }

    private static String replace(String passwd) {
        passwd = passwd.replace('1', '@');
        passwd = passwd.replace('0', '%');
        passwd = passwd.replace('l', 'L');
        passwd = passwd.replace('O', 'o');

        return passwd;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String user = Read.next();
            String passwd = Read.next();
            Account account = new Account(user, passwd);
            accounts.add(account);
        }

        List<Account> ans = modifyPasswd(accounts);
        if (ans.size() == 0) {
            if (accounts.size() == 1){
                System.out.println("There is 1 account and no account is modified");
            } else {
                System.out.println("There are " + N + " accounts and no account is modified");
            }
            return;
        }

        System.out.println(ans.size());
        for (Account account : ans) {
            System.out.println(account);
        }
    }
}
