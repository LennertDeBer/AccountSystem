package za.ac.nwu.as.web.sb.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.as.logic.flow.FetchAccountTransactionFlow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTransactionControllerTest {
    private static final String APP_URL ="/account-system/mvc";
    private static final String ACCOUNT_TYPE_CONTROLLER_URL = APP_URL + "/account-transaction";
    @Mock
    private FetchAccountTransactionFlow fetchAccountTransactionFlow;
    @Mock
    private CreateAccountTransactionFlow createAccountTransactionFlow;


    @InjectMocks
    private AccountTransactionController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAll() throws Exception {

        String expectedResponse = "{\"successful\":true,\"payload\":["+
                "{\"transactionId\":1,\"accountTypeMnemonic\":\"MILES\",\"typeId\":1,\"accountMemberUsername\":\"MIKE\",\"memberId\":1,\"amount\":30.55,\"transactionDate\":[2021,1,1]}]}";



        List<AccountTransactionDto> accountTransactions = new ArrayList<>();
        accountTransactions.add(new AccountTransactionDto(Long.valueOf(1),"MILES",Long.valueOf(1),"MIKE",Long.valueOf(1),30.55, LocalDate.parse("2021-01-01")));


        when(fetchAccountTransactionFlow.getAllAccountTransaction()).thenReturn(accountTransactions);
        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s",ACCOUNT_TYPE_CONTROLLER_URL,"all")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(fetchAccountTransactionFlow,times(1)).getAllAccountTransaction();
        //  String val = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponse,mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void create() throws Exception {
        String accountTransactionToBeCreated ="{\"transactionId\":1,\"accountTypeMnemonic\":\"MILES\",\"typeId\":1,\"accountMemberUsername\":\"MIKE\",\"memberId\":1,\"amount\":30.55,\"transactionDate\":[2021,1,1]}}";
        String expectedResponse = "{\"successful\":true,\"payload\":{\"transactionId\":1,\"accountTypeMnemonic\":\"MILES\",\"typeId\":1,\"accountMemberUsername\":\"MIKE\",\"memberId\":1,\"amount\":30.55,\"transactionDate\":[2021,1,1]}}";



        AccountTransactionDto accountTransaction = new AccountTransactionDto(Long.valueOf(1),"MILES",Long.valueOf(1),"MIKE",Long.valueOf(1),30.55, LocalDate.parse("2021-01-01"));


        when(createAccountTransactionFlow.create(eq(accountTransaction))).then(returnsFirstArg());

        MvcResult mvcResult = mockMvc.perform(post((String.format("%s/%s",ACCOUNT_TYPE_CONTROLLER_URL,"new")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(accountTransactionToBeCreated)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        verify(createAccountTransactionFlow, times(1)).create(eq(accountTransaction));
     String val =  mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getAccountTransactionById() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":{\"transactionId\":1,\"accountTypeMnemonic\":\"MILES\",\"typeId\":1,\"accountMemberUsername\":\"MIKE\",\"memberId\":1,\"amount\":30.55,\"transactionDate\":[2021,1,1]}}";



        AccountTransactionDto accountTransaction = new AccountTransactionDto(Long.valueOf(1),"MILES",Long.valueOf(1),"MIKE",Long.valueOf(1),30.55, LocalDate.parse("2021-01-01"));




        when(fetchAccountTransactionFlow.getAccountTransactionById(anyDouble())).thenReturn(accountTransaction);



        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s",ACCOUNT_TYPE_CONTROLLER_URL,"search/%l",1)))
                        .param("id", "1")
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(fetchAccountTransactionFlow,times(1)).getAccountTransactionById(1.0);
        String val = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponse,mvcResult.getResponse().getContentAsString());
    }
}