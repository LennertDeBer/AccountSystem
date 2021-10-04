package za.ac.nwu.as.translator.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.domain.persistence.AccountType;
import za.ac.nwu.as.repo.persistence.AccountTypeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTypeTranslatorImplTest {

    @Mock
    private AccountTypeRepository repo;

    @InjectMocks
    private AccountTypeTranslatorImpl translator;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllAccountTypes() {
        String expectedResponse = "[AccountTypeDto{ID= 1,  mnemonic ='MILES', accountTypeName ='Miles', creationDate =2020-01-01}," +
                " AccountTypeDto{ID= 2,  mnemonic ='BUCKS', accountTypeName ='Bucks', creationDate =2020-01-01}," +
                " AccountTypeDto{ID= 3,  mnemonic ='PLAY', accountTypeName ='AppCurrency', creationDate =2021-09-11}, " +
                "AccountTypeDto{ID= 4,  mnemonic ='RAND', accountTypeName ='South-African', creationDate =2020-01-01}]";
        List<AccountType> accountTypes = new ArrayList<>();
        accountTypes.add(new AccountType(Long.valueOf(1),"MILES","Miles", LocalDate.parse("2020-01-01")));
        accountTypes.add(new AccountType(Long.valueOf(2),"BUCKS","Bucks", LocalDate.parse("2020-01-01")));
        accountTypes.add(new AccountType(Long.valueOf(3),"PLAY","AppCurrency", LocalDate.parse("2021-09-11")));
        accountTypes.add(new AccountType(Long.valueOf(4),"RAND","South-African", LocalDate.parse("2020-01-01")));
        when(repo.findAll()).thenReturn(accountTypes);
        List<AccountTypeDto> result  = translator.getAllAccountTypes();
        assertNotNull(result);

        verify(repo, atLeastOnce()).findAll();
        String val =result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void create() {
        String accountTypeToBeCreated1 ="AccountTypeDto{ID= 1,  mnemonic ='MILES', accountTypeName ='Miles', creationDate =2020-01-01}";
        AccountType accountType1 = new AccountType(Long.valueOf(1),"MILES", "Miles", LocalDate.parse("2020-01-01"));

        //when(acty.getAccountType()).thenReturn(accountType1);
        when(repo.save(any(AccountType.class))).thenReturn(accountType1);
        AccountTypeDto result  = translator.create(new AccountTypeDto());
        assertNotNull(result);
        verify(repo, atLeastOnce()).save(any(AccountType.class));
        assertEquals(accountTypeToBeCreated1,
                result.toString());
    }
    @Test(expected = RuntimeException.class)
    public void createError() {
        String accountTypeToBeCreated1 ="AccountTypeDto{ID= 1,  mnemonic ='MILES', accountTypeName ='Miles', creationDate =2020-01-01}";
        AccountType accountType1 = new AccountType(Long.valueOf(1),"MILES", "Miles", LocalDate.parse("2020-01-01"));

        //when(acty.getAccountType()).thenReturn(accountType1);
        //when(repo.save(any(AccountType.class))).thenReturn(accountType1);
        AccountTypeDto result  = translator.create(null);
        assertNotNull(result);
       // verify(repo, atLeastOnce()).save(any(AccountType.class));
        assertEquals(accountTypeToBeCreated1,
                result.toString());
    }

    @Test
    public void getAccountTypeByMnemonicNativeQuery() {

        String expectedResponse = "AccountTypeDto{ID= 3,  mnemonic ='PLAY', accountTypeName ='The new Play account type name', creationDate =2021-04-01}";



        AccountType accountType = new AccountType(Long.valueOf(3),"PLAY", "The new Play account type name", LocalDate.parse("2021-04-01"));


        when(repo.getAccountTypeByMnemonicNativeQuery(anyString())).thenReturn(accountType);
        //AccountType accountType =accountTypeDto.getAccountType();
        AccountTypeDto result  = translator.getAccountTypeByMnemonicNativeQuery("PLAY");
        assertNotNull(result);
        // verify(accountTypeDto, atLeastOnce()).getAccountType();
        verify(repo, atLeastOnce()).getAccountTypeByMnemonicNativeQuery(anyString());
        String val =result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }
    @Test(expected = RuntimeException.class)
    public void getAccountTypeByMnemonicNativeQueryError() {

        String expectedResponse = "AccountTypeDto{ID= 3,  mnemonic ='PLAY', accountTypeName ='The new Play account type name', creationDate =2021-04-01}";



        AccountType accountType = new AccountType(Long.valueOf(3),"PLAY", "The new Play account type name", LocalDate.parse("2021-04-01"));


        //when(repo.getAccountTypeByMnemonicNativeQuery(anyString())).thenReturn(accountType);
        //AccountType accountType =accountTypeDto.getAccountType();
        AccountTypeDto result  = translator.getAccountTypeByMnemonicNativeQuery(null);
        assertNotNull(result);
        // verify(accountTypeDto, atLeastOnce()).getAccountType();
      //  verify(repo, atLeastOnce()).getAccountTypeByMnemonicNativeQuery(anyString());
        String val =result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void getAccountTypeByMnemonic() {

        String expectedResponse = "AccountTypeDto{ID= 3,  mnemonic ='PLAY', accountTypeName ='The new Play account type name', creationDate =2021-04-01}";



        AccountType accountType = new AccountType(Long.valueOf(3),"PLAY", "The new Play account type name", LocalDate.parse("2021-04-01"));


        when(repo.getAccountTypeByMnemonic(anyString())).thenReturn(accountType);
        AccountTypeDto result  = translator.getAccountTypeByMnemonic("PLAY");
        assertNotNull(result);

        verify(repo, atLeastOnce()).getAccountTypeByMnemonic(anyString());
        String val =result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }
/*
    @Test
    public void deleteAccountType() {
        String expectedResponse = "AccountTypeDto{ID= 3,  mnemonic ='PLAY', accountTypeName ='The new Play account type name', creationDate =2021-04-01}";



        AccountType accountType = new AccountType(Long.valueOf(3),"PLAY", "The new Play account type name", LocalDate.parse("2021-04-01"));


        when(repo.getAccountTypeByMnemonic(anyString())).thenReturn(accountType);
         translator.deleteAccountType("PLAY");


        verify(repo, atLeastOnce()).getAccountTypeByMnemonic(anyString());

    }
*/
    @Test
    public void updateAccountType() {
        String expectedResponse = "AccountTypeDto{ID= 3,  mnemonic ='PLAY', accountTypeName ='AppCurrency', creationDate =2021-09-01}";

        AccountType accountType = new AccountType(Long.valueOf(3),"PLAY", "The new Play account type name",
                LocalDate.parse("2021-09-01"));

        when(repo.getAccountTypeByMnemonic(anyString())).thenReturn(accountType);
        AccountTypeDto result  = translator.updateAccountType(accountType.getMnemonic(),"AppCurrency",accountType.getCreationDate());
        assertNotNull(result);


        verify(repo,
                times(1)).getAccountTypeByMnemonic(anyString());
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void getAccountTypeDtoByMnemonic() {

        String expectedResponse = "AccountTypeDto{ID= 3,  mnemonic ='PLAY', accountTypeName ='The new Play account type name', creationDate =2021-04-01}";



        AccountType accountType = new AccountType(Long.valueOf(3),"PLAY", "The new Play account type name", LocalDate.parse("2021-04-01"));


        when(repo.getAccountTypeByMnemonic(anyString())).thenReturn(accountType);
        AccountTypeDto result  = translator.getAccountTypeByMnemonic("PLAY");
        assertNotNull(result);

        verify(repo, atLeastOnce()).getAccountTypeByMnemonic(anyString());
        String val =result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

}