package za.ac.nwu.as.logic.flow.impl.acmeb;

import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountMemberDto;
import za.ac.nwu.as.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.as.logic.flow.FetchAccountMemberFlow;
import za.ac.nwu.as.logic.flow.ModifyAccountMemberFlow;
import za.ac.nwu.as.translator.AccountMemberTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;


@Transactional
@Component
public class ModifyAccountMemberFlowImpl implements ModifyAccountMemberFlow {
    private final AccountMemberTranslator accountMemberTranslator;
            private final FetchAccountMemberFlow fetchAccountMemberFlow;
            private final CreateAccountTransactionFlow createAccountTransactionFlow;
    public ModifyAccountMemberFlowImpl(AccountMemberTranslator accountMemberTranslator,FetchAccountMemberFlow fetchAccountMemberFlow,CreateAccountTransactionFlow createAccountTransactionFlow) {
        this.accountMemberTranslator = accountMemberTranslator;
        this.fetchAccountMemberFlow = fetchAccountMemberFlow;
        this.createAccountTransactionFlow = createAccountTransactionFlow;
    }

    @Override
    public AccountMemberDto increaseAccountMemberBalance(String userName, Double amount, LocalDate newCreationDate) {

       /* AccountType accountType =new AccountType(Long.valueOf(1),"MILES","Miles", LocalDate.parse("2020-01-01"));
        AccountMemberDto memberDto =  accountMemberTranslator.getAccountMemberByUsernameNativeQuery(userName);
        AccountMember member = memberDto.getAccountMember();*/
        if (null == newCreationDate) {
            newCreationDate =  LocalDate.now();
        }
      /*  AccountTransactionDto dt = new AccountTransactionDto(Long.valueOf(1),accountType.getMnemonic(),accountType.getAccountTypeID(),member.getMemberUsername(), member.getMemberID(), amount,newCreationDate);

        createAccountTransactionFlow.create(dt);
        */return accountMemberTranslator.increaseAccountMemberBalance(userName, amount, newCreationDate);
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
            if(accountMemberTranslator.getAccountMemberByUsernameNativeQuery(userName).getAccountBalance()<amount)
            {
                amount =0.00;
                }
        return accountMemberTranslator.decreaseAccountMemberBalance(userName, amount, newCreationDate);

        /*}
        finally{


        }*/
    }
}
