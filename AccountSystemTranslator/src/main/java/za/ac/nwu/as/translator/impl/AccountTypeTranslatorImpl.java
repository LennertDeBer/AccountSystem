package za.ac.nwu.as.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.domain.persistence.AccountType;
import za.ac.nwu.as.translator.AccountTypeTranslator;
import za.ac.nwu.as.repo.persistence.AccountTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTypeTranslatorImpl implements AccountTypeTranslator {
private final AccountTypeRepository accountTypeRepository;
@Autowired
public AccountTypeTranslatorImpl(AccountTypeRepository accountTypeRepository)
{
    this.accountTypeRepository = accountTypeRepository;
}
    @Override
    public List<AccountTypeDto> getAllAccountTypes() {
        List<AccountTypeDto> accountTypeDtos = new ArrayList<>();
        try {
            for (AccountType accountType : accountTypeRepository.findAll()) {
                accountTypeDtos.add(new AccountTypeDto(accountType));
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to read form DB.", e);
        }
        return accountTypeDtos;
    }

    @Override
    public AccountTypeDto create(AccountTypeDto accountTypeDto) {
        try {
            AccountType accountType = accountTypeRepository.save(accountTypeDto.getAccountType());
            return new AccountTypeDto(accountType);
        } catch (Exception e) {

            //TODO: log
            throw new RuntimeException("Unable to save to DB.", e);
        }}


        @Override
        public AccountTypeDto getAccountTypeByMnemonicNativeQuery(String mnemonic) {
            try {
                AccountType accountType = accountTypeRepository.getAccountTypeByMnemonicNativeQuery(mnemonic);
                return new AccountTypeDto(accountType);
            } catch (Exception e) {

                throw new RuntimeException("Unable to save to DB.", e);
            }
    }

        @Override
        public AccountTypeDto getAccountTypeByMnemonic(String mnemonic) {
            try {
                AccountType accountType = accountTypeRepository.getAccountTypeByMnemonic(mnemonic);
                return new AccountTypeDto(accountType);
            } catch (Exception e) {

                throw new RuntimeException("Unable to save to DB.", e);
            }
        }


        @Override
        public AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic) {
            try {
                return accountTypeRepository.getAccountTypeDtoByMnemonic(mnemonic);
            } catch (Exception e) {

                throw new RuntimeException("Unable to save to DB.", e);
            }
        }
}