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
, sname 		varchar2(60) 	constraint store_name_nn not null
, slat 		varchar2(20) 	constraint store_lat_nn not null
, slng 		varchar2(20) 	constraint store_lng_nn not null
, stype 		char(6) 		constraint store_type_nn not null
, sphoto 		varchar2(200)
, sclosecount 	number(1) 	default 0 constraint store_closecount_nn not null
, sfavoritecount 	number(4) 	default 0 constraint store_favoritecount_nn not null
, scategory 	varchar2(30) 	constraint store_category_nn not null
, constraint 	store_no_pk 		primary key (sno)
, constraint 	store_mno_fk	 	foreign key (mno) references market (mno)
, constraint 	store_id_fk 		foreign key (id) references member (id)
, constraint 	store_name_uq 		unique (sname)
, constraint 	store_name_ck 		check (length(sname) between 2 and 20)
, constraint 	store_type_ck 		check (stype in ('좌판', '매장'))
, constraint 	store_photo_ck 		check (substr(sphoto, -3) in ('png', 'jpg') or substr(sphoto, -4) = 'jpeg')
, constraint 	store_category_ck 		check (length(scategory) between 2 and 10)
, constraint 	store_sfavoritecount_ck 	check (sfavoritecount >= 0)
, constraint 	store_sclosecount_ck 	check (sclosecount between 0 and 3)
);

--* 더미 데이터)
insert into store values (seq_sno.nextval, 2, 'king123', '문현역 7번 출구 앞 10m', '35.13931', '129.1052', '좌판', 'testphoto.png', 0, 0, '분식');
insert into store values (seq_sno.nextval, 99, 'longlee', '지게골역 출구 앞 5m', '35.15445', '129.1190', '좌판', 'testphoto2.jpeg', 0, 22, '야채 가게');
insert into store values (seq_sno.nextval, 2, 'shortlee', '문현역 5번 출구 앞', '35.16046', '129.0562', '매장', 'testphoto2.jpeg', 0, 79, '과일 가게');
insert into store values (seq_sno.nextval, 2, 'longlee', '서면역 출구 앞 5m', '35.16638', '129.0712', '좌판', 'testphoto3.jpg', 0, 39, '떡볶이 가게');
insert into store values (seq_sno.nextval, 99, 'king123', '지게골역 출구 5m', '35.14459', '129.0285', '좌판', 'testphoto22.png', 0, 199, '잡화점');
insert into store values (seq_sno.nextval, 152, 'king123', '역 7번 출구 앞 10m', '35.13933', '129.1051', '좌판', 'testphoto.png', 0, 0, '떡볶이집');

-- 10000개의 더미 데이터 추가 삽입
BEGIN
  FOR i IN 1..10000 LOOP
    INSERT INTO store (sno, mno, id, sname, slat, slng, stype, sphoto, sclosecount, sfavoritecount, scategory)
    VALUES (
      seq_sno.nextval,
      FLOOR(DBMS_RANDOM.VALUE(1, 166)), -- 1부터 165 사이의 임의의 mno
      CASE MOD(i, 3)
        WHEN 0 THEN 'king123'
        WHEN 1 THEN 'longlee'
        WHEN 2 THEN 'shortlee'
      END, -- 3개의 id를 번갈아가며 사용
      '부산역 1번 출구 앞 ' || i || 'm', -- 1m부터 10000m까지 변하는 sname
      TO_CHAR(FLOOR(DBMS_RANDOM.VALUE(35.1, 35.3)), 'FM999.99999'), -- 부산 위도의 임의의 값
      TO_CHAR(FLOOR(DBMS_RANDOM.VALUE(129.0, 129.2)), 'FM999.99999'), -- 부산 경도의 임의의 값
      CASE MOD(i, 2)
        WHEN 0 THEN '좌판'
        WHEN 1 THEN '매장'
      END, -- '좌판' 또는 '매장'
      CASE MOD(i, 2)
        WHEN 0 THEN '/finalProject/storePhotoUpload/testphoto.png'
        WHEN 1 THEN '/finalProject/storePhotoUpload/testphoto2.jpeg'
      END, -- 랜덤하게 사진 지정
      0, -- sclosecount 초기값 0
      0, -- sfavoritecount 초기값 0
      CASE MOD(i, 4)
        WHEN 0 THEN '분식'
        WHEN 1 THEN '양식'
        WHEN 2 THEN '한식'
        WHEN 3 THEN '야채가게'
      END -- 4가지 카테고리 중 랜덤하게 지정
    );
  END LOOP;
