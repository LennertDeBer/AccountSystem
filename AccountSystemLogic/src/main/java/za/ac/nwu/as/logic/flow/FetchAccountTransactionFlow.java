package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.dto.AccountTransactionDto;

import java.util.List;

public interface FetchAccountTransactionFlow {
    List<AccountTransactionDto> getAllAccountTransaction();



    AccountTransactionDto getAccountTransactionById(Double id);

    Double getMemberBalance(Double id);
}
