package za.ac.nwu.as.logic.flow.impl.actx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

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
        //LOGGER.warn("The input object was {}",accountTransactionDto);
        LOGGER.info("The input object was {}", accountTransactionDto);

        if(null==accountTransactionDto.getTransactionDate())
        {
            accountTransactionDto.setTransactionDate(LocalDate.now());
        }
        //AccountTransactionDto.setTransactionId(new Long(50L));

        //Long transactionId, String accountTypeMnemonic, Long typeId, String accountMemberUsername, Long memberId, Double amount, LocalDate transactionDate
       /*AccountTransactionDto dto = new AccountTransactionDto();
        AccountTransaction at = AccountTransactionDto.getAccountTransaction(accountTransactionDto);*/


      // Discovery code for method
        AccountType accountType = fetchAccountTypeFlow.getAccountTypeDbEntityByMnemonic(accountTransactionDto.getAccountTypeMnemonic());
        AccountMember accountMember = fetchAccountMemberFlow.getAccountMemberDbEntityByUsername(accountTransactionDto.getAccountMemberUsername());

       // if(LOGGER.isInfoEnabled()) {
       //     LOGGER.info("Got AccountType for {} and the AccountTypeId is {}", accountTransactionDto.getAccountTypeMnemonic(), accountType.getAccountTypeID());
        //    LOGGER.info("Got AccountMember for  {} and the AccountMemberId is {}", accountTransactionDto.getAccountMemberUsername(), accountMember.getMemberID());
       // }
        AccountTransaction accountTransaction = accountTransactionDto.buildAccountTransaction(accountType, accountMember);

        AccountTransaction createAccountTransaction = accountTransactionTranslator.save(accountTransaction);
        //AccountTransaction createAccountTransaction = accountTransactionTranslator.save(at);

      /*  if(null != accountTransactionDto.getDetails())
        {
           AccountTransactionDetails accountTransactionDetails = accountTransactionDto.getDetails()
                   .buildAccountTransactionDetails(createAccountTransaction);
           accountTransactionDetailsTranslator.save(accountTransactionDetails);
        }*/

        AccountTransactionDto result =new AccountTransactionDto(createAccountTransaction);
       // LOGGER.warn("The return object is {}",result);
        LOGGER.info("The return object is {}",result);
        return result;
    }

   /* @Override
    public AccountTransactionDto create(Long transactionId, AccountType accountType, AccountMember accountMember, Double amount, LocalDate transactionDate) {
        if(null==transactionDate)
        {
            transactionDate = LocalDate.now();
        }
        AccountTransactionDto dto = new AccountTransactionDto(transactionId,accountType,accountMember,amount,transactionDate);
        AccountTransaction at = AccountTransactionDto.getAccountTransaction(dto);
        AccountTransaction createAccountTransaction = accountTransactionTranslator.save(at);
        return dto;
    }*/
}