END;
/



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
, constraint 	post_pcategory_ck 	check (pcategory in ('궁금해요','도와주세요','소통해요','시장소식'))
, constraint 	post_plikecount_ck 	check (plikecount >= 0)
);



--* 더미 데이터)
insert into post values (seq_pno.nextval, 100, 'king123', '2023-10-23', '와 이거 실화냐?', '진짜 좀 심하네요 너무 맛없는데 요기 왜옴?? ㄹㅇ이해안되네', 'testphoto.jpeg', 777, '소통해요');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '오늘 시장에서 무슨 일이...', '오늘 시장에서 무슨 일이 있었나요? 공유해주세요.', 'marketphoto.jpg', 120, '시장소식');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '오늘 시장에서...', '시장완전 개판이네 진짜 무야!!!!', 'marketphoto.jpeg', 11, '시장소식');
insert into post values (seq_pno.nextval, 100, 'shortlee', sysdate, '대박터짐요!!', '저 오늘 너무 기분 좋아요~~ㅎㅎ', 'marketphoto1.png', 12, '소통해요');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '제발 읽어주세요.', 'ㅋㅋㅋㅋㅋ이걸 낚이죠?', 'marketphoto11.jpg', 177, '소통해요');
insert into post values (seq_pno.nextval, 101, 'longlee', sysdate, '오늘 시장에서 무슨 일이...', '오늘 시장에서 무슨 일이 있었나요? 공유해주세요.', 'marketphoto.jpg', 120, '시장소식');
insert into post values (seq_pno.nextval, 100, 'king123', '2023-10-23', '오늘 날씨 너무 좋아요', '집에만 있기에 심심해서 산책 겸 나왔는데 좋아요~!!', 'testphoto.jpeg', 777, '소통해요');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '드디어 붕세권이다!', '수미식당 옆 좌판에 붕어빵 기계 들어왔네요! 이제 우리 동네 시장도 붕세권입니당ㅎㅎ.', 'marketphoto.jpg', 120, '소통해요');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '남천해변시장 산책 모임 구해용', '남천해변시장 남쪽 입구에 걷기 좋은 코스가 있어서 혹시 같이 걸으실 분 있으면 댓글 남겨주세요ㅎㅎ', 'marketphoto.jpeg', 11, '소통해요');
insert into post values (seq_pno.nextval, 101, 'shortlee', sysdate, '벌써 일년....', '2023년 시작한 지 엊그제 같은데 벌써 11월이라뇨?!?!?', 'marketphoto1.png', 12, '소통해요');
insert into post values (seq_pno.nextval, 101, 'longlee', sysdate, '단풍으로 물드니 더욱 운치가 있네욯ㅎㅎ.', '매주 시장에서 장보는데 단풍이 이쁘게 물들어서 사진으로 남겨봐요!', 'marketphoto11.jpg', 177, '소통해요');
insert into post values (seq_pno.nextval, 101, 'longlee', sysdate, '지난 주에는 춥더니 이번 주는 왤케 덥나유ㅠㅠ', '오락가락하는 날씨네요ㅠㅠ', 'marketphoto.jpg', 120, '소통해요');
insert into post values (seq_pno.nextval, 100, 'king123', '2023-10-23', '혹시 오늘 수미네 반찬 가게 열었나요??', '지난 주에 보니까 수요일에 휴무였던 거 같은데, 혹시 아시는 분 있으면 댓글 부탁해용!', 'testphoto.jpeg', 777, '궁금해요');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '남천해변시장 당근 제일 싼 곳', '담주에 당근 많이 사야하는데 제일 싼 곳 추천좀여ㅠ', 'marketphoto.jpg', 120, '궁금해요');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '오늘 남천해변시장 처음왔는데, 화장실 어디있나여??', '진짜 죽을 거 같아여 제발 빨리 좀 알려주세요', 'marketphoto.jpeg', 11, '궁금해요');
insert into post values (seq_pno.nextval, 101, 'shortlee', sysdate, '혹시 시장 대표 전화번호 아시는 분??', '혹시 시장 대표 전화번호 아시는 분??', 'marketphoto1.png', 12, '궁금해요');
insert into post values (seq_pno.nextval, 101, 'longlee', sysdate, '산책로 어디로 가면 있나여??.', '해변길 산책하러 가려고 하는데 어디로 가야하는지 몰겟네요ㅠ', 'marketphoto11.jpg', 177, '궁금해요');
insert into post values (seq_pno.nextval, 101, 'longlee', sysdate, '붕어빵 기계 좌표 좀요ㅠㅠ', '날씨가 쌀쌀해서 붕어빵 각이네요 위치 공유해주세요.', 'marketphoto.jpg', 120, '궁금해요');
insert into post values (seq_pno.nextval, 100, 'king123', '2023-10-23', '와 이거 실화냐?', '진짜 좀 심하네요 너무 맛없는데 요기 왜옴?? ㄹㅇ이해안되네', 'testphoto.jpeg', 777, '시장소식');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '오늘 시장에서 무슨 일이...', '오늘 시장에서 무슨 일이 있었나요? 공유해주세요.', 'marketphoto.jpg', 120, '시장소식');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '오늘 시장에서...', '시장완전 개판이네 진짜 무야!!!!', 'marketphoto.jpeg', 11, '시장소식');
insert into post values (seq_pno.nextval, 101, 'shortlee', sysdate, '대박터짐요!!', '저 오늘 너무 기분 좋아요~~ㅎㅎ', 'marketphoto1.png', 12, '시장소식');
insert into post values (seq_pno.nextval, 101, 'longlee', sysdate, '제발 읽어주세요.', 'ㅋㅋㅋㅋㅋ이걸 낚이죠?', 'marketphoto11.jpg', 177, '시장소식');
insert into post values (seq_pno.nextval, 101, 'longlee', sysdate, '오늘 시장에서 무슨 일이...', '오늘 시장에서 무슨 일이 있었나요? 공유해주세요.', 'marketphoto.jpg', 120, '시장소식');
insert into post values (seq_pno.nextval, 100, 'king123', '2023-10-23', '빨간 모자 쓴 7세 아동을 찾습니다.', '남천해변시장 1번 화장실 쪽에서 길을 잃은 것으로 보입니다. 혹시 보신 분은 010-0000-0000으로 연락주세요', 'testphoto.jpeg', 777, '도와주세요');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '에어팟 프로 왼쪽 찾아주실 분ㅠ', '해변로 쪽 CU 앞에서 잃어버린 것 같아요 찾아주신 분은 제 오른 쪽 귀가 감사의 인사를 드릴게요ㅠ.', 'marketphoto.jpg', 120, '도와주세요');
insert into post values (seq_pno.nextval, 100, 'longlee', sysdate, '찬미네 잡화점 앞에서 갈색 지갑 분실 하신 분 남천1동 파출소에 맡겨놨습니다!', '10/29일 오후 3시경 주웠고, 빨리 가져가시길 바랍니당!', 'marketphoto.jpeg', 11, '도와주세요');
insert into post values (seq_pno.nextval, 101, 'shortlee', sysdate, '우리 해피 찾아주세요ㅠ', '검정 치와와 입니다ㅠㅠ 꽃무늬 옷 입고 있어요. 찾아주신 분 꼭 사례할게요', 'marketphoto1.png', 12, '도와주세요');
insert into post values (seq_pno.nextval, 101, 'longlee', sysdate, '에어팟 프로 오른쪽 잃어버렸습니다.', '해변로 쪽 GS 앞에서 잃버린 것 같네요. 혹시 보신 분 있으신가여???', 'marketphoto11.jpg', 177, '도와주세요');
insert into post values (seq_pno.nextval, 101, 'longlee', sysdate, '흰색 말티즈가 시장 안을 며칠째 떠돌고 있습니다.', '목걸이 있는 거 보니까 집에서 키우는 댕댕이 같은데, 혹시 견주분 보시면 연락주세요!.', 'marketphoto.jpg', 120, '도와주세요');

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





