package za.ac.nwu.as.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "ACCOUNT_TX",schema = "LENNERT")
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = 8177408680279093323L;


    private Long transactionId;

    //foreign key to accountType table
    private AccountType accountType;

    //foreign key to accountType table
    private AccountMember member;


    private Double amount;


    private LocalDate transactionDate;

    public AccountTransaction(Long transactionId, AccountType accountType, AccountMember memberId, Double amount, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.accountType = accountType;
        this.member = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public AccountTransaction(AccountType accountType, AccountMember memberId, Double amount, LocalDate transactionDate) {
        this.accountType = accountType;
        this.member = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public AccountTransaction() {
    }

    public AccountTransaction(AccountTransaction accountTransaction) {
        this.transactionId = accountTransaction.getTransactionId();
        this.accountType = accountTransaction.getAccountType();
        this.member = accountTransaction.getMember();
        this.amount = accountTransaction.getAmount();
        this.transactionDate = accountTransaction.getTransactionDate();
    }




    @Id
    @SequenceGenerator(name = "AC_TX_GENERIC_SEQ", sequenceName = "LENNERT.AC_TX_GENERIC_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "AC_TX_GENERIC_SEQ")
    @Column(name="TX_ID")
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_TYPE_ID")
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    public AccountMember getMember() {
        return member;
    }

    public void setMember(AccountMember memberId) {
        this.member = memberId;
    }
    @Column(name="AMOUNT")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    @Column(name="TX_DATE")
    public LocalDate getTransactionDate() {
        return transactionDate;
    }



    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(accountType, that.accountType) && Objects.equals(member, that.member) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, accountType, member, amount, transactionDate);
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transactionId=" + transactionId +
                ", accountType=" + accountType +
                ", memberId=" + member +
                ", amount=" + String.format("%.2f",amount) +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
