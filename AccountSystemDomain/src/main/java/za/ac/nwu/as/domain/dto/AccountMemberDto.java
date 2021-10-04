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

    public AccountMemberDto(Long memberId, String memberUsername) {
        this.memberId = memberId;
        this.memberUsername = memberUsername;

    }

    private String memberUsername;


    public AccountMemberDto() {
    }

    public AccountMemberDto(String memberUsername) {

        this.memberUsername = memberUsername;

    }
    public AccountMemberDto(AccountMember accountMember) {
        this.setMemberId(accountMember.getMemberID());
        this.setMemberUsername(accountMember.getMemberUsername());
    }
    @ApiModelProperty(position = 1,
            value = "AccountMember member id",
            name = "MemberId",
            notes = "Uniquely identities the account member",
            dataType = "Java.lang.Long",
            example = "1",
            required = true)
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ApiModelProperty(position = 2,
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
    /*@ApiModelProperty(position = 3,
            value = "AccountMember AccountBalance",
            name = "AccountBalance",
            notes = "Uniquely identities the account member",
            dataType = "Java.lang.Double",
            example = "50.55",
            required = true)*/


    @JsonIgnore
    public AccountMember getAccountMember()
    {
        return new AccountMember(this.getMemberId(), this.getMemberUsername());
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
        return Objects.equals(memberUsername, that.memberUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberUsername);
    }

    @Override
    public String toString() {
        return "AccountMemberDto{" +
                "memberId=" + memberId +
                ",  memberUsername='" + memberUsername+"'"+
                '}';
    }
}
