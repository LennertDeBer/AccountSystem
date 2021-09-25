package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.dto.AccountMemberDto;

import java.time.LocalDate;

public interface ModifyAccountMemberFlow {
    AccountMemberDto increaseAccountMemberBalance(String userName, Double amount, LocalDate newCreationDate);

    AccountMemberDto decreaseAccountMemberBalance(String userName, Double amount, LocalDate newCreationDate);
}
