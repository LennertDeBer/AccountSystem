package za.ac.nwu.as.translator.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.repo.persistence.AccountTransactionRepository;
import za.ac.nwu.as.translator.AccountTransactionTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTransactionTranslatorImpl implements AccountTransactionTranslator {
 //   private static final Logger LOGGER = LoggerFactory.getLogger(AccountTransactionTranslatorImpl.class);
    private AccountTransactionRepository repo;

    public AccountTransactionTranslatorImpl(AccountTransactionRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<AccountTransaction> getAllAccountTransaction() {
        List<AccountTransaction> transactions = new ArrayList<>();
        try {
            for (AccountTransaction accountTransaction : repo.findAll()) {
                transactions.add(new AccountTransaction(accountTransaction));
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to read form DB.", e);
        }
        return transactions;
    }

    @Override
    public AccountTransaction save(AccountTransaction accountTransaction) {
        try {
        return repo.save(accountTransaction);
        }catch (Exception e)
        {
            throw new RuntimeException("Unable to save to the DB",e);
        }
    }

    @Override
    public AccountTransactionDto getAccountTransactionByIdNativeQuery(Double id) {
        try {
           Long val  = Long.valueOf(id.intValue());
          // LOGGER.info("The id is {}",val);
            AccountTransaction accountTransaction = repo.getAccountTransactionByIdNativeQuery(val);
            return new AccountTransactionDto(accountTransaction);
        } catch (Exception e) {

            throw new RuntimeException("Unable to save to DB.", e);
        }
    }

    @Override
    public Double getMemberBalance(Double id) {
        try {
            Long val  = Long.valueOf(id.intValue());
            // LOGGER.info("The id is {}",val);
            Double accountTransaction = repo.getAccountTransactionByIdNativeQueryD(val);
            return accountTransaction;
        } catch (Exception e) {

            throw new RuntimeException("Unable to save to DB.", e);
        }
    }


}
