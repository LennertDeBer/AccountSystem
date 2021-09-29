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
import za.ac.nwu.as.domain.dto.AccountMemberDto;
import za.ac.nwu.as.logic.flow.CreateAccountMemberFlow;
import za.ac.nwu.as.logic.flow.FetchAccountMemberFlow;
import za.ac.nwu.as.logic.flow.ModifyAccountMemberFlow;

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
public class AccountMemberControllerTest {

    private static final String APP_URL ="/account-system/mvc";
    private static final String ACCOUNT_TYPE_CONTROLLER_URL = APP_URL + "/account-member";


    @Mock
    private FetchAccountMemberFlow fetchAccountMemberFlow;
   @Mock
    private CreateAccountMemberFlow createAccountMemberFlow;
    @Mock
    private ModifyAccountMemberFlow modifyAccountMemberFlow;

    private MockMvc mockMvc;
    @InjectMocks
    private AccountMemberController controller;

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
                "{\"memberId\":1,\"memberUsername\":\"MIKE\",\"accountBalance\":50.5}]}";
        List<AccountMemberDto> accountMembers = new ArrayList<>();
        accountMembers.add(new AccountMemberDto(Long.valueOf(1),"MIKE",50.50));

        when(fetchAccountMemberFlow.getAllAccountMembers()).thenReturn(accountMembers);
        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s",ACCOUNT_TYPE_CONTROLLER_URL,"view/all")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(fetchAccountMemberFlow,times(1)).getAllAccountMembers();
          String val = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponse,mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void create() throws Exception {
        String accountTypeToBeCreated ="{\"memberId\":1,\"memberUsername\":\"MIKE\",\"accountBalance\":50.5}]}";
        String expectedResponse ="{\"successful\":true,\"payload\":"+
                "{\"memberId\":1,\"memberUsername\":\"MIKE\",\"accountBalance\":50.5}}";



        AccountMemberDto members = new AccountMemberDto(Long.valueOf(1),"MIKE", 50.50);


        when(createAccountMemberFlow.create(eq(members))).then(returnsFirstArg());

        MvcResult mvcResult = mockMvc.perform(post((String.format("%s/%s",ACCOUNT_TYPE_CONTROLLER_URL,"new")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(accountTypeToBeCreated)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        verify(createAccountMemberFlow, times(1)).create(eq(members));
        String val =  mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getAccountMember() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":"+
                "{\"memberId\":1,\"memberUsername\":\"MIKE\",\"accountBalance\":50.5}}";


        AccountMemberDto member = new AccountMemberDto(Long.valueOf(1),"MIKE", 50.50);



        when(fetchAccountMemberFlow.getAccountMemberByUsername("MIKE")).thenReturn(member);
        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s",ACCOUNT_TYPE_CONTROLLER_URL,"search/MIKE")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("MIKE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(fetchAccountMemberFlow,times(1)).getAccountMemberByUsername("MIKE");
        String val = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponse,mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void increaseAccountMemberBalance() throws Exception {
       String expectedResponse = "{\"successful\":true,\"payload\":"+
                "{\"memberId\":1,\"memberUsername\":\"MIKE\",\"accountBalance\":61.0}}";

        AccountMemberDto  member = new AccountMemberDto(Long.valueOf(1),"MIKE", 61.0);



        when(modifyAccountMemberFlow.increaseAccountMemberBalance(anyString(),
                anyDouble(), any(LocalDate.class))).thenReturn(member);

        MvcResult mvcResult = mockMvc.perform(put((String.format("%s/%s",
                        ACCOUNT_TYPE_CONTROLLER_URL, "increase/MIKE")))
                        .param("amount", "10.5")
                        .param("newCreationDate", "2021-04-01")
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        String val = mvcResult.getResponse().getContentAsString();


        verify(modifyAccountMemberFlow,atLeastOnce()).increaseAccountMemberBalance(eq("MIKE"),eq(Double.parseDouble("10.5")),eq(LocalDate.parse("2021-04-01")));



        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void increaseAccountMemberBalanceWithNoOptionalDate() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":"+
                "{\"memberId\":1,\"memberUsername\":\"MIKE\",\"accountBalance\":61.0}}";

        AccountMemberDto  member = new AccountMemberDto(Long.valueOf(1),"MIKE", 61.0);



        when(modifyAccountMemberFlow.increaseAccountMemberBalance(anyString(),
                anyDouble(),isNull())).thenReturn(member);

        MvcResult mvcResult = mockMvc.perform(put((String.format("%s/%s",
                        ACCOUNT_TYPE_CONTROLLER_URL, "increase/MIKE")))
                        .param("amount", "10.5")
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        String val = mvcResult.getResponse().getContentAsString();


        verify(modifyAccountMemberFlow,atLeastOnce()).increaseAccountMemberBalance(eq("MIKE"),eq(Double.parseDouble("10.5")),eq(null));



        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void increaseAccountMemberBalanceObitMandatory() throws Exception {
        mockMvc.perform(put((String.format("%s/%s",
                        ACCOUNT_TYPE_CONTROLLER_URL, "increase/MIKE")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(modifyAccountMemberFlow, never()).increaseAccountMemberBalance(anyString(),anyDouble(), any(LocalDate.class));
        verify(modifyAccountMemberFlow, never()).increaseAccountMemberBalance(anyString(), anyDouble(), isNull());
    }

    @Test
    public void decreaseAccountMemberBalance() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":"+
                "{\"memberId\":1,\"memberUsername\":\"MIKE\",\"accountBalance\":50.5}}";

        AccountMemberDto  member = new AccountMemberDto(Long.valueOf(1),"MIKE", 50.5);



        when(modifyAccountMemberFlow.decreaseAccountMemberBalance(anyString(),
                anyDouble(), any(LocalDate.class))).thenReturn(member);

        MvcResult mvcResult = mockMvc.perform(put((String.format("%s/%s",
                        ACCOUNT_TYPE_CONTROLLER_URL, "decrease/MIKE")))
                        .param("amount", "10.5")
                        .param("newCreationDate", "2021-04-01")
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        String val = mvcResult.getResponse().getContentAsString();


        verify(modifyAccountMemberFlow,atLeastOnce()).decreaseAccountMemberBalance(eq("MIKE"),eq(Double.parseDouble("10.5")),eq(LocalDate.parse("2021-04-01")));



        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }




    @Test
    public void decreaseAccountMemberBalanceWithNoOptionalDate() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":"+
                "{\"memberId\":1,\"memberUsername\":\"MIKE\",\"accountBalance\":50.5}}";

        AccountMemberDto  member = new AccountMemberDto(Long.valueOf(1),"MIKE", 50.5);



        when(modifyAccountMemberFlow.decreaseAccountMemberBalance(anyString(),
                anyDouble(), isNull())).thenReturn(member);

        MvcResult mvcResult = mockMvc.perform(put((String.format("%s/%s",
                        ACCOUNT_TYPE_CONTROLLER_URL, "decrease/MIKE")))
                        .param("amount", "10.5")
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        String val = mvcResult.getResponse().getContentAsString();


        verify(modifyAccountMemberFlow,atLeastOnce()).decreaseAccountMemberBalance(eq("MIKE"),eq(Double.parseDouble("10.5")),eq(null));



        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void decreaseAccountMemberBalanceObitMandatory() throws Exception {
        mockMvc.perform(put((String.format("%s/%s",
                        ACCOUNT_TYPE_CONTROLLER_URL, "decrease/MIKE")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(modifyAccountMemberFlow, never()).decreaseAccountMemberBalance(anyString(), anyDouble(), any(LocalDate.class));
        verify(modifyAccountMemberFlow, never()).decreaseAccountMemberBalance(anyString(), anyDouble(), isNull());
    }
}