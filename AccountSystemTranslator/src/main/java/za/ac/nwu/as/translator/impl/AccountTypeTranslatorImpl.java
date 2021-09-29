package za.ac.nwu.as.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.domain.persistence.AccountType;
import za.ac.nwu.as.repo.persistence.AccountTypeRepository;
import za.ac.nwu.as.translator.AccountTypeTranslator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
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


    @PersistenceContext
    private EntityManager manage;


    public void deleteAccountType(String mnemonic) {
        try {
            List<AccountTypeDto> accountTypeDtos = getAllAccountTypes();
            accountTypeDtos.remove(new AccountTypeDto());
            accountTypeRepository.delete(accountTypeRepository.getAccountTypeByMnemonic(mnemonic));


        } catch (Exception e) {

            throw new RuntimeException("Unable to save to DB.", e);
        }
    }

    @Override
    public AccountTypeDto updateAccountType(String mnemonic, String newAccountTypeName, LocalDate newdate) {
        try {
            AccountType accountType = accountTypeRepository.getAccountTypeByMnemonic(mnemonic);
            accountType.setAccountTypeName(newAccountTypeName);
            accountType.setCreationDate(newdate);
            return new AccountTypeDto(accountType);
        } catch (Exception e) {

            throw new RuntimeException("Unable to save to DB.", e);
        }
    }


   /* @Override
        public AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic) {
            try {
                return accountTypeRepository.getAccountTypeDtoByMnemonic(mnemonic);
            } catch (Exception e) {

                throw new RuntimeException("Unable to retrieve from DB.", e);
            }
        }*/


}