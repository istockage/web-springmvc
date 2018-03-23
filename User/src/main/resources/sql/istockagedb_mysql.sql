-- MySQL

drop database if exists istockagedb;

create database if not exists istockagedb;

use istockagedb;

-- CREATE
-- System
create table path_category (
    pc_id                   int auto_increment not null,
    pc_name                 varchar(10) not null,
    pc_extension            varchar(10) not null,
    primary key (pc_id)
);

create table admin_path (
    ap_id                   int auto_increment not null,
    ap_pc_id                int not null,
    ap_name                 nvarchar(20) not null,
    ap_path                 varchar(50) not null,
    primary key (ap_id),
    foreign key (ap_pc_id) references path_category (pc_id)
);

create table user_path (
    up_id                   int auto_increment not null,
    up_pc_id                int not null,
    up_name                 nvarchar(20) not null,
    up_path                 varchar(50) not null,
    primary key (up_id),
    foreign key (up_pc_id) references path_category (pc_id)
);

create table code_category (
    cc_id                   int auto_increment not null,
    cc_name                 nvarchar(20) not null,
    primary key (cc_id)
);

create table code (
    co_id                   int auto_increment not null,
    co_cc_id                int not null,
    co_name                 nvarchar(20) not null,
    co_no                   tinyint,
    primary key (co_id),
    foreign key (co_cc_id) references code_category (cc_id)
);

-- Admin
create table broker_head (
    bh_id                   int auto_increment not null,
    bh_name                 nvarchar(20) not null,
    bh_no                   char(4) not null,
    bh_update_time          datetime not null,
    primary key (bh_id)
);

create table broker_branch (
    bb_id                   int auto_increment not null,
    bb_bh_id                int not null,
    bb_name                 nvarchar(10) not null,
    bb_no                   char(4) not null,
    bb_update_time          datetime not null,
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
    me_signup_time          datetime not null,
    me_signin_number        int not null,
    me_update_info_time     datetime not null,
    me_update_pwd_time      datetime not null,
    primary key (me_id)
);

create table member_log (
    ml_id                   int auto_increment not null,
    ml_insert_time          timestamp default current_timestamp not null,
    ml_me_id                int not null,
    ml_up_id                int not null,
    ml_ip                   varchar(20),
    primary key (ml_id),
    foreign key (ml_me_id) references member (me_id),
    foreign key (ml_up_id) references user_path (up_id)
);

create table account (
    ac_id                   int auto_increment not null,
    ac_me_id                int not null,
    ac_bb_id                int not null,
    ac_no                   char(7) not null,
    ac_discount             tinyint not null,
    ac_update_time          datetime not null,
    primary key (ac_id),
    foreign key (ac_me_id) references member (me_id),
    foreign key (ac_bb_id) references broker_branch (bb_id)
);

create table stock (
    st_id                   int auto_increment not null,
    st_ac_id                int not null,
    st_name                 nvarchar(10) not null,
    st_no                   varchar(10) not null,
    st_type_code            tinyint,
    st_buy_time             datetime,
    st_buy_price            float,
    st_buy_share            int,
    st_buy_discount         tinyint,
    st_buy_fee              mediumint,
    st_buy_cost             int,
    st_sell_time            datetime,
    st_sell_price           float,
    st_sell_share           int,
    st_sell_discount        tinyint,
    st_sell_fee             mediumint,
    st_sell_tax             mediumint,
    st_sell_revenue         int,
    primary key (st_id),
    foreign key (st_ac_id) references account (ac_id)
);