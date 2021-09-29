package za.ac.nwu.as.logic.flow.impl.actx;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.translator.AccountTransactionTranslator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FetchAccountTransactionFlowImplTest {

    @Mock
    private  AccountTransactionTranslator translator;

    @InjectMocks
    private FetchAccountTransactionFlowImpl flow;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllAccountTransaction() {
        String expectedResponse = "[AccountTransactionDto{transactionId=1, accountTypeMnemonic='MILES', typeId=1, accountMemberUsername='MIKE', memberId=1, amount=30.55, transactionDate=2021-01-01}]";
        AccountTransactionDto dto =  new AccountTransactionDto(Long.valueOf(1),"MILES",Long.valueOf(1),"MIKE",Long.valueOf(1),30.55, LocalDate.parse("2021-01-01"));


        List<AccountTransaction> accountTransactions = new ArrayList<>();
        accountTransactions.add(new AccountTransaction(Long.valueOf(1), dto.getAccountType(Long.valueOf(1),"MILES"),dto.getMember(Long.valueOf(1),"MIKE"),30.55, LocalDate.parse("2021-01-01")));

        when(translator.getAllAccountTransaction()).thenReturn(accountTransactions);
        List<AccountTransactionDto> result  = flow.getAllAccountTransaction();
        assertNotNull(result);

        verify(translator, atLeastOnce()).getAllAccountTransaction();
        String val =result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void getAccountTransactionById() {


            String expectedResponse = "AccountTransactionDto{transactionId=1, accountTypeMnemonic='MILES', typeId=1, accountMemberUsername='MIKE', memberId=1, amount=30.55, transactionDate=2021-01-01}";


            AccountTransactionDto accountTransaction = new AccountTransactionDto(Long.valueOf(1), "MILES", Long.valueOf(1), "MIKE", Long.valueOf(1), 30.55, LocalDate.parse("2021-01-01"));


            when(translator.getAccountTransactionByIdNativeQuery(anyDouble())).thenReturn(accountTransaction);

            AccountTransactionDto result = flow.getAccountTransactionById(1.0);
            assertNotNull(result);


            verify(translator, times(1)).getAccountTransactionByIdNativeQuery(1.0);
            String val = result.toString();
            assertEquals(expectedResponse, val);


    }
}