-- MySQL

-- SET UP
-- show variables like 'character_set_%';
-- set character_set_database = utf8;
-- set character_set_results = utf8;
-- set character_set_server = utf8;
-- set character_set_system = utf8;

-- show variables like 'collation_%';
-- set collation_server = utf8_general_ci;
-- set collation_database = utf8_general_ci;

drop database if exists istockagedb;

create database if not exists istockagedb;

use istockagedb;

-- CREATE
-- System
/*
create table admin_path (
    ap_path                 varchar(100) not null,
    ap_name                 nvarchar(20) not null,
    primary key (ap_path)
);
*/

create table user_path (
    up_path                 varchar(100) not null,
    up_name                 nvarchar(20) not null,
    primary key (up_path)
);

create table code_category (
    cc_no                   int not null,
    cc_name                 nvarchar(20) not null,
    primary key (cc_no)
);

create table code (
    co_cc_no                int not null,
    co_no                   tinyint not null,
    co_name                 nvarchar(20) not null,
    primary key (co_cc_no, co_no),
    foreign key (co_cc_no) references code_category (cc_no)
);

-- Admin
create table securities_broker_head (
    sh_no                   char(2) not null,
    sh_name                 nvarchar(10) not null,
    sh_update_time          datetime not null,
    primary key (sh_no)
);

create table securities_broker_branch (
    sb_sh_no                char(2) not null,
    sb_no                   char(2) not null,
    sb_name                 nvarchar(10) not null,
    sb_update_time          datetime not null,
    primary key (sb_sh_no, sb_no),
    foreign key (sb_sh_no) references securities_broker_head (sh_no)
);

create table securities (
	se_no                   varchar(10) not null,
	se_name                 nvarchar(10) not null,
	se_update_time          datetime not null,
	primary key (se_no)
);

/*
create table futures_broker_head (
    fh_no                   char(4) not null,
    fh_name                 nvarchar(10) not null,
    fh_update_time          datetime not null,
    primary key (fh_no)
);

create table futures_broker_branch (
    fb_fh_no                char(4) not null,
    fb_no                   char(3) not null,
    fb_name                 nvarchar(10) not null,
    fb_update_time          datetime not null,
    primary key (fb_fh_no, fb_no),
    foreign key (fb_fh_no) references futures_broker_head (fh_no)
);
*/

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
    ml_up_path              varchar(100) not null,
    ml_ip                   varchar(20),
    primary key (ml_id),
    foreign key (ml_me_id) references member (me_id),
    foreign key (ml_up_path) references user_path (up_path)
);

create table securities_account (
    sa_id                   int auto_increment not null,
    sa_me_id                int not null,
    sa_sb_sh_no             char(2) not null,
    sa_sb_no                char(2) not null,
    sa_no                   char(7) not null,
    sa_discount             tinyint,
    sa_count                int not null,
    sa_update_time          datetime not null,
    primary key (sa_id),
    foreign key (sa_me_id) references member (me_id),
    foreign key (sa_sb_sh_no, sa_sb_no) references securities_broker_branch (sb_sh_no, sb_no)
);

create table stock (
    st_id                   int auto_increment not null,
    st_me_id                int not null,
    st_sa_id                int not null,
    st_se_no                varchar(10) not null,
    st_co_cc_no             int not null,
    st_co_no                tinyint not null,
    st_buy_time             datetime,
    st_buy_price            float,
    st_buy_share            int,
    st_buy_discount         tinyint,
    st_buy_fee              mediumint,
    st_buy_delivery         int,
    st_sell_time            datetime,
    st_sell_price           float,
    st_sell_share           int,
    st_sell_discount        tinyint,
    st_sell_fee             mediumint,
    st_sell_tax             mediumint,
    st_sell_delivery        int,
    st_update_time          datetime not null,
    primary key (st_id),
    foreign key (st_me_id) references member (me_id),
    foreign key (st_sa_id) references securities_account (sa_id),
    foreign key (st_se_no) references securities (se_no),
    foreign key (st_co_cc_no, st_co_no) references code (co_cc_no, co_no)
);

