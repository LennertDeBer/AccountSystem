package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public AccountTransactionController(FetchAccountTransactionFlow fetchAccountTransactionFlow, CreateAccountTransactionFlow createAccountTransactionFlow) {
        this.fetchAccountTransactionFlow = fetchAccountTransactionFlow;
        this.createAccountTransactionFlow = createAccountTransactionFlow;
    }
    @GetMapping("/all")
    @ApiOperation(value = "Gets all the configured Account types.", notes = "Returns a list of account types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account types returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<AccountTransactionDto>>> getAll() {
        List<AccountTransactionDto> accountTransactions = fetchAccountTransactionFlow.getAllAccountTransaction();
        GeneralResponse<List<AccountTransactionDto>> response = new GeneralResponse<> (true, accountTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping("New")
    @ApiOperation(value ="Create a new AccountType.", notes ="Crates a new AccountType in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The AccountType was created successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTransactionDto>> create(
            @ApiParam(value = "Request body to create a new AccountType",
                    required = true)
            @RequestBody AccountTransactionDto accountTransactions){
        AccountTransactionDto accountTransactionsResponse = createAccountTransactionFlow.create(accountTransactions);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse<>(true,accountTransactionsResponse);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
