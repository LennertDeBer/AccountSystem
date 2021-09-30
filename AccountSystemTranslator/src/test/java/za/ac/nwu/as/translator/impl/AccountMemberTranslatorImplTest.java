package za.ac.nwu.as.translator.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.AccountMemberDto;
import za.ac.nwu.as.domain.persistence.AccountMember;
import za.ac.nwu.as.repo.persistence.AccountMemberRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountMemberTranslatorImplTest {

    @Mock
    private AccountMemberRepository repo;
    @InjectMocks
    private AccountMemberTranslatorImpl translator;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {
            String accountTypeToBeCreated = "AccountMemberDto{memberId=1,  memberUsername='MIKE'}";
            String expectedResponse = "{\"successful\":true,\"payload\":" +
                    "{\"memberId\":1,\"memberUsername\":\"MIKE\"}}";


            AccountMember members = new AccountMember(Long.valueOf(1), "MIKE");


            when(repo.save(any(AccountMember.class))).thenReturn(members);
            AccountMemberDto result = translator.create(new AccountMemberDto());
            assertNotNull(result);
            verify(repo, atLeastOnce()).save(any(AccountMember.class));
            String val = result.toString();
            assertEquals(accountTypeToBeCreated,
                    result.toString());

            //when(repo.save(any(AccountMember.class))).thenThrow(RuntimeException.class);

    }

    @Test(expected = RuntimeException.class)
    public void createError() {
        String accountTypeToBeCreated = "AccountMemberDto{memberId=1,  memberUsername='MIKE'}";
        String expectedResponse = "{\"successful\":true,\"payload\":" +
                "{\"memberId\":1,\"memberUsername\":\"MIKE\"}}";


        AccountMember members = new AccountMember(Long.valueOf(1), "MIKE");


       // when(repo.save(any(AccountMember.class))).thenReturn(members);
        AccountMemberDto result = translator.create(null);
        assertNotNull(result);
       // verify(repo, atLeastOnce()).save(any(AccountMember.class));
        String val = result.toString();
        assertEquals(accountTypeToBeCreated,
                result.toString());

        //when(repo.save(any(AccountMember.class))).thenThrow(RuntimeException.class);

    }

    @Test
    public void getAllAccountMembers() {
        String expectedResponse = "[AccountMemberDto{memberId=1,  memberUsername='MIKE'}]";
        List<AccountMember> accountMembers = new ArrayList<>();
        accountMembers.add(new AccountMember(Long.valueOf(1),"MIKE"));
        when(repo.findAll()).thenReturn(accountMembers);
        List<AccountMemberDto> result  = translator.getAllAccountMembers();
        assertNotNull(result);

        verify(repo, atLeastOnce()).findAll();
        String val =result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }




    @Test
    public void getAccountMemberByUsernameNativeQuery() {
        String expectedResponse = "AccountMemberDto{memberId=1,  memberUsername='MIKE'}";
        AccountMember member = new AccountMember(Long.valueOf(1),"MIKE");


        when(repo.getAccountMemberByUsernameNativeQuery("MIKE")).thenReturn(member);
        AccountMemberDto result  = translator.getAccountMemberByUsernameNativeQuery("MIKE");

        assertNotNull(result);

        verify(repo,times(1)).getAccountMemberByUsernameNativeQuery("MIKE");
        String val = result.toString();
        assertEquals(expectedResponse,result.toString());
    }


}