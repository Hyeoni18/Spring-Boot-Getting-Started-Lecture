package hello.springboot;

import hello.springboot.account.Account;
import hello.springboot.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = accountService.createAccount("spring","1234");
        System.out.println(account.getUsername());
        System.out.println(account.getPassword());
    }
}
