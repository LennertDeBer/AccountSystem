package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.as.domain.persistence.AccountMember;

import java.io.Serializable;
import java.util.Objects;

@ApiModel(value = "AccountMember",
        description = "A DTO represents the AccountMember"
)
public class AccountMemberDto implements Serializable {
    private static final long serialVersionUID = 1268247002055781833L;

    private Long memberId;

    public AccountMemberDto(Long memberId, String memberUsername, Double accountBalance) {
        this.memberId = memberId;
        this.memberUsername = memberUsername;
        this.accountBalance = accountBalance;
    }

    private String memberUsername;
    private Double accountBalance;

    public AccountMemberDto() {
    }

    public AccountMemberDto(String memberUsername, Double accountBalance) {

        this.memberUsername = memberUsername;
        this.accountBalance = accountBalance;
    }
    public AccountMemberDto(AccountMember accountMember) {
        this.setAccountBalance(accountMember.getAccountBalance());
        this.setMemberUsername(accountMember.getMemberUsername());
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ApiModelProperty(position = 1,
            value = "AccountMember MemberUsername",
            name = "MemberUsername",
            notes = "Uniquely identities the account member",
            dataType = "Java.lang.String",
            example = "MIKE",
            required = true)
    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }
    @ApiModelProperty(position = 2,
            value = "AccountMember AccountBalance",
            name = "AccountBalance",
            notes = "Uniquely identities the account member",
            dataType = "Java.lang.Double",
            example = "50.55",
            required = true)
    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }


    @JsonIgnore
    public AccountMember getAccountMember()
    {
        return new AccountMember(getMemberUsername(), getAccountBalance());
    }
    @JsonIgnore
    public AccountMember getAccountMember(Long memberId,String username)
    {
        return new AccountMember(memberId, username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountMemberDto that = (AccountMemberDto) o;
        return Objects.equals(memberUsername, that.memberUsername) && Objects.equals(accountBalance, that.accountBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberUsername, accountBalance);
    }

    @Override
    public String toString() {
        return "AccountMemberDto{" +
                "memberId=" + memberId +
                ",  memberUsername='" + memberUsername + '\'' +
                ", accountBalance=" + String.format("%.2f",accountBalance) +
                '}';
    }
}
