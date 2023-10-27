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
PROMPT 호시탐탐의 나머지 테이블을 생성합니다. Please wait.
PROMPT .
PROMPT .
PROMPT .
SET TERMOUT OFF

ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD HH24:MI:SS';


--5.	STORE (점포 테이블)
create table store(
sno 		number(5)
, mno 		number(3)
, id 		varchar2(12)
, spno 		number(5)
, sono 		number(3)
, sname 		varchar2(60) 	constraint store_name_nn not null
, slat 		varchar2(20) 	constraint store_lat_nn not null
, slng 		varchar2(20) 	constraint store_lng_nn not null
, stype 		varchar2(20) 	constraint store_type_nn not null
, sphoto 		varchar2(200)
, sclosecount 	number(1) 	default 0 constraint store_closecount_nn not null
, sfavoritecount 	number(4) 	default 0 constraint store_favoritecount_nn not null
, constraint 	store_no_pk 		primary key (sno)
, constraint 	store_mno_fk	 	foreign key (mno) references market (mno)
, constraint 	store_id_fk 		foreign key (id) references member (id)
, constraint 	store_parent_no_fk 	foreign key (sno) references store (sno)
, constraint 	store_name_uq 		unique (sname)
, constraint 	store_name_ck 		check (length(sname) between 2 and 15)
, constraint 	store_type_ck 		check (stype in ('좌판', '매장'))
, constraint 	store_photo_ck 		check (substr(sphoto, -3) in ('png', 'jpg') or substr(sphoto, -4) = 'jpeg')
);


--* 더미 데이터)
insert into store values (seq_sno.nextval, 2, 'king123', '', '', '문현역 7번 출구 앞 10m', '35.333242', '145.2343432', '좌판', 'testphoto.png', 1, 0);
insert into store values (seq_sno.nextval, 99, 'longlee', '', '', '지게골역 출구 앞 5m', '35.33242', '145.23432', '좌판', 'testphoto2.jpeg', 2, 22);
insert into store values (seq_sno.nextval, 2, 'shortlee', 1, 1, '문현역 5번 출구 앞', '35.33242', '145.23432', '매장', 'testphoto2.jpeg', 2, 79);
insert into store values (seq_sno.nextval, 2, 'longlee', 3, 1, '서면역 출구 앞 5m', '35.33242', '145.23432', '좌판', 'testphoto3.jpg', 2, 39);
insert into store values (seq_sno.nextval, 99, 'king123', 2, 2, '지게골역 출구 5m', '35.332', '145.2332', '좌판', 'testphoto22.png', 2, 199);



--6.	POST (글 테이블)
create table post(
pno 		number(5)
, mno 		number(3)
, id 		varchar2(12)
, pregdate 	date 			default sysdate constraint post_regdate_nn not null
, ptitle 		varchar2(100) 		constraint post_title_nn not null
, pcontent 	varchar2(1500) 		constraint post_content_nn not null
, pphoto 		varchar2(200)
, plikecount 	number(4) 		default 0 constraint post_likecount_nn not null
, pcategory 	varchar2(15) 		constraint post_pcategory_nn not null
, constraint 	post_no_pk 		primary key (pno)
, constraint 	post_mno_fk 		foreign key (mno) references market (mno)
, constraint 	post_id_fk	 	foreign key (id) references member (id)
, constraint 	post_title_ck 		check (length(ptitle) between 5 and 20)
, constraint 	post_content_ck 		check (length(pcontent) between 5 and 500)
, constraint 	post_photo_ck 		check (substr(pphoto, -3) in ('png', 'jpg') or substr(pphoto, -4) = 'jpeg')
, constraint 	post_pcategory_ck 		check (pcategory in ('시장질문', '사건사고', '일상', '실종/분실'))
);


--* 더미 데이터)
insert into post values (seq_pno.nextval, 100, 'king123', '2023-10-23', '와 이거 실화냐?', '진짜 좀 심하네요 너무 맛없는데 요기 왜옴?? ㄹㅇ이해안되네', 'testphoto.jpeg', 777, '일상');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '오늘 시장에서 무슨 일이...', '오늘 시장에서 무슨 일이 있었나요? 공유해주세요.', 'marketphoto.jpg', 120, '사건사고');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '오늘 시장에서...', '시장완전 개판이네 진짜 무야!!!!', 'marketphoto.jpeg', 11, '사건사고');
insert into post values (seq_pno.nextval, 100, 'shortlee', sysdate, '대박터짐요!!', '저 오늘 너무 기분 좋아요~~ㅎㅎ', 'marketphoto1.png', 12, '일상');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '제발 읽어주세요.', 'ㅋㅋㅋㅋㅋ이걸 낚이죠?', 'marketphoto11.jpg', 177, '일상');
insert into post values (seq_pno.nextval, 101, 'longlee', sysdate, '오늘 시장에서 무슨 일이...', '오늘 시장에서 무슨 일이 있었나요? 공유해주세요.', 'marketphoto.jpg', 120, '사건사고');



