-- 1. BOOK 테이블 생성 구문
CREATE TABLE BOOK (
      bookId    VARCHAR2(8)
    , title     VARCHAR2(20)
    , author    VARCHAR2(20)
    , price     NUMBER(12)
    , isbn      VARCHAR2(20)
    , publish   VARCHAR2(20)
    , CONSTRAINT  pk_book PRIMARY KEY (bookId)
);

-- 2. isExist() : 동일 데이터가 존재하는지 선조회
SELECT b.BOOKID
  FROM book b
 WHERE b.BOOKID = ?
;

-- 3. add() : 신규 제품 정보 등록
INSERT INTO BOOK(bookId, title, author, price, isbn, publish)
VALUES (?, ?, ?, ?, ?, ?)
;

-- 4. get() : 제품 정보 조회
SELECT b.BOOKID
     , b.TITLE
     , b.AUTHOR
     , b.PRICE
     , b.ISBN
     , b.PUBLISH
  FROM book b
 WHERE b.BOOKID = ?
;

-- 5. set()
UPDATE book b
   SET b.BOOKID = ?
     , b.TITLE = ?
     , b.AUTHOR = ?
     , b.PRICE = ?
     , b.ISBN = ?
     , b.PUBLISH = ?
 WHERE b.BOOKID = ?
;

-- 6. remove()
DELETE book b
 WHERE b.BOOKID = ?
;

-- 7. getAllProducts()
SELECT b.BOOKID
     , b.TITLE
     , b.AUTHOR
     , b.PRICE
     , b.ISBN
     , b.PUBLISH
  FROM book b
;