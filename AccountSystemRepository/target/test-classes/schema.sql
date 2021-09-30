CREATE SCHEMA LENNERT;

CREATE sequence LENNERT.AC_TYPE_GENERIC_SEQ START WITH 50002 increment BY 1;
CREATE sequence LENNERT.MEM_AC_GENERIC_SEQ START WITH 1 increment BY 1;
CREATE sequence LENNERT.AC_TX_GENERIC_SEQ START WITH 1 increment BY 1;

CREATE TABLE LENNERT.ACCOUNT_TYPE(
ACCOUNT_TYPE_ID BIGINT not null,
MNEMONIC VARCHAR(50) not null,
ACCOUNT_TYPE_NAME VARCHAR(50) NOT NULL,
CREATION_DATE DATE NOT NULL,
 primary key (ACCOUNT_TYPE_ID)
);


create table LENNERT.MEMBER_ACCOUNT(
MEMBER_ID BIGINT NOT NULL,
MEMBER_Username VARCHAR2(20) NOT NULL,
primary key (MEMBER_ID)
);


create table LENNERT.ACCOUNT_TX(
TX_ID BIGINT not null,
ACCOUNT_TYPE_ID BIGINT not null,
MEMBER_ID BIGINT NOT NULL,
AMOUNT number(9,2) not null,
TX_DATE DATE NOT NULL,
primary key (TX_ID),
FOREIGN KEY (ACCOUNT_TYPE_ID)REFERENCES LENNERT.ACCOUNT_TYPE(ACCOUNT_TYPE_ID),
FOREIGN KEY (MEMBER_ID)REFERENCES LENNERT.MEMBER_ACCOUNT(MEMBER_ID)
);


