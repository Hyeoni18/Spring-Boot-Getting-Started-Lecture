package hello.springboot.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest //repository와 관련된 bean만 등록해서 test (슬라이싱 테스트) , 인메모리 db 가 필요함.
//@SpringBootTest //근데 테스트를 이렇게 실행하면 모든 bean 을 등록. 그러니까 application.properties 가 적용됨. 그러면 postgresql 가 적용됨.
//@SpringBootTest(properties = "spring.datasource.url=''") //아니면 프로퍼티를 오버라이딩 하던가.
public class AccountRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getURL());
            System.out.println(metaData.getDriverName());
            System.out.println(metaData.getUserName());
        }
    }

    @Test
    public void test() {
        Account account = new Account();
        account.setUsername("springg");
        account.setPassword("pass");

        Account newAcount = accountRepository.save(account);

        assertThat(newAcount).isNotNull();

        Optional<Account> existingAccount = accountRepository.findByUsername(newAcount.getUsername());
        assertThat(existingAccount).isNotNull();

        Optional<Account> nonExistingAccount = accountRepository.findByUsername("timing");
    //    assertThat(nonExistingAccount).isNull(); Account 일 때.
        assertThat(nonExistingAccount).isEmpty(); //Optional은 null이 아님. 일단 Optional이 나오니까.
    }
}