--7.	COMMENTS (댓글 테이블)
create table comments(
cno 		number(5)
, pno 		number(5)
, id 		varchar2(12)
, ccontent 	varchar2(300) 		constraint comments_content_nn not null
, cregdate 	date 			default sysdate constraint comments_regdate_nn not null
, constraint 	comments_no_pk 		primary key (cno)
, constraint 	comments_pno_fk	 	foreign key (pno) references post (pno)
, constraint 	comments_id_fk 		foreign key (id) references member (id)
, constraint 	comments_content_ck 	check (length(ccontent) between 5 and 100)
);


--* 더미 데이터)
insert into comments values (seq_cno.nextval, 1, 'king123', '아 글 진짜 못쓰네 지루해서 잠올 뻔 했다~~', '2023-10-23');



--8.	MEMBER_POST_LIKE (글 좋아요 테이블)
create table member_post_like(
pno 		number(5)
, id 	varchar2(12)
, constraint 	member_post_like_pno_fk 		foreign key (pno) references post (pno)
, constraint 	member_post_like_id_fk 		foreign key (id) references member (id)
, constraint 	member_post_like_pno_id_pk 	primary key (pno, id)
);


--* 더미 데이터)
insert into member_post_like values (1, 'king123');



--9.	STORE_CATEGORY (점포 카테고리 교차 테이블)
create table store_category(
sno 		number(5)
, cateno 		number(5)
, constraint 	store_category_sno_fk 		foreign key (sno) references store (sno)
, constraint 	store_category_cateno_fk	 	foreign key (cateno) references category (cateno)
, constraint 	store_category_sno_cateno_pk 		primary key (sno, cateno)
);


--* 더미 데이터)
insert into store_category values (1, 3);
insert into store_category values (2, 4);
insert into store_category values (2, 1);



--10.	MEMBER_STORE_CLOSE (점포 폐업신고 테이블)
create table member_store_close(
sno 		number(5)
, id 	varchar2(12)
, constraint 	member_store_close_sno_fk 		foreign key (sno) references store (sno)
, constraint 	member_store_close_id_fk 		foreign key (id) references member (id)
, constraint 	member_store_close_sno_id_pk 	primary key (sno, id)
);


--* 더미 데이터)
insert into member_store_close values (1, 'king123');
insert into member_store_close values (2, 'king123');



--11.	MEMBER_STORE_FAVORITE (점포 찜 테이블)
create table member_store_favorite(
sno 		number(5)
, id 		varchar2(12)
, constraint 	member_store_favorite_no_fk 	foreign key (sno) references store (sno)
, constraint 	member_store_favorite_id_fk	 	foreign key (id) references member (id)
, constraint 	member_store_favorite_no_id_pk 	primary key (sno, id)
);


--* 더미 데이터)
insert into member_store_favorite values (1, 'king123');
insert into member_store_favorite values (2, 'king123');



--12.	REVIEW (리뷰 테이블)
create table review(
rno 		number(5)
, sno 		number(5)
, id 		varchar2(12)
, rregdate 	date 			default sysdate constraint review_regdate_nn not null
, rcontent 	varchar2(300) 		constraint review_content_nn not null
, rrating 		number(1) 		constraint review_rating_nn not null
, constraint 	review_no_pk 		primary key (rno)
, constraint 	review_sno_fk 		foreign key (sno) references store (sno)
, constraint 	review_id_fk 		foreign key (id) references member (id)
, constraint 	review_content_ck 		check (length(rcontent) between 5 and 500)
, constraint 	review_rating_ck 		check (rrating between 1 and 5)
);


--* 더미 데이터)
insert into review values (seq_rno.nextval, 1, 'king123', '2023-08-01', '굿 진짜 존맛탱 그잡채!', 5);
insert into review values (seq_rno.nextval, 2, 'king123', '2023-10-23', '우웩 토나옴 다신 여기 안온다', 1);
insert into review values (seq_rno.nextval, 1, 'king123', sysdate, '우웨우웩우웩!!', 2);
insert into review values (seq_rno.nextval, 1, 'longlee', '2023-10-21', '진짜 넘 맛없는데??', 1);
insert into review values (seq_rno.nextval, 1, 'shortlee', '2023-10-19', '음 보통~~', 3);
insert into review values (seq_rno.nextval, 2, 'shortlee', sysdate, '맛있음!!', 5);



--13.	PAYMENT (결제방식 테이블)
create table payment(
payno 	number(1)
, paytype 	varchar2(12) 	constraint payment_type_nn not null
, constraint 		payment_payno_pk 	primary key (payno)
);


--** 필수 입력 데이터)
insert into payment values (1, '현금');
insert into payment values (2, '카드');
insert into payment values (3, '계좌이체');



--14.	STORE_PAYMENT (점포 결제방식 교차 테이블)
create table store_payment(
sno 		number(5)
, payno 		number(1)
, constraint 	store_payment_sno_fk 		foreign key (sno) references store (sno)
, constraint 	store_payment_payno_fk 		foreign key (payno) references payment (payno)
, constraint 	store_payment_sno_payno_pk 	primary key (sno, payno)
);


--* 더미 데이터)
insert into store_payment values (1, 1);
insert into store_payment values (1, 3);
insert into store_payment values (2, 1);


COMMIT;


SET TERMOUT ON
PROMPT TMI
PROMPT 호시탐탐
PROMPT .
PROMPT .
PROMPT 모든 테이블 생성이 완료되었습니다.