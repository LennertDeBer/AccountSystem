package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.dto.AccountTypeDto;

import java.time.LocalDate;

public interface ModifyAccountTypeFlow {
   // AccountTypeDto deleteAccountType(String mnemonic);
    AccountTypeDto updateAccountType(String mnemonic, String newAccountTypeName, LocalDate datenew);
}
