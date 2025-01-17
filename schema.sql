create table studenttable(
                             username VARCHAR(100) PRIMARY KEY ,
                            password VARCHAR(100) NOT NULL ,
                            name VARCHAR(100) NOT NULL
);

create table admintable(
    username VARCHAR(100) ,
    password VARCHAR(100)
);

create table questiontable(
    question varchar(100),
    a varchar(100),
    b varchar(100),
    c varchar(100),
    d varchar(100),
    answer varchar(100)
);

create table instructiontable(
                                 instruction varchar(100)
)