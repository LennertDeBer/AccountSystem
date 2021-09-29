package za.ac.nwu.as.logic.flow.impl.acmeb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.AccountMemberDto;
import za.ac.nwu.as.translator.AccountMemberTranslator;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ModifyAccountMemberFlowImplTest {

    @Mock
    private AccountMemberTranslator translator;

    @InjectMocks
    private ModifyAccountMemberFlowImpl flow;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

  /*  @Test
    public void increaseAccountMemberBalance() {
    }*/


    @Test
    public void increaseAccountMemberBalance() throws Exception {
        String expectedResponse = "AccountMemberDto{memberId=1,  memberUsername='MIKE', accountBalance=61.00}";

        AccountMemberDto member = new AccountMemberDto(Long.valueOf(1),"MIKE", 61.0);

        when(translator.increaseAccountMemberBalance(anyString(),
                anyDouble(), any(LocalDate.class))).thenReturn(member);
        AccountMemberDto result  = flow.increaseAccountMemberBalance(member.getMemberUsername(),member.getAccountBalance(),LocalDate.parse("2021-09-01"));
        assertNotNull(result);


        verify(translator,
                times(1)).increaseAccountMemberBalance(
                anyString(),anyDouble(), any(LocalDate.class));
        String val = result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void increaseAccountMemberBalanceWithNoOptionalDate() throws Exception {
        String expectedResponse = "AccountMemberDto{memberId=1,  memberUsername='MIKE', accountBalance=61.00}";

        AccountMemberDto member = new AccountMemberDto(Long.valueOf(1),"MIKE", 61.0);

        when(translator.increaseAccountMemberBalance(anyString(),
                anyDouble(), isNull())).thenReturn(member);


        AccountMemberDto result = translator.increaseAccountMemberBalance(member.getMemberUsername(), member.getAccountBalance(), null);;
        /*if(null==accountType.getCreationDate()) {
             result = flow.updateAccountType(accountType.getMnemonic(), accountType.getAccountTypeName(), LocalDate.now());
        }*/
        assertNotNull(result);


        verify(translator,
                atLeastOnce()).increaseAccountMemberBalance(anyString(),
                anyDouble(), isNull());
        String val = result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void increaseAccountMemberBalanceObitMandatory() throws Exception {

        verify(translator, never()).increaseAccountMemberBalance(anyString(), anyDouble(), any(LocalDate.class));
        verify(translator, never()).increaseAccountMemberBalance(anyString(), anyDouble(), isNull());
    }








    @Test
    public void decreaseAccountMemberBalance() throws Exception {
        String expectedResponse = "AccountMemberDto{memberId=1,  memberUsername='MIKE', accountBalance=61.00}";

        AccountMemberDto member = new AccountMemberDto(Long.valueOf(1),"MIKE", 61.0);

        when(translator.decreaseAccountMemberBalance(anyString(),
                anyDouble(), any(LocalDate.class))).thenReturn(member);
        AccountMemberDto result  = flow.decreaseAccountMemberBalance(member.getMemberUsername(),member.getAccountBalance(),LocalDate.parse("2021-09-01"));
        assertNotNull(result);


        verify(translator,
                times(1)).decreaseAccountMemberBalance(
                anyString(),anyDouble(), any(LocalDate.class));
        String val = result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void decreaseAccountMemberBalanceWithNoOptionalDate() throws Exception {
        String expectedResponse = "AccountMemberDto{memberId=1,  memberUsername='MIKE', accountBalance=61.00}";

        AccountMemberDto member = new AccountMemberDto(Long.valueOf(1),"MIKE", 61.0);

        when(translator.decreaseAccountMemberBalance(anyString(),
                anyDouble(), isNull())).thenReturn(member);


        AccountMemberDto result = translator.decreaseAccountMemberBalance(member.getMemberUsername(), member.getAccountBalance(), null);;
        /*if(null==accountType.getCreationDate()) {
             result = flow.updateAccountType(accountType.getMnemonic(), accountType.getAccountTypeName(), LocalDate.now());
        }*/
        assertNotNull(result);


        verify(translator,
                atLeastOnce()).decreaseAccountMemberBalance(anyString(),
                anyDouble(), isNull());
        String val = result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void decreaseAccountMemberBalanceObitMandatory() throws Exception {

        verify(translator, never()).decreaseAccountMemberBalance(anyString(), anyDouble(), any(LocalDate.class));
        verify(translator, never()).decreaseAccountMemberBalance(anyString(), anyDouble(), isNull());
    }

}