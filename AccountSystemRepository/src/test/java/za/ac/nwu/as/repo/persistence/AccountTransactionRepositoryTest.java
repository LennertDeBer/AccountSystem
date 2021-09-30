package za.ac.nwu.as.repo.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.repo.config.RepositoryConfigTest;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryConfigTest.class})
public class AccountTransactionRepositoryTest {

    @Autowired
    AccountTransactionRepository accountTransactionRepository;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void getAccountTransactionByIdNativeQuery() {
        AccountTransaction transaction = accountTransactionRepository.getAccountTransactionByIdNativeQuery(new Long(1));
        assertNotNull(transaction);
        assertEquals(new Long(1),transaction.getTransactionId());
    }

    @Test
    public void getAccountTransactionByIdNativeQueryE() {
        AccountTransaction transaction = accountTransactionRepository.getAccountTransactionByIdNativeQuery(new Long(4));
        assertNull(transaction);
        //assertEquals("MILES",miles.getMnemonic());

    }


}