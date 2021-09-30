package za.ac.nwu.as.logic.flow.impl.actx;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountMember;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.domain.persistence.AccountType;
import za.ac.nwu.as.logic.flow.FetchAccountMemberFlow;
import za.ac.nwu.as.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.as.translator.AccountTransactionTranslator;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountTransactionFlowImplTest {

    @Mock
    private AccountTransactionTranslator translator;
    @Mock
    private FetchAccountTypeFlow fetchAccountTypeFlow;
    @Mock
    private FetchAccountMemberFlow fetchAccountMemberFlow;

    @InjectMocks
    private CreateAccountTransactionFlowImpl flow;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /*@Test
    public void create() {
    }*/

    @Test
    public void create() {
       /* String accountTransactionToBeCreated ="{\"transactionId\":1,\"accountTypeMnemonic\":\"MILES\",\"typeId\":1,\"accountMemberUsername\":\"MIKE\",\"memberId\":1,\"amount\":30.55,\"transactionDate\":[2021,1,1]}}";
        String expectedResponse = "AccountTransactionDto{transactionId=1, accountTypeMnemonic='MILES', typeId=1, accountMemberUsername='MIKE', memberId=1, amount=30.55, transactionDate=2021-01-01}";

        AccountType accountType = new AccountType(Long.valueOf(1),"MILES", "Miles",
                LocalDate.parse("2021-09-01"));
        AccountMember members = new AccountMember(Long.valueOf(1),"MIKE", 50.50);

        AccountTransactionDto accountTransactionRe = new AccountTransactionDto(Long.valueOf(1),"MILES",Long.valueOf(1),"MIKE",Long.valueOf(1),30.55, LocalDate.parse("2021-01-01"));


        Mockito.lenient().when(fetchAccountTypeFlow.getAccountTypeDbEntityByMnemonic(anyString())).thenReturn(accountType);


        Mockito.lenient().when(fetchAccountMemberFlow.getAccountMemberDbEntityByUsername(anyString())).thenReturn(members);


        AccountTransaction accountTransaction = accountTransactionRe.buildAccountTransaction(accountType, members);

        when(translator.save(any(AccountTransaction.class))).thenReturn(accountTransaction);
        AccountTransactionDto result  = new AccountTransactionDto(translator.save(new AccountTransaction()));
        assertNotNull(result);
        verify(translator, atLeastOnce()).save(any(AccountTransaction.class));

        //when(flow.create(any(AccountTransactionDto.class))).thenReturn(accountTransactionRe);
        assertNotNull(result);
       // verify(flow, atLeastOnce()).create(any(AccountTransactionDto.class));
        String val= result.toString();
        assertEquals(expectedResponse,
                result.toString());*/
        String accountTransactionToBeCreated ="{\"transactionId\":1,\"accountTypeMnemonic\":\"MILES\",\"typeId\":1,\"accountMemberUsername\":\"MIKE\",\"memberId\":1,\"amount\":30.55,\"transactionDate\":[2021,1,1]}}";
        String expectedResponse = "AccountTransactionDto{transactionId=1, accountTypeMnemonic='MILES', typeId=1, accountMemberUsername='MIKE', memberId=1, amount=30.55, transactionDate=2021-01-01}";


        AccountType accountType = new AccountType(Long.valueOf(1),"MILES", "Miles", LocalDate.parse("2021-09-01"));
        AccountMember members = new AccountMember(Long.valueOf(1),"MIKE");


        AccountTransactionDto accountTransactionRe = new AccountTransactionDto(Long.valueOf(1),"MILES",Long.valueOf(1),"MIKE",Long.valueOf(1),30.55, LocalDate.parse("2021-01-01"));

        AccountTransaction accountTransaction = accountTransactionRe.buildAccountTransaction(accountType, members);


        when(translator.save(any(AccountTransaction.class))).thenReturn(accountTransaction);
        AccountTransactionDto result  = flow.create(accountTransactionRe);
        assertNotNull(result);
        verify(translator, atLeastOnce()).save(any(AccountTransaction.class));
        String val= result.toString();
        assertEquals(expectedResponse,
                result.toString());



    }
}