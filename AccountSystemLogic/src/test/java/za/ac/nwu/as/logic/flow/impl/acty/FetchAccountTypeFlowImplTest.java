package za.ac.nwu.as.logic.flow.impl.acty;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.domain.persistence.AccountType;
import za.ac.nwu.as.translator.AccountTypeTranslator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FetchAccountTypeFlowImplTest {

    @Mock
    private AccountTypeTranslator translator;

    //easy way
    @InjectMocks
    private FetchAccountTypeFlowImpl flow;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllAccountTypes() {
        String expectedResponse = "[AccountTypeDto{ID= 1,  mnenomic ='MILES', accountTypeName ='Miles', creationDate =2020-01-01}," +
                " AccountTypeDto{ID= 2,  mnenomic ='BUCKS', accountTypeName ='Bucks', creationDate =2020-01-01}," +
                " AccountTypeDto{ID= 3,  mnenomic ='PLAY', accountTypeName ='AppCurrency', creationDate =2021-09-11}, " +
                "AccountTypeDto{ID= 4,  mnenomic ='RAND', accountTypeName ='South-African', creationDate =2020-01-01}]";
        List<AccountTypeDto> accountTypes = new ArrayList<>();
        accountTypes.add(new AccountTypeDto(Long.valueOf(1),"MILES","Miles", LocalDate.parse("2020-01-01")));
        accountTypes.add(new AccountTypeDto(Long.valueOf(2),"BUCKS","Bucks", LocalDate.parse("2020-01-01")));
        accountTypes.add(new AccountTypeDto(Long.valueOf(3),"PLAY","AppCurrency", LocalDate.parse("2021-09-11")));
        accountTypes.add(new AccountTypeDto(Long.valueOf(4),"RAND","South-African", LocalDate.parse("2020-01-01")));
        when(translator.getAllAccountTypes()).thenReturn(accountTypes);
        List<AccountTypeDto> result  = flow.getAllAccountTypes();
        assertNotNull(result);

        verify(translator, atLeastOnce()).getAllAccountTypes();
        String val =result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void getAccountTypeByMnemonic() {
        String expectedResponse = "AccountTypeDto{ID= 3,  mnenomic ='PLAY', accountTypeName ='The new Play account type name', creationDate =2021-04-01}";



        AccountTypeDto accountType = new AccountTypeDto(Long.valueOf(3),"PLAY", "The new Play account type name", LocalDate.parse("2021-04-01"));


        when(translator.getAccountTypeByMnemonicNativeQuery(anyString())).thenReturn(accountType);
        AccountTypeDto result  = flow.getAccountTypeByMnemonic("PLAY");
        assertNotNull(result);

        verify(translator, atLeastOnce()).getAccountTypeByMnemonicNativeQuery(anyString());
        String val =result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void getAccountTypeDbEntityByMnemonic() {
        String expectedResponse = "AccountType{accountTypeID=3, mnemonic='PLAY', accountTypeName='The new Play account type name', creationDate=2021-04-01}";



        AccountTypeDto accountTypeDto = new AccountTypeDto(Long.valueOf(3),"PLAY", "The new Play account type name", LocalDate.parse("2021-04-01"));


        when(translator.getAccountTypeByMnemonicNativeQuery(anyString())).thenReturn(accountTypeDto);
        //AccountType accountType =accountTypeDto.getAccountType();
                AccountType result  = flow.getAccountTypeDbEntityByMnemonic("PLAY");
        assertNotNull(result);
       // verify(accountTypeDto, atLeastOnce()).getAccountType();
        verify(translator, atLeastOnce()).getAccountTypeByMnemonicNativeQuery(anyString());
        String val =result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void methodToTest() {
        assertTrue(flow.methodToTest());
    }
}