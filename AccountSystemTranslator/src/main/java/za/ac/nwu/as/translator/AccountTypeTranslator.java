package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.AccountTypeDto;

import java.time.LocalDate;
import java.util.List;

public interface AccountTypeTranslator {
    List<AccountTypeDto> getAllAccountTypes();
    AccountTypeDto create(AccountTypeDto accountType);
    AccountTypeDto getAccountTypeByMnemonicNativeQuery(String mnemonic);
    AccountTypeDto getAccountTypeByMnemonic(String mnemonic);
    void deleteAccountType(String mnemonic);
    AccountTypeDto updateAccountType(String mnemonic, String newAccountTypeName, LocalDate newdate);
    AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);
    void someMethod();

}
