package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.as.logic.flow.FetchAccountTransactionFlow;

import java.util.List;

@RestController
@RequestMapping("account-transaction")
public class AccountTransactionController {

    private final FetchAccountTransactionFlow fetchAccountTransactionFlow;
    private final CreateAccountTransactionFlow createAccountTransactionFlow;
    /*private final FetchAccountTypeFlow fetchAccountTypeFlow;
    private final FetchAccountMemberFlow fetchAccountMemberFlow;*/

    public AccountTransactionController(FetchAccountTransactionFlow fetchAccountTransactionFlow, CreateAccountTransactionFlow createAccountTransactionFlow/*, FetchAccountTypeFlow fetchAccountTypeFlow, FetchAccountMemberFlow fetchAccountMemberFlow*/) {
        this.fetchAccountTransactionFlow = fetchAccountTransactionFlow;
        this.createAccountTransactionFlow = createAccountTransactionFlow;
       /* this.fetchAccountTypeFlow = fetchAccountTypeFlow;
        this.fetchAccountMemberFlow = fetchAccountMemberFlow;*/
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the configured Account transactions.", notes = "Returns a list of account transactions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account transactions returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<AccountTransactionDto>>> getAll() {
        List<AccountTransactionDto> accountTransactions = fetchAccountTransactionFlow.getAllAccountTransaction();
        GeneralResponse<List<AccountTransactionDto>> response = new GeneralResponse<> (true, accountTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping("add")
    @ApiOperation(value ="Create a new AccountType.", notes ="Crates a new AccountType in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The AccountTransaction was created successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })

    /*Change ApiParam*/
    public ResponseEntity<GeneralResponse<AccountTransactionDto>> add(/*
            @ApiParam(value = "The Id that uniquely identifies the AccountTransaction.",
                    example = "1",
                    name = "id",
                    required = true)
            @RequestParam("id") final Long id,
           @ApiParam(value = "The mnemonic that uniquely identifies the AccountType.",
            example = "MILES",
            name = "mnemonic",
            required = true)
            @RequestParam("mnemonic") final String mnemonic,
            @ApiParam(value = "The userName that uniquely identifies the AccountType.",
                    example = "MIKE",
                    name = "userName",
                    required = true)
            @RequestParam("userName") final String userName,
             @ApiParam(value = "The  amount by which theAccountMember should be increased by.",
                    example = "10.55",
                    name = "amount",
                    required = true)
            @RequestParam("amount") final Double amount,
            @ApiParam(value = "The optional new date with which to update the creation date in ISO date formate (yyyy-MM-dd)\r\nIf empty/null it will not be updated",
            name = "newCreationDate")
            @RequestParam(value = "newCreationDate",required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                   LocalDate newCreationDate

*/
            @ApiParam(value = "Request body to create a new AccountType",
                    required = true)
            @RequestBody AccountTransactionDto accountTransactions){
      /*  AccountType accountType = fetchAccountTypeFlow.getAccountTypeDbEntityByMnemonic(mnemonic);
        AccountMember accountMember = fetchAccountMemberFlow.getAccountMemberDbEntityByUsername(userName);*/


       //AccountTransactionDto accountTransactions = new AccountTransactionDto(id,accountType,accountMember,amount,newCreationDate);
        //AccountTransactionDto accountTransactionsResponse = createAccountTransactionFlow.create(id,accountType,accountMember,amount,newCreationDate);
        AccountTransactionDto accountTransactionsResponse = createAccountTransactionFlow.create(accountTransactions);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse<>(true,accountTransactionsResponse);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }



    @PostMapping("decrease")
    @ApiOperation(value ="Create a new AccountType.", notes ="Crates a new AccountType in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The AccountTransaction was created successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTransactionDto>> decrease(
            @ApiParam(value = "Request body to create a new AccountType",
            required = true)
            @RequestBody AccountTransactionDto accountTransactions){
        accountTransactions.setAmount(0.00-accountTransactions.getAmount());
        AccountTransactionDto accountTransactionsResponse = createAccountTransactionFlow.create(accountTransactions);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse<>(true,accountTransactionsResponse);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }




    @GetMapping("search/{id}")
    @ApiOperation(value = "Gets all the configured Account types.", notes = "Returns a list of account types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account types returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTransactionDto>> getAccountTransactionById(
            @ApiParam(value = "The  id by which the AccountTransaction can be uniquely identified.",
                    example = "1",
                    name = "id",
                    required = true)
            @RequestParam("id") final Double id) {
        AccountTransactionDto accountTransactions = fetchAccountTransactionFlow.getAccountTransactionById(id);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse<> (true, accountTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
