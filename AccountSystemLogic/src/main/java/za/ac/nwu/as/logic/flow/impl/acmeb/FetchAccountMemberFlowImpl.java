package za.ac.nwu.as.logic.flow.impl.acmeb;

import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountMemberDto;
import za.ac.nwu.as.domain.persistence.AccountMember;
import za.ac.nwu.as.logic.flow.FetchAccountMemberFlow;
import za.ac.nwu.as.translator.AccountMemberTranslator;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class FetchAccountMemberFlowImpl implements FetchAccountMemberFlow {
    private AccountMemberTranslator accountMemberTranslator;

    public FetchAccountMemberFlowImpl(AccountMemberTranslator accountMemberTranslator) {
        this.accountMemberTranslator = accountMemberTranslator;
    }

    @Override
    public List<AccountMemberDto> getAllAccountMembers() {
        return accountMemberTranslator.getAllAccountMembers();
    }

    @Override
    public AccountMemberDto getAccountMemberByUsername(String userName) {
        return accountMemberTranslator.getAccountMemberByUsernameNativeQuery(userName);
    }

    @Override
    public AccountMember getAccountMemberDbEntityByUsername(String accountMemberUsername) {
        AccountMemberDto ad = accountMemberTranslator.getAccountMemberByUsernameNativeQuery(accountMemberUsername);
        AccountMember mem = ad.getAccountMember();
        return mem;
    }
}
