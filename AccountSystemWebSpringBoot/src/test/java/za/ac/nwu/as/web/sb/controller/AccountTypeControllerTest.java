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
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.as.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.as.logic.flow.ModifyAccountTypeFlow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTypeControllerTest {

    private static final String APP_URL ="/account-system/mvc";
    private static final String ACCOUNT_TYPE_CONTROLLER_URL = APP_URL + "/account-type";
    @Mock
    private FetchAccountTypeFlow fetchAccountTypeFlow;
    @Mock
    private CreateAccountTypeFlow createAccountTypeFlow;
    @Mock
    private ModifyAccountTypeFlow modifyAccountTypeFlow;

    @InjectMocks
    private AccountTypeController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp()  {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAll() throws Exception{
        String expectedResponse = "{\"successful\":true,\"payload\":["+
        "{\"accountTypeId\":1,\"mnemonic\":\"MILES\",\"accountTypeName\":\"Miles\",\"creationDate\":[2020,1,1]},"+
                "{\"accountTypeId\":2,\"mnemonic\":\"BUCKS\",\"accountTypeName\":\"Bucks\",\"creationDate\":[2020,1,1]},"+
                "{\"accountTypeId\":3,\"mnemonic\":\"PLAY\",\"accountTypeName\":\"AppCurrency\",\"creationDate\":[2021,9,11]},"+
                "{\"accountTypeId\":4,\"mnemonic\":\"RAND\",\"accountTypeName\":\"South-African\",\"creationDate\":[2020,1,1]}]}";

        List<AccountTypeDto> accountTypes = new ArrayList<>();
        accountTypes.add(new AccountTypeDto(Long.valueOf(1),"MILES","Miles", LocalDate.parse("2020-01-01")));
        accountTypes.add(new AccountTypeDto(Long.valueOf(2),"BUCKS","Bucks", LocalDate.parse("2020-01-01")));
        accountTypes.add(new AccountTypeDto(Long.valueOf(3),"PLAY","AppCurrency", LocalDate.parse("2021-09-11")));
        accountTypes.add(new AccountTypeDto(Long.valueOf(4),"RAND","South-African", LocalDate.parse("2020-01-01")));

        when(fetchAccountTypeFlow.getAllAccountTypes()).thenReturn(accountTypes);
        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s",ACCOUNT_TYPE_CONTROLLER_URL,"all")))
                .servletPath(APP_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(fetchAccountTypeFlow,times(1)).getAllAccountTypes();
      //  String val = mvcResult.getResponse().getContentAsString();
    assertEquals(expectedResponse,mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void create() throws Exception {
        String accountTypeToBeCreated ="{\"accountTypeId\":1,\"mnemonic\":\"MILES\",\"accountTypeName\":\"Miles\",\"creationDate\":[2020,1,1]},";
        String expectedResponse = "{\"successful\":true,\"payload\":" +"{\"accountTypeId\":1,\"mnemonic\":\"MILES\",\"accountTypeName\":\"Miles\",\"creationDate\":[2020,1,1]}}";



        AccountTypeDto accountType = new AccountTypeDto(Long.valueOf(1),"MILES", "Miles", LocalDate.parse("2020-01-01"));


                when(createAccountTypeFlow.create(eq(accountType))).then(returnsFirstArg());

        MvcResult mvcResult = mockMvc.perform(post((String.format("%s/%s",ACCOUNT_TYPE_CONTROLLER_URL,"new")))
                                .servletPath(APP_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(accountTypeToBeCreated)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn();

        verify(createAccountTypeFlow, times(1)).create(eq(accountType));
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    //@Ignore
    public void deleteAccountType() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":" +
                "{\"accountTypeId\":3,\"mnemonic\":\"PLAY\",\"accountTypeName\":\"Play account type\",\"creationDate\":[2021,4,1]}}";

        AccountTypeDto accountType = new AccountTypeDto(Long.valueOf(3),"PLAY", "Play account type", LocalDate.parse("2021-04-01"));


                when(modifyAccountTypeFlow.deleteAccountType(anyString())).thenReturn(accountType);

        MvcResult mvcResult = mockMvc.perform(delete((String.format("%s/%s",
                        ACCOUNT_TYPE_CONTROLLER_URL, "PLAY")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(modifyAccountTypeFlow,
                times(1)).deleteAccountType(eq("PLAY"));
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void updateAccountType() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":" +
                "{\"accountTypeId\":3,\"mnemonic\":\"PLAY\",\"accountTypeName\":\"The new Play account type name\",\"creationDate\":[2021,4,1]}}";

        AccountTypeDto accountType = new AccountTypeDto(Long.valueOf(3),"PLAY", "The new Play account type name",
                LocalDate.parse("2021-04-01"));



        when(modifyAccountTypeFlow.updateAccountType(anyString(),
                anyString(), any(LocalDate.class))).thenReturn(accountType);

        MvcResult mvcResult = mockMvc.perform(put((String.format("%s/%s",
                ACCOUNT_TYPE_CONTROLLER_URL, "update/PLAY")))
                     .param("newAccountTypeName", "The new Play account type name")
                     .param("newCreationDate", "2021-04-01")
                     .servletPath(APP_URL)
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        String val = mvcResult.getResponse().getContentAsString();


        verify(modifyAccountTypeFlow,atLeastOnce()).updateAccountType(eq("PLAY"),eq("The new Play account type name"),eq(LocalDate.parse("2021-04-01")));



        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateAccountTypeWithNoOptionalDate() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":" +
                "{\"accountTypeId\":3,\"mnemonic\":\"PLAY\",\"accountTypeName\":\"The new Play account type name\",\"creationDate\":[2021,9,1]}}";

        AccountTypeDto accountType = new AccountTypeDto(Long.valueOf(3),"PLAY", "The new Play account type name",
                LocalDate.parse("2021-09-01"));

        when(modifyAccountTypeFlow.updateAccountType(anyString(),
                anyString(), isNull())).thenReturn(accountType);

        MvcResult mvcResult = mockMvc.perform(put((String.format("%s/%s",
                ACCOUNT_TYPE_CONTROLLER_URL, "update/PLAY")))
                .param("newAccountTypeName", "The new Play account type name")
                                .servletPath(APP_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(modifyAccountTypeFlow,
                times(1)).updateAccountType(eq("PLAY"),
                eq("The new Play account type name"), eq(null));
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateAccountTypeObitMandatory() throws Exception {
        mockMvc.perform(put((String.format("%s/%s",
                                ACCOUNT_TYPE_CONTROLLER_URL, "update/PLAY")))
                                .servletPath(APP_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(modifyAccountTypeFlow, never()).updateAccountType(anyString(), anyString(), any(LocalDate.class));
        verify(modifyAccountTypeFlow, never()).updateAccountType(anyString(), anyString(), isNull());
        verify(modifyAccountTypeFlow, never()).updateAccountType(anyString(), isNull(), isNull());
    }


    @Test
    public void getAccountType() throws Exception {

        String expectedResponse = "{\"successful\":true,\"payload\":" +
                "{\"accountTypeId\":3,\"mnemonic\":\"PLAY\",\"accountTypeName\":\"The new Play account type name\",\"creationDate\":[2021,4,1]}}";



        AccountTypeDto accountType = new AccountTypeDto(Long.valueOf(3),"PLAY", "The new Play account type name", LocalDate.parse("2021-04-01"));



        when(fetchAccountTypeFlow.getAccountTypeByMnemonic("PLAY")).thenReturn(accountType);
        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s",ACCOUNT_TYPE_CONTROLLER_URL,"search/PLAY")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("PLAY")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(fetchAccountTypeFlow,times(1)).getAccountTypeByMnemonic("PLAY");
         String val = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponse,mvcResult.getResponse().getContentAsString());

    }
}
