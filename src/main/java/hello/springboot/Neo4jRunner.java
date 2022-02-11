package hello.springboot;

import org.neo4j.driver.Session;
import org.neo4j.driver.internal.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setEmail("spring@boot");
        account.setUsername("spring");

        Session session = sessionFactory.openSession();
        session.save(session);
        sessionFactory.close();
    }
}
