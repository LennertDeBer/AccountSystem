package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.domain.dto.AccountMemberDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.CreateAccountMemberFlow;
import za.ac.nwu.as.logic.flow.FetchAccountMemberFlow;

import java.util.List;

@RestController
@RequestMapping("account-member")
public class AccountMemberController {


    private final FetchAccountMemberFlow fetchAccountMemberFlow;
    private final CreateAccountMemberFlow createAccountMemberFlow;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountMemberController.class);

    public AccountMemberController(FetchAccountMemberFlow fetchAccountMemberFlow, CreateAccountMemberFlow createAccountMemberFlow/*,FetchAccountTypeFlow fetchAccountTypeFlow,CreateAccountTransactionFlow createAccountTransactionFlow*/) {
        this.fetchAccountMemberFlow = fetchAccountMemberFlow;
        this.createAccountMemberFlow = createAccountMemberFlow;

     //   this.fetchAccountTypeFlow = fetchAccountTypeFlow;
      //  this.createAccountTransactionFlow = createAccountTransactionFlow;
    }

    @GetMapping("view/all")
    @ApiOperation(value = "Gets all the configured Member accounts.", notes = "Returns a list of member account's")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account types returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<AccountMemberDto>>> getAll() {

        List<AccountMemberDto> accountMembers = fetchAccountMemberFlow.getAllAccountMembers();
        GeneralResponse<List<AccountMemberDto>> response = new GeneralResponse<> (true, accountMembers);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping("new")
    @ApiOperation(value ="Create a new AccountMember.", notes ="Crates a new AccountMember in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The AccountMember was created successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountMemberDto>> create(
            @ApiParam(value = "Request body to create a new AccountType",
                    required = true)
            @RequestBody AccountMemberDto accountMember){
        AccountMemberDto accountMemberResponse = createAccountMemberFlow.create(accountMember);
        GeneralResponse<AccountMemberDto> response = new GeneralResponse<>(true,accountMemberResponse);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("search/{userName}")
    @ApiOperation(value = "Fetches the specified AccountMember.",notes = "Fetches the AccountMember corresponding to the given userName.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found"),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountMemberDto>> getAccountMember(
            @ApiParam(value = "The userName that uniquely identifies the AccountType.",
                    example = "MIKE",
                    name = "userName",
                    required = true)
            @PathVariable("userName") final String userName) {
        AccountMemberDto accountMemberDto = fetchAccountMemberFlow.getAccountMemberByUsername(userName);
        GeneralResponse<AccountMemberDto> response = new GeneralResponse<>(true,accountMemberDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}
