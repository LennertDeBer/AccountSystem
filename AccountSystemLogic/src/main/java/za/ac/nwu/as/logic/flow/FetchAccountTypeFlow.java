package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.domain.persistence.AccountType;

import java.util.List;

public interface FetchAccountTypeFlow {

    List<AccountTypeDto> getAllAccountTypes();

    AccountTypeDto getAccountTypeByMnemonic(String mnemonic);

    AccountType getAccountTypeDbEntityByMnemonic(String accountTypeMnemonic);
}
