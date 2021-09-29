package za.ac.nwu.as.translator.impl;

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
import za.ac.nwu.as.repo.persistence.AccountTransactionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTransactionTranslatorImplTest {

    @Mock
    private AccountTransactionRepository repo;
    @InjectMocks
    private AccountTransactionTranslatorImpl translator;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void getAllAccountTransaction() {
        String expectedResponse = "[AccountTransaction{transactionId=1, accountType=AccountType{accountTypeID=1, mnemonic='MILES', accountTypeName='Miles', creationDate=2021-09-01}, memberId=AccountMember{memberID=1, memberUsername='MIKE', accountBalance=50.50}, amount=30.55, transactionDate=2021-01-01}]";

        AccountType accountType = new AccountType(Long.valueOf(1),"MILES", "Miles", LocalDate.parse("2021-09-01"));
        AccountMember members = new AccountMember(Long.valueOf(1),"MIKE", 50.50);


        List<AccountTransaction> accountTransactions = new ArrayList<>();
        accountTransactions.add(new AccountTransaction(Long.valueOf(1),accountType,members,30.55, LocalDate.parse("2021-01-01")));


        when(repo.findAll()).thenReturn(accountTransactions);
        List<AccountTransaction> result  = translator.getAllAccountTransaction();
        assertNotNull(result);
        verify(repo,times(1)).findAll();
         // String val = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponse,result.toString());
    }

    @Test
    public void save() {
        String accountTransactionToBeCreated ="{\"transactionId\":1,\"accountTypeMnemonic\":\"MILES\",\"typeId\":1,\"accountMemberUsername\":\"MIKE\",\"memberId\":1,\"amount\":30.55,\"transactionDate\":[2021,1,1]}}";
        String expectedResponse = "AccountTransaction{transactionId=1, accountType=AccountType{accountTypeID=1, mnemonic='MILES', accountTypeName='Miles', creationDate=2021-09-01}, memberId=AccountMember{memberID=1, memberUsername='MIKE', accountBalance=50.50}, amount=30.55, transactionDate=2021-01-01}";


        AccountType accountType = new AccountType(Long.valueOf(1),"MILES", "Miles", LocalDate.parse("2021-09-01"));
        AccountMember members = new AccountMember(Long.valueOf(1),"MIKE", 50.50);


        AccountTransactionDto accountTransactionRe = new AccountTransactionDto(Long.valueOf(1),accountType,members,30.55, LocalDate.parse("2021-01-01"));
        AccountTransaction accountTransaction = accountTransactionRe.buildAccountTransaction(accountType, members);


        when(repo.save(any(AccountTransaction.class))).thenReturn(accountTransaction);
        AccountTransaction result  = translator.save(new AccountTransaction());
        assertNotNull(result);
        verify(repo, atLeastOnce()).save(any(AccountTransaction.class));
        String val= result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }


 
    @Test
    public void getAccountTransactionByIdNativeQuery() {
        String expectedResponse = "AccountTransactionDto{transactionId=1, accountTypeMnemonic='MILES', typeId=1, accountMemberUsername='MIKE', memberId=1, amount=30.55, transactionDate=2021-01-01}";



        AccountType accountType = new AccountType(Long.valueOf(1),"MILES", "Miles", LocalDate.parse("2021-09-01"));
        AccountMember members = new AccountMember(Long.valueOf(1),"MIKE", 50.50);

        AccountTransaction accountTransaction = new AccountTransaction(Long.valueOf(1),accountType,members,30.55, LocalDate.parse("2021-01-01"));


        when(repo.getAccountTransactionByIdNativeQuery(anyLong())).thenReturn(accountTransaction);

        AccountTransactionDto result  = translator.getAccountTransactionByIdNativeQuery(1.0);
        assertNotNull(result);


        verify(repo,times(1)).getAccountTransactionByIdNativeQuery(anyLong());
        String val = result.toString();
        assertEquals(expectedResponse,val);
    }
    @Test(expected = RuntimeException.class)
    public void getAccountTransactionByIdNativeQueryError() {
        String expectedResponse = "AccountTransactionDto{transactionId=1, accountTypeMnemonic='MILES', typeId=1, accountMemberUsername='MIKE', memberId=1, amount=30.55, transactionDate=2021-01-01}";



        AccountType accountType = new AccountType(Long.valueOf(1),"MILES", "Miles", LocalDate.parse("2021-09-01"));
        AccountMember members = new AccountMember(Long.valueOf(1),"MIKE", 50.50);

        AccountTransaction accountTransaction = new AccountTransaction(Long.valueOf(1),accountType,members,30.55, LocalDate.parse("2021-01-01"));


       // when(repo.getAccountTransactionByIdNativeQuery(isNull())).thenThrow(RuntimeException.class);

        AccountTransactionDto result  = translator.getAccountTransactionByIdNativeQuery(null);
        assertNotNull(result);


       // verify(repo,times(1)).getAccountTransactionByIdNativeQuery(anyLong());
        String val = result.toString();
        assertEquals(expectedResponse,val);
    }
}