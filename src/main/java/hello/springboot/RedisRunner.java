package hello.springboot;

import hello.springboot.account.Account;
import hello.springboot.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RedisRunner implements ApplicationRunner {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> values = redisTemplate.opsForValue(); //value 관련된 operation을 제공.
        values.set("spring","timing");
        values.set("springboot","2.0");
        values.set("hello","world");

        Account account = new Account();
        account.setEmail("spring@boot.com");
        account.setUsername("spring");

        accountRepository.save(account);

        Optional<Account> byId = accountRepository.findById(account.getId());
        System.out.println(byId.get().getEmail());
        System.out.println(byId.get().getUsername());

    }
}
