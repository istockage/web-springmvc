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
    co_no                   tinyint,
    co_name                 nvarchar(20) not null,
    primary key (co_id),
    foreign key (co_cc_id) references code_category (cc_id)
);

-- Admin
create table broker_head (
    bh_id                   int auto_increment not null,
    bh_no                   char(4) not null,
    bh_name                 nvarchar(20) not null,
    bh_update_time          datetime not null,
    primary key (bh_id)
);

create table broker_branch (
    bb_id                   int auto_increment not null,
    bb_bh_id                int not null,
    bb_no                   char(4) not null,
    bb_name                 nvarchar(10) not null,
    bb_update_time          datetime not null,
    primary key (bb_id),
    foreign key (bb_bh_id) references broker_head (bh_id)
);

-- User
create table member (
    me_id                   int auto_increment not null,
    me_no                   char(10) not null,
    me_email                varchar(50) not null,
    me_password             char(32) not null,
    me_salt                 char(36) not null,
    me_random               char(6),
    me_lastname             nvarchar(20),
    me_firstname            nvarchar(20),
    me_activity_code        tinyint not null,
    me_signup_time          datetime not null,
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

-- INSERT
-- System
-- path_category
insert into path_category (pc_name, pc_extension) values ('view', '');
insert into path_category (pc_name, pc_extension) values ('action', 'do');
insert into path_category (pc_name, pc_extension) values ('ajax', 'ajax');

-- User
-- user_path
insert into user_path (up_pc_id, up_name, up_path) values (1, '找不到網頁', 'error/page-not-found');
insert into user_path (up_pc_id, up_name, up_path) values (1, '首頁', 'index');
insert into user_path (up_pc_id, up_name, up_path) values (1, '忘記密碼', 'secure/forget-password');
insert into user_path (up_pc_id, up_name, up_path) values (2, '忘記密碼', 'secure/forget-password.do');
insert into user_path (up_pc_id, up_name, up_path) values (1, '重設密碼', 'secure/reset-password');
insert into user_path (up_pc_id, up_name, up_path) values (2, '重設密碼', 'secure/reset-password.do');
insert into user_path (up_pc_id, up_name, up_path) values (1, '登入', 'secure/sign-in');
insert into user_path (up_pc_id, up_name, up_path) values (2, '登入', 'secure/sign-in.do');
insert into user_path (up_pc_id, up_name, up_path) values (2, '登出', 'secure/sign-out.do');
insert into user_path (up_pc_id, up_name, up_path) values (1, '註冊', 'secure/sign-up');
insert into user_path (up_pc_id, up_name, up_path) values (2, '註冊', 'secure/sign-up.do');
insert into user_path (up_pc_id, up_name, up_path) values (2, '啟用帳號', 'secure/sign-up-activity.do');
insert into user_path (up_pc_id, up_name, up_path) values (3, '信箱重複驗證', 'secure/sign-up-email-repeat.ajax');
insert into user_path (up_pc_id, up_name, up_path) values (1, '發送確認信', 'secure/sign-up-mail');
insert into user_path (up_pc_id, up_name, up_path) values (2, '發送確認信', 'secure/sign-up-mail.do');
insert into user_path (up_pc_id, up_name, up_path) values (1, '重新發送確認信', 'secure/sign-up-mail-again');
insert into user_path (up_pc_id, up_name, up_path) values (2, '重新發送確認信', 'secure/sign-up-mail-again.do');