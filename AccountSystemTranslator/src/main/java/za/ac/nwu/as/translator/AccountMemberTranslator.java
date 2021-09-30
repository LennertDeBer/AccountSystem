package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.AccountMemberDto;

import java.util.List;

public interface AccountMemberTranslator {
    AccountMemberDto create(AccountMemberDto accountMember);

    List<AccountMemberDto> getAllAccountMembers();


    AccountMemberDto getAccountMemberByUsernameNativeQuery(String userName);

}
