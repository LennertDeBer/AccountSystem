package za.ac.nwu.as.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.translator.AccountTypeTranslator;

import static org.junit.Assert.fail;
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
    }

    @After
    public void tearDown() throws Exception {
        newMethod();
    }

    @Test
    public void create() {
        //void
        doThrow(new RuntimeException()).when(translator).someMethod();
        //only on exception per method
        //not void
        //when(translator.getAccountTypeByMnemonic(anyString())).thenThrow(new Exception());
        newMethod();

        //verify(translator,times(1)).getAccountTypeByMnemonic(anyString());
        verify(translator,times(1)).someMethod();
        verify(translator,never()).create(any(AccountTypeDto.class));



        //the method should be called at least one time
        // verify(translator,atLeastOnce()).create(eq(new AccountTypeDto()));

        //method should not be called is success
        //verify(translator,never()).create(any(AccountTypeDto.class));
    }


    private void newMethod() {
        try {
             flow.create(new AccountTypeDto());

             fail("Should throw exception");
            }
        catch (Exception e)
            {}

    }
}