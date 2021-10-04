package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.as.domain.persistence.AccountMember;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.domain.persistence.AccountType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "AccountTransaction",
        description = "A DTO represents the AccountTransaction"
)
public class AccountTransactionDto implements Serializable {
    private static final long serialVersionUID = -2104023588857202326L;
    private Long transactionId;
    //foreign key to accountType table
    private String accountTypeMnemonic;
    private Long typeId;
    //foreign key to accountType table
    private String accountMemberUsername;
    private Long memberId;

    private Double amount;


    private LocalDate transactionDate;







    public AccountTransactionDto(Long transactionId, String accountTypeMnemonic, Long typeId, String accountMemberUsername, Long memberId, Double amount, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.accountTypeMnemonic = accountTypeMnemonic;
        this.typeId = typeId;
        this.accountMemberUsername = accountMemberUsername;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public AccountTransactionDto(Long transactionId, AccountType accountType, AccountMember accountMember, Double amount, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.accountTypeMnemonic = accountType.getMnemonic();
        this.typeId = accountType.getAccountTypeID();
        this.accountMemberUsername = accountMember.getMemberUsername();
        this.memberId = accountMember.getMemberID();
        this.amount = amount;
        this.transactionDate = transactionDate;


    }
    public AccountTransactionDto(AccountTransactionDto accountTransactionDto) {
        this.transactionId = accountTransactionDto.getTransactionId();
        this.accountTypeMnemonic = accountTransactionDto.getAccountTypeMnemonic();
        this.typeId = accountTransactionDto.getTypeId();
        this.accountMemberUsername = accountTransactionDto.getAccountMemberUsername();
        this.memberId = accountTransactionDto.getMemberId();
        this.amount = accountTransactionDto.getAmount();
        this.transactionDate = accountTransactionDto.getTransactionDate();

    }







    /*public AccountTransactionDto(String accountTypeMnemonic, String accountMemberUsername, Double amount, LocalDate transactionDate) {
        this.setAccountTypeMnemonic(accountTypeMnemonic);
        this.setAccountMemberUsername(accountMemberUsername);
        this.setAmount(amount);
        this.setTransactionDate(transactionDate);
    }

    public AccountTransactionDto(String accountTypeMnemonic, String accountMemberUsername, Double amount) {

        this.setAccountTypeMnemonic(accountTypeMnemonic);
        this.setAccountMemberUsername(accountMemberUsername);
        this.setAmount(amount);

    }*/
    public AccountTransactionDto() {
    }

    public AccountTransactionDto(AccountTransaction accountTransaction) {
        this.transactionId = accountTransaction.getTransactionId();
        this.accountTypeMnemonic =  accountTransaction.getAccountType().getMnemonic();
        this.typeId =accountTransaction.getAccountType().getAccountTypeID();
        this.accountMemberUsername =  accountTransaction.getMember().getMemberUsername();
        this.memberId = accountTransaction.getMember().getMemberID();
        this.amount =  accountTransaction.getAmount();
        this.transactionDate =  accountTransaction.getTransactionDate();
    }
    @ApiModelProperty(position = 1,
            value = "AccountTransaction ID",
            name = "TransactionId",
            notes = "The primary key used in the transaction",
            dataType = "Java.lang.LONG",
            example = "1",
            required = true)
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @ApiModelProperty(position = 2,
            value = "AccountTransaction AccountTransaction",
            name = "AccountTransaction",
            notes = "The primary key used in the AccountTransaction",
            dataType = "Java.lang.Long",
            example = "1",
            required = true)
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    @ApiModelProperty(position = 3,
            value = "AccountTransaction MemberId",
            name = "MemberId",
            notes = "The primary key used in the member",
            dataType = "Java.lang.LONG",
            example = "1",
            required = true)
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ApiModelProperty(position = 4,
            value = "AccountTransaction Amount",
            name = "Amount",
            notes = "The amount that was used in the transaction",
            dataType = "Java.lang.Double",
            example = "30.55",
            required = true)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @ApiModelProperty(position = 5,
            value = "AccountTransaction date of the transaction",
            name = "TransactionDate",
            notes = "This is the date on which the transaction took place",
            dataType = "Java.lang.String",
            example = "2021-01-01",
            allowEmptyValue = true
    )
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    @ApiModelProperty(position = 6,
            value = "AccountTransaction Mnemonic",
            name = "Mnemonic",
            notes = "This currency of which was used in transaction",
            dataType = "Java.lang.String",
            example = "MILES",
            allowEmptyValue = true
    )
    public String getAccountTypeMnemonic() {
        return accountTypeMnemonic;
    }

    public void setAccountTypeMnemonic(String accountTypeMnemonic) {
        this.accountTypeMnemonic = accountTypeMnemonic;
    }
    @ApiModelProperty(position = 7,
            value = "AccountTransaction of member",
            name = "MemberUsername",
            notes = "The transaction was made by this member",
            dataType = "Java.lang.String",
            example = "MIKE",
            allowEmptyValue = true
    )
    public String getAccountMemberUsername() {
        return accountMemberUsername;
    }

    public void setAccountMemberUsername(String accountMemberUsername) {
        this.accountMemberUsername = accountMemberUsername;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        if(null == transactionDate)
        {
            transactionDate = LocalDate.now();
        }
        this.transactionDate = transactionDate;
    }



    @JsonIgnore
    public AccountType getAccountType(Long id ,String mnemonic) {
        return new AccountType(id, mnemonic);

    }


    @JsonIgnore
    public AccountMember getMember(Long id ,String username) {
        return new AccountMember(id, username);
    }

    @JsonIgnore
    public static AccountTransaction getAccountTransaction(AccountTransactionDto accountTransactionDto)
    {
        return new AccountTransaction(accountTransactionDto.getTransactionId(),
                accountTransactionDto.getAccountType(accountTransactionDto.getTypeId(),accountTransactionDto.getAccountTypeMnemonic()),
                accountTransactionDto.getMember(accountTransactionDto.getMemberId(),accountTransactionDto.getAccountMemberUsername()),
                accountTransactionDto.getAmount(),accountTransactionDto.getTransactionDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionDto that = (AccountTransactionDto) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(accountTypeMnemonic, that.accountTypeMnemonic) && Objects.equals(typeId, that.typeId) && Objects.equals(accountMemberUsername, that.accountMemberUsername) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, accountTypeMnemonic, typeId, accountMemberUsername, memberId, amount, transactionDate);
    }


    @JsonIgnore
    public AccountTransaction buildAccountTransaction(AccountType accountType, AccountMember accountMember) {
       // return new AccountTransaction(this.getTransactionId(), accountType, accountMember, this.getAmount(), this.getTransactionDate());

        return new AccountTransaction(this.getTransactionId(), accountType, accountMember, this.getAmount(), this.getTransactionDate());
    }

    @Override
    public String toString() {
        return "AccountTransactionDto{" +
                "transactionId=" + transactionId +
                ", accountTypeMnemonic='" + accountTypeMnemonic + '\'' +
                ", typeId=" + typeId +
                ", accountMemberUsername='" + accountMemberUsername + '\'' +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
