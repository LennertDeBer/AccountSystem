package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.dto.AccountMemberDto;
import za.ac.nwu.as.domain.persistence.AccountMember;

import java.util.List;

public interface FetchAccountMemberFlow {
    List<AccountMemberDto> getAllAccountMembers();

    AccountMemberDto getAccountMemberByUsername(String userName);

    AccountMember getAccountMemberDbEntityByUsername(String accountMemberUsername);
}
