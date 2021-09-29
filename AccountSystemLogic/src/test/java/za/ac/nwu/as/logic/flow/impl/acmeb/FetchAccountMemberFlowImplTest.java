package za.ac.nwu.as.logic.flow.impl.acmeb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.AccountMemberDto;
import za.ac.nwu.as.domain.persistence.AccountMember;
import za.ac.nwu.as.translator.AccountMemberTranslator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FetchAccountMemberFlowImplTest {


    @Mock
    private AccountMemberTranslator translator;
    @InjectMocks
    private FetchAccountMemberFlowImpl flow;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllAccountMembers() {
        String expectedResponse = "[AccountMemberDto{memberId=1,  memberUsername='MIKE', accountBalance=50.50}]";
        List<AccountMemberDto> accountMembers = new ArrayList<>();
        accountMembers.add(new AccountMemberDto(Long.valueOf(1),"MIKE",50.50));
        when(translator.getAllAccountMembers()).thenReturn(accountMembers);
        List<AccountMemberDto> result  = flow.getAllAccountMembers();
        assertNotNull(result);

        verify(translator, atLeastOnce()).getAllAccountMembers();
        String val =result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void getAccountMemberByUsername() {
        String expectedResponse = "AccountMemberDto{memberId=1,  memberUsername='MIKE', accountBalance=50.50}";
        AccountMemberDto member = new AccountMemberDto(Long.valueOf(1),"MIKE", 50.50);


        when(translator.getAccountMemberByUsernameNativeQuery("MIKE")).thenReturn(member);
        AccountMemberDto result  = flow.getAccountMemberByUsername("MIKE");

        assertNotNull(result);

        verify(translator,times(1)).getAccountMemberByUsernameNativeQuery("MIKE");
        String val = result.toString();
        assertEquals(expectedResponse,result.toString());
    }

    @Test
    public void getAccountMemberDbEntityByUsername() {
        String expectedResponse = "AccountMember{memberID=1, memberUsername='MIKE', accountBalance=50.50}";
        AccountMemberDto member = new AccountMemberDto(Long.valueOf(1),"MIKE", 50.50);


        when(translator.getAccountMemberByUsernameNativeQuery(anyString())).thenReturn(member);
        AccountMember result  = flow.getAccountMemberDbEntityByUsername("MIKE");

        assertNotNull(result);

        verify(translator,times(1)).getAccountMemberByUsernameNativeQuery(anyString());
        String val = result.toString();
        assertEquals(expectedResponse,result.toString());
    }
}