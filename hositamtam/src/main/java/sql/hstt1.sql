--
-- Copyright (c) TMI Corporation 2023.  All Rights Reserved.
--
--  NAME
--    hstt.sql
--
--  USAGE
--       SQL> START hstt.sql
--
--

SET TERMOUT ON
PROMPT 호시탐탐의 테이블을 생성합니다. Please wait.
PROMPT .
PROMPT .
PROMPT .
SET TERMOUT OFF


drop table store_payment;
drop table payment;
drop table review;
drop table member_store_favorite;
drop table member_store_close;
drop table member_post_like;
drop table comments;
drop table post;
drop table store;
drop table market_category;
drop table category;
drop table market;
drop table member;

drop sequence seq_cno;
drop sequence seq_pno;
drop sequence seq_rno;
drop sequence seq_sno;
drop sequence seq_mno;


create sequence seq_mno
    start with 166
    increment by 1
    minvalue 166
    nocycle
    nocache;

create sequence seq_sno
    start with 1
    increment by 1
    minvalue 1
    nocycle
    nocache;

create sequence seq_rno
    start with 1
    increment by 1
    minvalue 1
    nocycle
    nocache;

create sequence seq_pno
    start with 1
    increment by 1
    minvalue 1
    nocycle
    nocache;

create sequence seq_cno
    start with 1
    increment by 1
    minvalue 1
    nocycle
    nocache;



ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD HH24:MI:SS';

--1.	MEMBER (회원 테이블)

create table member(
id 		varchar2(12) 		constraint member_id_ck check (length(id) between 4 and 12)
, nickname 	varchar2(24) 		constraint member_nickname_nn not null
, passwd 		varchar2(16) 		constraint member_passwd_nn not null
, birthdate 	date 			constraint member_birthdate_nn not null
, gender 		char(3) 			constraint member_gender_nn not null
, exp 		number(5) 		default 0 constraint member_exp_nn not null
, grade 		number(1) 		default 0 constraint member_grade_nn not null
, exist 		number(1) 		default 0 constraint member_exist_nn not null
, constraint 	member_id_pk 		primary key (id) 
, constraint 	member_nickname_uq 	unique (nickname) 
, constraint 	member_nickname_ck 	check (length(nickname) between 2 and 8)
, constraint 	member_passwd_ck 	check (length(passwd) between 8 and 16)
, constraint 	member_gender_ck 		check (gender in ('여', '남'))
, constraint 	member_grade_ck 		check (grade between 0 and 4)
, constraint 	member_exist_ck 		check (exist in (0,1))
);


--* 더미 데이터)
insert into member values ('king123', '효철짱짱맨', '123456789', '2023-10-23', '남', 45, 2, 0);
insert into member values ('longlee', '롱다리맨', '1234567891', '2000-01-20', '여', 24, 1, 0);
insert into member values ('shortlee', '숏다리맨', '123456789', '1995-03-01', '남', 0, 0, 0);


--2.	MARKET (시장 테이블)

create table market(
mno 		number(3)
, mname 	varchar2(80)
, mtype 	varchar2(10)
, maddr 	varchar2(210)
, mlat 		varchar2(20)
, mlng 		varchar2(20)
, mtoilet 	char(1)
, mparking 	char(1)
, mtel 		char(20)
, mupdateday 	char(10)
, constraint 	market_no_pk 	primary key (mno)
);




--3.	CATEGORY (카테고리)

create table category(
cateno 	number(5)
, catetype 	varchar2(12) 		constraint category_type_nn not null
, constraint 	category_cateno_pk 	primary key (cateno)
);


--** 필수 입력 데이터)
insert into category values (1, '농산물');
insert into category values (2, '음식점');
insert into category values (3, '가공식품');
insert into category values (4, '수산물');
insert into category values (5, '축산물');
insert into category values (6, '가정용품');
insert into category values (7, '의류');
insert into category values (8, '신발');
insert into category values (9, '기타');




--4.	MARKET_CATEGORY (시장 카테고리 교차 테이블)

create table market_category(
mno 	number(3)
, cateno 	number(5)
, constraint 	market_category_mno_fk	 	foreign key (mno) references market (mno)
, constraint 	market_category_cateno_fk 		foreign key (cateno) references category (cateno)
, constraint 	market_category_mno_cateno_pk 	primary key (mno, cateno)
);



COMMIT;

SET TERMOUT ON
PROMPT TMI
PROMPT 호시탐탐
PROMPT .
PROMPT .
PROMPT MARKET_CATEGORY 까지 테이블 생성이 완료되었습니다.