--9.	MEMBER_STORE_CLOSE (점포 폐업신고 테이블)

create table member_store_close(
sno 		number(5)
, id 	varchar2(12)
, constraint 	member_store_close_sno_fk 		foreign key (sno) references store (sno)
, constraint 	member_store_close_id_fk 		foreign key (id) references member (id)
, constraint 	member_store_close_sno_id_pk 	primary key (sno, id)
);



--* 더미 데이터)




--10.	MEMBER_STORE_FAVORITE (점포 찜 테이블)

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



--11.	REVIEW (리뷰 테이블)

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



--12.	PAYMENT (결제방식 테이블)

create table payment(
payno 	number(1)
, paytype 	varchar2(12) 	constraint payment_type_nn not null
, constraint 		payment_payno_pk 	primary key (payno)
);



--** 필수 입력 데이터)
insert into payment values (1, '현금');
insert into payment values (2, '카드');
insert into payment values (3, '계좌이체');


--13.	STORE_PAYMENT (점포 결제방식 교차 테이블)

create table store_payment(
sno 		number(5)
, payno 		number(1)
, constraint 	store_payment_sno_fk 		foreign key (sno) references store (sno)
, constraint 	store_payment_payno_fk 		foreign key (payno) references payment (payno)
, constraint 	store_payment_sno_payno_pk 	primary key (sno, payno)
);


