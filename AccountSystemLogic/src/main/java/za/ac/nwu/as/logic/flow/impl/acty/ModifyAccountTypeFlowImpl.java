package za.ac.nwu.as.logic.flow.impl.acty;

import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.logic.flow.ModifyAccountTypeFlow;
import za.ac.nwu.as.translator.AccountTypeTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component
public class ModifyAccountTypeFlowImpl implements ModifyAccountTypeFlow {
    private final AccountTypeTranslator accountTypeTranslator;

    public ModifyAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator) {
        this.accountTypeTranslator = accountTypeTranslator;
    }

    @Override
    public AccountTypeDto deleteAccountType(String mnemonic) {
        AccountTypeDto accountTypeByMnemonic = accountTypeTranslator.getAccountTypeByMnemonic(mnemonic);
        accountTypeTranslator.deleteAccountType(mnemonic);
        return accountTypeByMnemonic;
    }

    @Override
    public AccountTypeDto updateAccountType(String mnemonic, String newAccountTypeName, LocalDate datenew) {

            if (null == datenew) {
                return accountTypeTranslator.updateAccountType(mnemonic, newAccountTypeName, LocalDate.now());
            }
            return accountTypeTranslator.updateAccountType(mnemonic, newAccountTypeName, datenew);
    }
}


