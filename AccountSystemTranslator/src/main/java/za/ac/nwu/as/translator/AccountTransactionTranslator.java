package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.persistence.AccountTransaction;

import java.util.List;

public interface AccountTransactionTranslator {


    List<AccountTransaction> getAllAccountTransaction();

    AccountTransaction save(AccountTransaction accountTransaction);
}
