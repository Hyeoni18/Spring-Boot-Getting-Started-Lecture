package hello.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class PgSQLRunner implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(PgSQLRunner.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try(Connection connection = dataSource.getConnection()) {

            logger.info(String.valueOf(dataSource.getClass())); //어떤 JDBC 를 사용하는지 알 수 있음.
            logger.info(connection.getMetaData().getURL());
            logger.info(connection.getMetaData().getUserName()); //SA

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE ACCOUNT (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        }

        jdbcTemplate.execute("INSERT INTO ACCOUNT VALUES (1, 'spring')"); //간결하게 sql 을 사용할 수 있음.
    }
}
