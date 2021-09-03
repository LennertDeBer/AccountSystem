package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.as.domain.persistence.AccountType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "AccountType",
          description = "A DTO represents the AccountType"
)
public class AccountTypeDto implements Serializable {
    private static final long serialVersionUID = -534685320648028968L;
    private String mnemonic;
    private String accountTypeName;
    private LocalDate createDate;

    public AccountTypeDto() {
    }

    public AccountTypeDto(String mnemonic, String accountTypeName, LocalDate createDate) {
        this.mnemonic = mnemonic;
        this.accountTypeName = accountTypeName;
        this.createDate = createDate;
    }

    public AccountTypeDto(AccountType accountType) {
        this.setAccountTypeName(accountType.getAccountTypeName());
        this.setCreationDate(accountType.getCreationDate());
        this.setMnemonic(accountType.getMnemonic());
    }

    @ApiModelProperty(position = 1,
            value = "AccountType Mnemonic",
            name = "Mnemonic",
            notes = "Uniquely identities the account type",
            dataType = "Java.lang.String",
            example = "MILES",
            required = true)
    public String getMnemonic(){return mnemonic;}

    public void setMnemonic(String mnemonic){this.mnemonic = mnemonic;}

    @ApiModelProperty(position = 2,
            value = "AccountType Name",
            name = "Name",
            notes = "The name of the account type",
            dataType = "Java.lang.String",
            example = "Miles",
            required = true)
    public String getAccountTypeName(){return accountTypeName;}

    public void setAccountTypeName(String accountTypeName){this.accountTypeName = accountTypeName;}

    @ApiModelProperty(position = 3,
            value = "AccountType Creation Date",
            name = "CreationDate",
            notes = "This is the date on which the AccountType was created",
            dataType = "Java.lang.String",
            example = "2020-01-01",
            allowEmptyValue = true
    )
    public LocalDate getCreationDate(){return createDate;}

    public void setCreationDate(LocalDate createDate){this.createDate = createDate;}

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        AccountTypeDto that = (AccountTypeDto) o;
        return Objects.equals(mnemonic, that.mnemonic) && Objects.equals(accountTypeName, that.accountTypeName) && Objects.equals(createDate, that.createDate);
     }

     @JsonIgnore
    public AccountType getAccountType()
     {
         return new AccountType(getMnemonic(), getAccountTypeName(), getCreationDate());
     }

     @Override
    public int hashCode(){ return Objects.hash(mnemonic, accountTypeName, createDate);}

    @Override
    public String toString(){
        return "AccountTypeDto{"+
                "mnenomic ='" + mnemonic + '\''
                +", accountTypeName ='" + accountTypeName +'\''
                +", creationDate =" + createDate
                + '}';
    }
}
