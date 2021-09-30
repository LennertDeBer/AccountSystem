package za.ac.nwu.as.translator.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountMemberDto;
import za.ac.nwu.as.domain.persistence.AccountMember;
import za.ac.nwu.as.repo.persistence.AccountMemberRepository;
import za.ac.nwu.as.translator.AccountMemberTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMemberTranslatorImpl implements AccountMemberTranslator {
    private final AccountMemberRepository accountMemberRepository;

    public AccountMemberTranslatorImpl(AccountMemberRepository accountMemberRepository) {
        this.accountMemberRepository = accountMemberRepository;
    }

    @Override
    public AccountMemberDto create(AccountMemberDto accountMemberDto) {
        try {
            AccountMember accountMember = accountMemberRepository.save(accountMemberDto.getAccountMember());
            return new AccountMemberDto(accountMember);
        } catch (Exception e) {
            throw new RuntimeException("Unable to save to DB.", e);
        }
    }

    @Override
    public List<AccountMemberDto> getAllAccountMembers() {
        List<AccountMemberDto> accountMemberDtos = new ArrayList<>();
        try {
            for (AccountMember accountMember : accountMemberRepository.findAll()) {
                accountMemberDtos.add(new AccountMemberDto(accountMember));
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to read form DB.", e);
        }
        return accountMemberDtos;
    }

    @Override
    public AccountMemberDto getAccountMemberByUsernameNativeQuery(String userName) {

        try {
            AccountMember accountMember = accountMemberRepository.getAccountMemberByUsernameNativeQuery(userName);
            AccountMemberDto accountMemberDtos = new AccountMemberDto(accountMember);
            return accountMemberDtos;

        } catch (Exception e) {
            throw new RuntimeException("Unable to read form DB.", e);
        }

    }


}
