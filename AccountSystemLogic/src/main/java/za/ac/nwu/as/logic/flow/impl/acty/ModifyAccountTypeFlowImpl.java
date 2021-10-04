package za.ac.nwu.as.logic.flow.impl.acty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.logic.flow.ModifyAccountTypeFlow;
import za.ac.nwu.as.logic.flow.impl.actx.CreateAccountTransactionFlowImpl;
import za.ac.nwu.as.translator.AccountTypeTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component
public class ModifyAccountTypeFlowImpl implements ModifyAccountTypeFlow {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);
    private final AccountTypeTranslator accountTypeTranslator;

    public ModifyAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator) {
        this.accountTypeTranslator = accountTypeTranslator;
    }

  /*  @Override
    public AccountTypeDto deleteAccountType(String mnemonic) {
        AccountTypeDto accountTypeByMnemonic = accountTypeTranslator.getAccountTypeByMnemonic(mnemonic);
        accountTypeTranslator.deleteAccountType(mnemonic);
        return accountTypeByMnemonic;
    }
*/
    @Override
    public AccountTypeDto updateAccountType(String mnemonic, String newAccountTypeName, LocalDate datenew) {
        LOGGER.info("The input mnemonic: {}\n AccountTypeName: {}\n date{}", mnemonic,newAccountTypeName,datenew);
            if (null == datenew) {
                datenew =  LocalDate.now();
            }
        AccountTypeDto result = accountTypeTranslator.updateAccountType(mnemonic, newAccountTypeName, datenew);
        LOGGER.info("The output {}",result);
            return result;
    }
}


