package za.ac.nwu.as.repo.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.domain.persistence.AccountMember;
import za.ac.nwu.as.repo.config.RepositoryConfigTest;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryConfigTest.class})
public class AccountMemberRepositoryTest {

    @Autowired
    AccountMemberRepository accountMemberRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAccountMemberByUsernameNativeQueryMike() {
        AccountMember member = accountMemberRepository.getAccountMemberByUsernameNativeQuery("MIKE");
        assertNotNull(member);
        assertEquals("MIKE",member.getMemberUsername());
    }
    @Test
    public void getAccountMemberByUsernameNativeQuery() {
        AccountMember member = accountMemberRepository.getAccountMemberByUsernameNativeQuery("MICKY");
        assertNull(member);

    }
}