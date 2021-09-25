package za.ac.nwu.as.logic.flow.impl.acmeb;

import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountMemberDto;
import za.ac.nwu.as.logic.flow.CreateAccountMemberFlow;
import za.ac.nwu.as.translator.AccountMemberTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class CreateAccountMemberFlowImpl implements CreateAccountMemberFlow {
    private AccountMemberTranslator accountMemberTranslator;

    public CreateAccountMemberFlowImpl(AccountMemberTranslator accountMemberTranslator) {
        this.accountMemberTranslator = accountMemberTranslator;
    }

    @Override
    public AccountMemberDto create(AccountMemberDto accountMember) {

        return accountMemberTranslator.create(accountMember);
    }
}
