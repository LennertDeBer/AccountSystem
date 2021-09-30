package za.ac.nwu.as.logic.flow.impl.actx;

import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.as.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class FetchAccountTransactionFlowImpl implements FetchAccountTransactionFlow {

    private final AccountTransactionTranslator accountTransactionTranslator;

    public FetchAccountTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator) {
        this.accountTransactionTranslator = accountTransactionTranslator;
    }

    @Override
    public List<AccountTransactionDto> getAllAccountTransaction() {
       List<AccountTransactionDto> transactionDto = new ArrayList<>();
       for (AccountTransaction accountTransaction : accountTransactionTranslator.getAllAccountTransaction()){
           transactionDto.add(new AccountTransactionDto(accountTransaction));
       }
       return transactionDto;
    }

    @Override
    public AccountTransactionDto getAccountTransactionById(Double id) {
        return accountTransactionTranslator.getAccountTransactionByIdNativeQuery(id);
    }

    @Override
    public Double getMemberBalance(Double id) {
        return accountTransactionTranslator.getMemberBalance(id);
    }


}
