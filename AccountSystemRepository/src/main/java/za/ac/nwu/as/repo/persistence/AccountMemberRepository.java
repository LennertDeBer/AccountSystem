package za.ac.nwu.as.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.persistence.AccountMember;

@Repository
public interface AccountMemberRepository extends JpaRepository<AccountMember, Long> {
    @Query(value = "SELECT "+
            "   MEMBER_ID,"+
            "   MEMBER_Username,"+
            "   ACCOUNT_BALANCE" +
            "   FROM "+
            "   LENNERT.MEMBER_ACCOUNT "+
            "   WHERE MEMBER_Username = :userName ",nativeQuery = true)
    AccountMember getAccountMemberByUsernameNativeQuery(String userName);
}
