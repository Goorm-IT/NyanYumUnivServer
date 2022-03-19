create database nyu;
use nyu;


CREATE TABLE user(
                     uid VARCHAR(50) primary key ,
                     nickname VARCHAR(50),
                     userlevel VARCHAR(20),
                     postId VARCHAR(20)
);

create TABLE store(
                      storeId varchar (50) primary key ,
                      address varchar (50),
                      score float,
                      comments varchar
);

create table menu(
                     storeId varchar(50),
                     menuAlias varchar(50),
                     cost int(255),
                     score float,
                     foreign key (storeId) references store(storeId)
);