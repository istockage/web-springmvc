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
    ap_path                 varchar(100) not null,
    primary key (ap_id),
    foreign key (ap_pc_id) references path_category (pc_id)
);

create table user_path (
    up_id                   int auto_increment not null,
    up_pc_id                int not null,
    up_name                 nvarchar(20) not null,
    up_path                 varchar(100) not null,
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
create table securities_broker_head (
    sh_id                   int auto_increment not null,
    sh_no                   char(2) not null,
    sh_name                 nvarchar(10) not null,
    sh_update_time          datetime not null,
    primary key (sh_id)
);

create table securities_broker_branch (
    sb_id                   int auto_increment not null,
    sb_sh_id                int not null,
    sb_no                   char(2) not null,
    sb_name                 nvarchar(10) not null,
    sb_update_time          datetime not null,
    primary key (sb_id),
    foreign key (sb_sh_id) references securities_broker_head (sh_id)
);

create table futures_broker_head (
    fh_id                   int auto_increment not null,
    fh_no                   char(4) not null,
    fh_name                 nvarchar(10) not null,
    fh_update_time          datetime not null,
    primary key (fh_id)
);

create table futures_broker_branch (
    fb_id                   int auto_increment not null,
    fb_fh_id                int not null,
    fb_no                   char(3) not null,
    fb_name                 nvarchar(10) not null,
    fb_update_time          datetime not null,
    primary key (fb_id),
    foreign key (fb_fh_id) references futures_broker_head (fh_id)
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

create table securities_account (
    sa_id                   int auto_increment not null,
    sa_me_id                int not null,
    sa_sh_id                int not null,
    sa_sb_id                int not null,
    sa_no                   char(7) not null,
    sa_discount             tinyint,
    sa_times                int not null,
    sa_update_time          datetime not null,
    primary key (sa_id),
    foreign key (sa_me_id) references member (me_id),
    foreign key (sa_sh_id) references securities_broker_head (sh_id),
    foreign key (sa_sb_id) references securities_broker_branch (sb_id)
);

create table stock (
    st_id                   int auto_increment not null,
    st_sa_id                int not null,
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
    foreign key (st_sa_id) references securities_account (sa_id)
);

-- INSERT
-- System
-- path_category
insert into path_category (pc_name, pc_extension) values ('view', '');
insert into path_category (pc_name, pc_extension) values ('action', 'do');
insert into path_category (pc_name, pc_extension) values ('ajax', 'ajax');

-- Admin
-- securities_broker_head
insert into securities_broker_head (sh_no, sh_name, sh_update_time) values ('96', '富邦', now());
insert into securities_broker_head (sh_no, sh_name, sh_update_time) values ('92', '凱基', now());

-- securities_broker_branch
insert into securities_broker_branch (sb_sh_id, sb_no, sb_name, sb_update_time) values (1, '79', '延吉', now());
insert into securities_broker_branch (sb_sh_id, sb_no, sb_name, sb_update_time) values (2, '18', '大直', now());

-- User
-- member
insert into member (me_no, me_email, me_password, me_salt, me_random, me_lastname, me_firstname, me_activity_code, me_signup_time, me_update_info_time, me_update_pwd_time) values ('9203330360', 'chengjhan@gmail.com', '03a6de93ab7271375694231bf9eacc5b', '992cb6c0-c52e-4155-94c5-558251878998', null, null, null, 1, now(), now(), now());
insert into member (me_no, me_email, me_password, me_salt, me_random, me_lastname, me_firstname, me_activity_code, me_signup_time, me_update_info_time, me_update_pwd_time) values ('0568914095', 'chengjhan+1@gmail.com', '348f6a73c4a04d015e516ffddfde7432', '14bbce0f-78b8-4e83-822a-06b619f00758', null, null, null, 1, now(), now(), now());

-- securities_account
insert into securities_account (sa_me_id, sa_sh_id, sa_sb_id, sa_no, sa_discount, sa_times, sa_update_time) values (1, 1, 1, '0239889', 60, 0, now());
insert into securities_account (sa_me_id, sa_sh_id, sa_sb_id, sa_no, sa_discount, sa_times, sa_update_time) values (1, 2, 2, '0060626', 38, 0, now());
insert into securities_account (sa_me_id, sa_sh_id, sa_sb_id, sa_no, sa_discount, sa_times, sa_update_time) values (2, 1, 1, '1111111', 60, 0, now());

-- user_path
insert into user_path (up_pc_id, up_name, up_path) values (1, '找不到網頁', 'error/page-not-found');
insert into user_path (up_pc_id, up_name, up_path) values (1, '首頁', 'index');
insert into user_path (up_pc_id, up_name, up_path) values (1, '帳戶', 'member/profile');
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
insert into user_path (up_pc_id, up_name, up_path) values (1, '個人帳戶', 'settings/account');
insert into user_path (up_pc_id, up_name, up_path) values (2, '個人帳戶(變更密碼)', 'settings/account/change-password.do');
insert into user_path (up_pc_id, up_name, up_path) values (2, '個人帳戶(基本資料)', 'settings/account/info.do');
insert into user_path (up_pc_id, up_name, up_path) values (1, '證券帳戶', 'settings/securities-account');
insert into user_path (up_pc_id, up_name, up_path) values (1, '新增證券帳戶', 'settings/securities-account/add');
insert into user_path (up_pc_id, up_name, up_path) values (2, '新增證券帳戶', 'settings/securities-account/add.do');
insert into user_path (up_pc_id, up_name, up_path) values (1, '編輯證券帳戶', 'settings/securities-account/edit');
insert into user_path (up_pc_id, up_name, up_path) values (2, '編輯證券帳戶', 'settings/securities-account/edit.do');
insert into user_path (up_pc_id, up_name, up_path) values (3, '選定證券商中的所有分公司', 'settings/securities-account/securities-broker-branch-list.ajax');
insert into user_path (up_pc_id, up_name, up_path) values (1, '股票統計圖表', 'stock/chart');
insert into user_path (up_pc_id, up_name, up_path) values (1, '股票交易明細', 'stock/list');