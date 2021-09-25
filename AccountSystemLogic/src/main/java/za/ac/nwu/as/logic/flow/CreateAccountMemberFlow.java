package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.dto.AccountMemberDto;

public interface CreateAccountMemberFlow {
    AccountMemberDto create(AccountMemberDto accountMember);
}
