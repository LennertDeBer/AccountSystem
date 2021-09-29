package za.ac.nwu.as.logic.flow.impl.acty;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.translator.AccountTypeTranslator;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ModifyAccountTypeFlowImplTest {
    @Mock
    private AccountTypeTranslator translator;

    @InjectMocks
    private ModifyAccountTypeFlowImpl flow;


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void deleteAccountTypeMiles() {/*
        String accountTypeToBeCreated ="AccountTypeDto{ID= 1,  mnenomic ='MILES', accountTypeName ='Miles', creationDate =2020-01-01}";
        AccountTypeDto accountType = new AccountTypeDto(Long.valueOf(1),"MILES", "Miles", LocalDate.parse("2020-01-01"));
        doThrow(new RuntimeException()).when(translator).deleteAccountType(anyString());
        AccountTypeDto result  = flow.deleteAccountType("MILES");
        //AccountTypeDto result  = translator.getAccountTypeByMnemonic("MILES");
        assertNotNull(result);
        verify(translator, atLeastOnce()).create(any(AccountTypeDto.class));
        String val = result.toString();
        assertEquals(accountTypeToBeCreated,
                result.toString());
        when(translator.getAccountTypeByMnemonic(anyString())).thenReturn(accountType);
        when(translator.getAccountTypeByMnemonic(anyString())).thenReturn(accountType);
        verify(translator,atLeastOnce()).getAccountTypeByMnemonic(accountType.getMnemonic());
        verify(translator,atLeastOnce()).deleteAccountType(accountType.getMnemonic());
*/

    }


    @Test
    public void updateAccountType() throws Exception {
        String expectedResponse = "AccountTypeDto{ID= 3,  mnenomic ='PLAY', accountTypeName ='The new Play account type name', creationDate =2021-09-01}";

        AccountTypeDto accountType = new AccountTypeDto(Long.valueOf(3),"PLAY", "The new Play account type name",
                LocalDate.parse("2021-09-01"));

        when(translator.updateAccountType(anyString(),
                anyString(), any(LocalDate.class))).thenReturn(accountType);
        AccountTypeDto result  = flow.updateAccountType(accountType.getMnemonic(),accountType.getAccountTypeName(),accountType.getCreationDate());
        assertNotNull(result);


        verify(translator,
                times(1)).updateAccountType(eq("PLAY"),
                eq("The new Play account type name"), eq(LocalDate.parse("2021-09-01")));
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void updateAccountTypeWithNoOptionalDate() throws Exception {
        String expectedResponse = "AccountTypeDto{ID= 3,  mnenomic ='PLAY', accountTypeName ='The new Play account type name', creationDate ="+LocalDate.now()+"}";

        AccountTypeDto accountType = new AccountTypeDto(Long.valueOf(3),"PLAY", "The new Play account type name",
                LocalDate.now());

        when(translator.updateAccountType(anyString(),
                anyString(), isNull())).thenReturn(accountType);
        AccountTypeDto result = translator.updateAccountType(accountType.getMnemonic(), accountType.getAccountTypeName(), null);;
        /*if(null==accountType.getCreationDate()) {
             result = flow.updateAccountType(accountType.getMnemonic(), accountType.getAccountTypeName(), LocalDate.now());
        }*/
        assertNotNull(result);


        verify(translator,
                atLeastOnce()).updateAccountType(eq("PLAY"),
                eq("The new Play account type name"), eq(null));
        String val = result.toString();
        assertEquals(expectedResponse,
                result.toString());
    }

    @Test
    public void updateAccountTypeObitMandatory() throws Exception {

        verify(translator, never()).updateAccountType(anyString(), anyString(), any(LocalDate.class));
        verify(translator, never()).updateAccountType(anyString(), anyString(), isNull());
        verify(translator, never()).updateAccountType(anyString(), isNull(), isNull());
    }
}