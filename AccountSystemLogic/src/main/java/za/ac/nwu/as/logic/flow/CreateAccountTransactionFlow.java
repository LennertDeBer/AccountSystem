package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.dto.AccountTransactionDto;

public interface CreateAccountTransactionFlow {
    AccountTransactionDto create(AccountTransactionDto accountTransactions);
  //  AccountTransactionDto create(Long transactionId, AccountType accountType, AccountMember accountMember, Double amount, LocalDate transactionDate);
}
