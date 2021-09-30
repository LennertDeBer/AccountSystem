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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class CreateAccountMemberFlowImplTest {

    @Mock
    private AccountMemberTranslator translator;

    @InjectMocks
    private CreateAccountMemberFlowImpl flow;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {
        String accountTypeToBeCreated ="AccountMemberDto{memberId=1,  memberUsername='MIKE'}";
        String expectedResponse ="{\"successful\":true,\"payload\":"+
                "{\"memberId\":1,\"memberUsername\":\"MIKE\"}}";



        AccountMemberDto members = new AccountMemberDto(Long.valueOf(1),"MIKE");


        when(translator.create(any(AccountMemberDto.class))).thenReturn(members);
        AccountMemberDto result  = flow.create(new AccountMemberDto());
        assertNotNull(result);
        verify(translator, atLeastOnce()).create(any(AccountMemberDto.class));
       String val= result.toString();
        assertEquals(accountTypeToBeCreated,
                result.toString());
    }
}