--* 더미 데이터)
insert into store_payment values (1, 1);
insert into store_payment values (1, 2);
insert into store_payment values (2, 1);
insert into store_payment values (2, 3);
insert into store_payment values (3, 1);

BEGIN
  FOR i IN 4..10006 LOOP
    INSERT INTO store_payment (sno, payno)
    SELECT i, LEVEL
    FROM DUAL
    CONNECT BY LEVEL <= ROUND(DBMS_RANDOM.VALUE(1, 3));
  END LOOP;
  COMMIT;
END;
/
-- trigger 생성
-- 리뷰 작성시 exp 1씩 증가
CREATE OR REPLACE TRIGGER increase_exp_on_review
AFTER INSERT ON review
FOR EACH ROW
BEGIN
    UPDATE member
    SET exp = exp + 1
    WHERE id = :NEW.id;
END;
/

-- 댓글 작성시 exp 1씩 증가
CREATE OR REPLACE TRIGGER increase_exp_on_comment
AFTER INSERT ON comments
FOR EACH ROW
BEGIN
    UPDATE member
    SET exp = exp + 1
    WHERE id = :NEW.id;
END;
/

-- 게시글 작성시 exp 3씩 증가
CREATE OR REPLACE TRIGGER increase_exp_on_post
AFTER INSERT ON post
FOR EACH ROW
BEGIN
    UPDATE member
    SET exp = exp + 3
    WHERE id = :NEW.id;
END;
/

-- 점포 등록시 exp 3씩 증가
CREATE OR REPLACE TRIGGER increase_exp_on_store
AFTER INSERT ON store
FOR EACH ROW
BEGIN
    UPDATE member
    SET exp = exp + 5
    WHERE id = :NEW.id;
END;
/

-- exp 에 따른 grade 설정
CREATE OR REPLACE TRIGGER increase_grade_on_exp
BEFORE INSERT OR UPDATE ON member
FOR EACH ROW
BEGIN
    IF 	  :NEW.exp < 20 THEN
        	:NEW.grade := 0;
    ELSIF :NEW.exp >= 20 AND :NEW.exp < 40 THEN
        	:NEW.grade := 1;
    ELSIF :NEW.exp >= 40 AND :NEW.exp < 60 THEN
        	:NEW.grade := 2;
    ELSIF :NEW.exp >= 60 AND :NEW.exp < 80 THEN
        	:NEW.grade := 3;
    ELSIF :NEW.exp >= 80 THEN
        	:NEW.grade := 4;
    END IF;
END;
/

COMMIT;

SET TERMOUT ON
PROMPT TMI
PROMPT 호시탐탐
PROMPT .
PROMPT .
PROMPT 모든 테이블 생성이 완료되었습니다.
