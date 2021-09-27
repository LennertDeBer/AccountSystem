package za.ac.nwu.as.logic.flow.impl.actx;

import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountMember;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.domain.persistence.AccountType;
import za.ac.nwu.as.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.as.logic.flow.FetchAccountMemberFlow;
import za.ac.nwu.as.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.as.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;


@Transactional
@Component
public class CreateAccountTransactionFlowImpl implements CreateAccountTransactionFlow {
    private final FetchAccountTypeFlow fetchAccountTypeFlow;
    private final FetchAccountMemberFlow fetchAccountMemberFlow;
    private final AccountTransactionTranslator accountTransactionTranslator;

    public CreateAccountTransactionFlowImpl(FetchAccountTypeFlow fetchAccountTypeFlow, FetchAccountMemberFlow fetchAccountMemberFlow, AccountTransactionTranslator accountTransactionTranslator) {
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
        this.fetchAccountMemberFlow = fetchAccountMemberFlow;
        this.accountTransactionTranslator = accountTransactionTranslator;
    }

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto) {
        //AccountTransactionDto.setTransactionId(new Long(50L));

        //Long transactionId, String accountTypeMnemonic, Long typeId, String accountMemberUsername, Long memberId, Double amount, LocalDate transactionDate
       /*AccountTransactionDto dto = new AccountTransactionDto();
        AccountTransaction at = AccountTransactionDto.getAccountTransaction(accountTransactionDto);*/


      // Discovery code for method
        AccountType accountType = fetchAccountTypeFlow.getAccountTypeDbEntityByMnemonic(accountTransactionDto.getAccountTypeMnemonic());
        AccountMember accountMember = fetchAccountMemberFlow.getAccountMemberDbEntityByUsername(accountTransactionDto.getAccountMemberUsername());


        AccountTransaction accountTransaction = accountTransactionDto.buildAccountTransaction(accountType, accountMember);

        AccountTransaction createAccountTransaction = accountTransactionTranslator.save(accountTransaction);
        //AccountTransaction createAccountTransaction = accountTransactionTranslator.save(at);

      /*  if(null != accountTransactionDto.getDetails())
        {
           AccountTransactionDetails accountTransactionDetails = accountTransactionDto.getDetails()
                   .buildAccountTransactionDetails(createAccountTransaction);
           accountTransactionDetailsTranslator.save(accountTransactionDetails);
        }*/

        return new AccountTransactionDto(createAccountTransaction);
    }

    @Override
    public AccountTransactionDto create(Long transactionId, AccountType accountType, AccountMember accountMember, Double amount, LocalDate transactionDate) {
        if(null==transactionDate)
        {
            transactionDate = LocalDate.now();
        }
        AccountTransactionDto dto = new AccountTransactionDto(transactionId,accountType,accountMember,amount,transactionDate);
        AccountTransaction at = AccountTransactionDto.getAccountTransaction(dto);
        AccountTransaction createAccountTransaction = accountTransactionTranslator.save(at);
        return dto;
    }
}
