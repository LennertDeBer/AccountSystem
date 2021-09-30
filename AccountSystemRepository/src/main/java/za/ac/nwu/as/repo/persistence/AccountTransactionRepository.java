package za.ac.nwu.as.repo.persistence;


import  org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.persistence.AccountTransaction;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransaction, Long> {

    @Query(value = "SELECT "+
            "   SUM(AMOUNT)" +
            "   FROM "+
            "   LENNERT.ACCOUNT_TX "+
            "   GROUP BY MEMBER_ID "+
            "   HAVING MEMBER_ID = :id",nativeQuery = true)
    Double getAccountTransactionByIdNativeQueryD(Long id);

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
