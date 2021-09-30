package za.ac.nwu.as.repo.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.persistence.AccountTransaction;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {
/*
    @Query(value = "SELECT "+
            "   TX_ID,"+
            "   ACCOUNT_TYPE_ID,"+
            "   MEMBER_ID," +
            "   AMOUNT," +
            "   TX_DATE" +
            "   FROM "+
            "   LENNERT.ACCOUNT_TX "+
            "   GROUPED BY MEMBER_ID ORDER BY MEMBER_ID",nativeQuery = true)
    List<ICommentC> getAccountTransactionByIdNativeQuery(Long id);
*/
         @Query(value = "SELECT "+
            "   TX_ID,"+
            "   ACCOUNT_TYPE_ID,"+
            "   MEMBER_ID," +
            "   AMOUNT," +
            "   TX_DATE" +
            "   FROM "+
            "   LENNERT.ACCOUNT_TX "+
            "   WHERE TX_ID = :id",nativeQuery = true)
         AccountTransaction getAccountTransactionByIdNativeQuery(Long id);
}
