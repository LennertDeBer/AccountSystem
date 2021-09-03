package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.AccountTypeDto;
//import za.ac.nwu.as.translator.impl.AccountTypeTranslatorImpl
import java.util.List;

public interface AccountTypeTranslator {
    List<AccountTypeDto> getAllAccountTypes();
    AccountTypeDto create(AccountTypeDto accountType);
    AccountTypeDto getAccountTypeByMnemonicNativeQuery(String mnemonic);
    AccountTypeDto getAccountTypeByMnemonic(String mnemonic);
    AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);

}
