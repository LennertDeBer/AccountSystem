package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.AccountMemberDto;

import java.time.LocalDate;
import java.util.List;

public interface AccountMemberTranslator {
    AccountMemberDto create(AccountMemberDto accountMember);

    List<AccountMemberDto> getAllAccountMembers();


    AccountMemberDto increaseAccountMemberBalance(String userName, Double amount, LocalDate now);

    AccountMemberDto getAccountMemberByUsernameNativeQuery(String userName);

    AccountMemberDto decreaseAccountMemberBalance(String userName, Double amount, LocalDate newCreationDate);
}
