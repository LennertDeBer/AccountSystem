package za.ac.nwu.as.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MEMBER_ACCOUNT",schema = "LENNERT")
public class AccountMember implements Serializable {

    private static final long serialVersionUID = 5023554932355705625L;
    private Long memberID;
    private String memberUsername;
    private Set<AccountTransaction> accountTransactions;


    public AccountMember() {
    }

    public AccountMember(Long memberID, String memberUsername) {
        this.memberID = memberID;
        this.memberUsername = memberUsername;

    }

    public AccountMember(String memberUsername) {
        this.memberUsername = memberUsername;
    }


    @Id
    @SequenceGenerator(name = "MEM_AC_GENERIC_SEQ", sequenceName = "LENNERT.MEM_AC_GENERIC_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MEM_AC_GENERIC_SEQ")
    @Column(name = "MEMBER_ID")
    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }
    @Column(name = "MEMBER_Username")
    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }



    @OneToMany(targetEntity = AccountTransaction.class,fetch = FetchType.LAZY,mappedBy = "member",orphanRemoval = true,cascade = CascadeType.ALL)
    public Set<AccountTransaction> getAccountTransactions() {
        return accountTransactions;
    }

    public void setAccountTransactions(Set<AccountTransaction> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountMember that = (AccountMember) o;
        return Objects.equals(memberID, that.memberID) && Objects.equals(memberUsername, that.memberUsername) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberID, memberUsername);
    }

    @Override
    public String toString() {
        return "AccountMember{" +
                "memberID=" + memberID +
                ", memberUsername='" + memberUsername+"'"+
                '}';
    }
}
