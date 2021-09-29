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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountTypeFlowImplTest {

    //easy way
    @Mock
    private AccountTypeTranslator translator;

    //easy way
    @InjectMocks
    private CreateAccountTypeFlowImpl flow;

    @Before
    public void setUp() throws Exception {
        /*harder way
        translator = Mockito.mock(AccountTypeTranslator.class);
        flow = new CreateAccountTypeFlowImpl(translator);
        harder way*/
       // doThrow(new RuntimeException()).when(translator).someMethod();
       // newMethod();
        //flow = new CreateAccountTypeFlowImpl(translator);
    }

    @After
    public void tearDown() throws Exception {
        //flow = null;

    }
  /*  @Test
    public void createNoDate() {
        //void
        //doThrow(new RuntimeException()).when(translator).someMethod();
        //only on exception per method
        //not void



        String accountTypeToBeCreated2 ="AccountTypeDto{ID= 1,  mnenomic ='MILES', accountTypeName ='Miles', creationDate ="+LocalDate.now()+"}";
        AccountTypeDto accountType2 = new AccountTypeDto(Long.valueOf(1),"MILES", "Miles", null);

        when(translator.create(any(AccountTypeDto.class))).thenReturn(accountType2);
        AccountTypeDto result  = flow.create(accountType2);
        assertNotNull(result);
        verify(translator, atLeastOnce()).create(any(AccountTypeDto.class));
        String val= result.toString();
        assertEquals(accountTypeToBeCreated2,
                result.toString());
    }
*/
    @Test
    public void create() {
        //void
       //doThrow(new RuntimeException()).when(translator).someMethod();
        //only on exception per method
        //not void
        String accountTypeToBeCreated1 ="AccountTypeDto{ID= 1,  mnenomic ='MILES', accountTypeName ='Miles', creationDate =2020-01-01}";
        AccountTypeDto accountType1 = new AccountTypeDto(Long.valueOf(1),"MILES", "Miles", LocalDate.parse("2020-01-01"));

        when(translator.create(any(AccountTypeDto.class))).thenReturn(accountType1);
        AccountTypeDto result  = flow.create(new AccountTypeDto());
        assertNotNull(result);
      verify(translator, atLeastOnce()).create(any(AccountTypeDto.class));
        assertEquals(accountTypeToBeCreated1,
                result.toString());
    }/*
    @Test
    public void createObitMandatory() {
        verify(translator, never()).updateAccountType(anyString(), anyString(), any(LocalDate.class));
        verify(translator, never()).updateAccountType(anyString(), anyString(), isNull());
      //  verify(translator, atLeastOnce()).create(any(AccountTypeDto.class));
    }
*/

    private void newMethod() {
        try {
             flow.create(new AccountTypeDto());

             fail("Should throw exception");
            }
        catch (Exception e)
            {}

    }
}