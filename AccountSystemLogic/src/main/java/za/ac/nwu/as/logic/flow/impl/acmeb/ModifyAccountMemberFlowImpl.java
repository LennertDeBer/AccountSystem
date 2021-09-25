package za.ac.nwu.as.logic.flow.impl.acmeb;

import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountMemberDto;
import za.ac.nwu.as.logic.flow.ModifyAccountMemberFlow;
import za.ac.nwu.as.translator.AccountMemberTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;


@Transactional
@Component
public class ModifyAccountMemberFlowImpl implements ModifyAccountMemberFlow {
    private final AccountMemberTranslator accountMemberTranslator;

    public ModifyAccountMemberFlowImpl(AccountMemberTranslator accountMemberTranslator) {
        this.accountMemberTranslator = accountMemberTranslator;
    }

    @Override
    public AccountMemberDto increaseAccountMemberBalance(String userName, Double amount, LocalDate newCreationDate) {

        if (null == newCreationDate) {
            return accountMemberTranslator.increaseAccountMemberBalance(userName, amount, LocalDate.now());
        }
        return accountMemberTranslator.increaseAccountMemberBalance(userName, amount, newCreationDate);
    }

    @Override
    public AccountMemberDto decreaseAccountMemberBalance(String userName, Double amount, LocalDate newCreationDate) {
        /*try {
            return accountMemberTranslator.decreaseAccountMemberBalance(userName, amount, newCreationDate);
        /*}
        catch (RuntimeException e) {*/
            if (null == newCreationDate) {
                return accountMemberTranslator.decreaseAccountMemberBalance(userName, amount, LocalDate.now());
            }
        return accountMemberTranslator.decreaseAccountMemberBalance(userName, amount, newCreationDate);

        /*}
        finally{


        }*/
    }
}
