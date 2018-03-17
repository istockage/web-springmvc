-- MySQL

drop database if exists istockagedb;

create database if not exists istockagedb;

use istockagedb;

-- CREATE
-- Admin
create table broker_head (
    bh_id                   int auto_increment not null,
    bh_name                 nvarchar(20) not null,
    bh_no                   char(4) not null,
    primary key (bh_id)
);

create table broker_branch (
    bb_id                   int auto_increment not null,
    bb_bh_id                int not null,
    bb_name                 nvarchar(10) not null,
    bb_no                   char(4) not null,
    primary key (bb_id),
    foreign key (bb_bh_id) references broker_head (bh_id)
);

-- User
create table member (
    me_id                   int auto_increment not null,
    me_email                varchar(50) not null,
    me_password             varchar(50) not null,
    me_salt                 varchar(50) not null,
    me_lastname             nvarchar(20),
    me_firstname            nvarchar(20),
    primary key (me_id)
);

create table account (
    ac_id                   int auto_increment not null,
    ac_me_id                int not null,
    ac_bb_id                int not null,
    primary key (ac_id),
    foreign key (ac_me_id) references member (me_id),
    foreign key (ac_bb_id) references broker_branch (bb_id)
);

create table stock (
    st_id                   int auto_increment not null,
    st_ac_id                int not null,
    st_name                 nvarchar(10) not null,
    st_no                   varchar(10) not null,
    primary key (st_id),
    foreign key (st_ac_id) references account (ac_id)
);