-- INSERT
-- System
-- user_path
insert into user_path (up_path, up_name) values ('error/page-not-found', '找不到網頁');
insert into user_path (up_path, up_name) values ('index', '首頁');
insert into user_path (up_path, up_name) values ('member/profile', '帳戶');
insert into user_path (up_path, up_name) values ('secure/forget-password', '忘記密碼');
insert into user_path (up_path, up_name) values ('secure/forget-password.do', '忘記密碼');
insert into user_path (up_path, up_name) values ('secure/reset-password', '重設密碼');
insert into user_path (up_path, up_name) values ('secure/reset-password.do', '重設密碼');
insert into user_path (up_path, up_name) values ('secure/sign-in', '登入');
insert into user_path (up_path, up_name) values ('secure/sign-in.do', '登入');
insert into user_path (up_path, up_name) values ('secure/sign-out.do', '登出');
insert into user_path (up_path, up_name) values ('secure/sign-up', '註冊');
insert into user_path (up_path, up_name) values ('secure/sign-up.do', '註冊');
insert into user_path (up_path, up_name) values ('secure/sign-up-activity.do', '啟用帳號');
insert into user_path (up_path, up_name) values ('secure/sign-up-email-repeat.ajax', '信箱重複驗證');
insert into user_path (up_path, up_name) values ('secure/sign-up-mail', '發送確認信');
insert into user_path (up_path, up_name) values ('secure/sign-up-mail.do', '發送確認信');
insert into user_path (up_path, up_name) values ('secure/sign-up-mail-again', '重新發送確認信');
insert into user_path (up_path, up_name) values ('secure/sign-up-mail-again.do', '重新發送確認信');
insert into user_path (up_path, up_name) values ('settings/account', '個人帳戶');
insert into user_path (up_path, up_name) values ('settings/account/change-password.do', '個人帳戶(變更密碼)');
insert into user_path (up_path, up_name) values ('settings/account/info.do', '個人帳戶(基本資料)');
insert into user_path (up_path, up_name) values ('settings/securities-account', '證券帳戶');
insert into user_path (up_path, up_name) values ('settings/securities-account/add', '新增證券帳戶');
insert into user_path (up_path, up_name) values ('settings/securities-account/add.do', '新增證券帳戶');
insert into user_path (up_path, up_name) values ('settings/securities-account/edit', '編輯證券帳戶');
insert into user_path (up_path, up_name) values ('settings/securities-account/edit.do', '編輯證券帳戶');
insert into user_path (up_path, up_name) values ('settings/securities-account/securities-broker-branch-list.ajax', '選定證券商中的所有分公司');
insert into user_path (up_path, up_name) values ('stock/chart', '股票統計圖表');
insert into user_path (up_path, up_name) values ('stock/inventory', '股票庫存明細');
insert into user_path (up_path, up_name) values ('stock/inventory/add', '新增股票庫存');
insert into user_path (up_path, up_name) values ('stock/inventory/add.do', '新增股票庫存');
insert into user_path (up_path, up_name) values ('stock/list', '股票交易明細');

-- code_category
insert into code_category (cc_no, cc_name) values (1, '買賣類別');

-- code
insert into code (co_cc_no, co_no, co_name) values (1, 1, '現股');
insert into code (co_cc_no, co_no, co_name) values (1, 2, '融資');
insert into code (co_cc_no, co_no, co_name) values (1, 3, '融券');
insert into code (co_cc_no, co_no, co_name) values (1, 4, '中籤');

-- Admin
-- securities_broker_head
insert into securities_broker_head (sh_no, sh_name, sh_update_time) values ('92', '凱基', now());
insert into securities_broker_head (sh_no, sh_name, sh_update_time) values ('96', '富邦', now());

-- securities_broker_branch
insert into securities_broker_branch (sb_sh_no, sb_no, sb_name, sb_update_time) values ('92', '18', '大直', now());
insert into securities_broker_branch (sb_sh_no, sb_no, sb_name, sb_update_time) values ('96', '79', '延吉', now());

-- securities
insert into securities (se_no, se_name, se_update_time) values ('2317', '鴻海', now());
insert into securities (se_no, se_name, se_update_time) values ('2337', '旺宏', now());
insert into securities (se_no, se_name, se_update_time) values ('2834', '臺企銀', now());
insert into securities (se_no, se_name, se_update_time) values ('2884', '玉山金', now());
insert into securities (se_no, se_name, se_update_time) values ('3264', '欣銓', now());

-- User
-- member
insert into member (me_no, me_email, me_password, me_salt, me_random, me_lastname, me_firstname, me_activity_code, me_signup_time, me_update_info_time, me_update_pwd_time) values ('9203330360', 'chengjhan@gmail.com', '03a6de93ab7271375694231bf9eacc5b', '992cb6c0-c52e-4155-94c5-558251878998', null, null, null, 1, now(), now(), now());
insert into member (me_no, me_email, me_password, me_salt, me_random, me_lastname, me_firstname, me_activity_code, me_signup_time, me_update_info_time, me_update_pwd_time) values ('0568914095', 'chengjhan+1@gmail.com', '348f6a73c4a04d015e516ffddfde7432', '14bbce0f-78b8-4e83-822a-06b619f00758', null, null, null, 1, now(), now(), now());

-- securities_account
insert into securities_account (sa_me_id, sa_sb_sh_no, sa_sb_no, sa_no, sa_discount, sa_count, sa_update_time) values (1, '96', '79', '0239889', 60, 0, now());
insert into securities_account (sa_me_id, sa_sb_sh_no, sa_sb_no, sa_no, sa_discount, sa_count, sa_update_time) values (1, '92', '18', '0060626', 38, 0, now());
insert into securities_account (sa_me_id, sa_sb_sh_no, sa_sb_no, sa_no, sa_discount, sa_count, sa_update_time) values (2, '96', '79', '1111111', 60, 0, now());

-- stock
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 1, '2317', 1, 1, '2012-08-27 12:00:00', 85, 1000, 60, 73, 85073, '2012-09-14 12:00:00', 96.2, 1000, 60, 82, 288, 95830, now());
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 1, '3264', 1, 1, '2012-09-17 12:00:00', 20.8, 1000, 60, 20, 20820, '2012-09-27 12:00:00', 20.2, 1000, 60, 20, 60, 20120, now());
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 1, '2337', 1, 1, '2012-09-20 12:00:00', 9.8, 2000, 60, 20, 19620, '2012-10-04 12:00:00', 9.5, 2000, 60, 20, 57, 18923, now());
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 2, '2884', 1, 1, '2018-07-26 10:33:43', 20.95, 2000, 40, 24, 41924, null, null, null, null, null, null, null, now());
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 2, '2884', 1, 1, '2018-08-02 11:46:14', 21.3, 2000, 40, 24, 42624, null, null, null, null, null, null, null, now());
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 2, '2884', 1, 1, '2018-08-02 12:27:25', 21.25, 2000, 40, 24, 42524, null, null, null, null, null, null, null, now());
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 1, '2884', 1, 1, '2018-08-08 14:30:00', 21.95, 2000, 60, 37, 43937, null, null, null, null, null, null, null, now());
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 1, '2884', 1, 1, '2018-08-09 13:15:10', 21.85, 2000, 60, 37, 43737, null, null, null, null, null, null, null, now());
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 1, '2884', 1, 1, '2018-08-09 13:18:06', 21.8, 2000, 60, 37, 43637, null, null, null, null, null, null, null, now());
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 2, '2884', 1, 1, '2018-08-13 13:20:54', 21.5, 2000, 40, 24, 43024, null, null, null, null, null, null, null, now());
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 2, '2834', 1, 1, '2018-09-06 14:30:00', 10.55, 5000, 40, 30, 52780, null, null, null, null, null, null, null, now());
insert into stock (st_me_id, st_sa_id, st_se_no, st_co_cc_no, st_co_no, st_buy_time, st_buy_price, st_buy_share, st_buy_discount, st_buy_fee, st_buy_delivery, st_sell_time, st_sell_price, st_sell_share, st_sell_discount, st_sell_fee, st_sell_tax, st_sell_delivery, st_update_time) values (1, 2, '2834', 1, 1, '2018-09-10 14:30:00', 10.55, 5000, 40, 30, 52780, null, null, null, null, null, null, null, now());