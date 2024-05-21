use tendb;
-- 테이블 삭제
DROP TABLE IF EXISTS SEL_ORDER_DETAIL_TB CASCADE;
DROP TABLE IF EXISTS SEL_ORDER_TB CASCADE;
DROP TABLE IF EXISTS OUT_ITEM_DETAIL_TB CASCADE;
DROP TABLE IF EXISTS OUT_CART_TB CASCADE;
DROP TABLE IF EXISTS OUTB_DETAIL_TB CASCADE;
DROP TABLE IF EXISTS OUTB_TB CASCADE;
DROP TABLE IF EXISTS CUS_TB CASCADE;
DROP TABLE IF EXISTS FAC_ITEM_DETAIL_TB CASCADE;
DROP TABLE IF EXISTS FAC_ITEM_TB CASCADE;
DROP TABLE IF EXISTS INB_TB CASCADE;
DROP TABLE IF EXISTS FAC_TB CASCADE;
DROP TABLE IF EXISTS GBG_DETAIL_TB CASCADE;
DROP TABLE IF EXISTS INVOICE_TB CASCADE;
DROP TABLE IF EXISTS ITEM_DETAIL_TB CASCADE;
DROP TABLE IF EXISTS ITEM_TB CASCADE;
DROP TABLE IF EXISTS ITEM_CAT_TB CASCADE;
DROP TABLE IF EXISTS LOCATE_TB CASCADE;
DROP TABLE IF EXISTS SEL_TB CASCADE;
DROP TABLE IF EXISTS WHS_ADMIN_TB CASCADE;
DROP TABLE IF EXISTS WHS_TB CASCADE;
DROP TABLE IF EXISTS LOC_TB CASCADE;

-- 테이블 생성
-- LOC_TB 테이블 생성
CREATE TABLE LOC_TB (
    LOC_PK int NOT NULL AUTO_INCREMENT,
    LOC_NM varchar(30) NOT NULL,
    PRIMARY KEY (LOC_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- WHS_TB 테이블 생성
CREATE TABLE WHS_TB (
    WHS_PK bigint NOT NULL AUTO_INCREMENT,
    WHS_NM varchar(30) NOT NULL,
    LOC_PK int NOT NULL,
    WHS_CAPACITY bigint NOT NULL,
    PRIMARY KEY (WHS_PK),
    KEY FK_LOC_TB_TO_WHS_TB_1 (LOC_PK),
    CONSTRAINT FK_LOC_TB_TO_WHS_TB_1 FOREIGN KEY (LOC_PK) REFERENCES LOC_TB (LOC_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- WHS_ADMIN_TB 테이블 생성
CREATE TABLE WHS_ADMIN_TB (
    WHS_ADMIN_PK bigint NOT NULL AUTO_INCREMENT,
    WHS_EMAIL varchar(30) NOT NULL,
    WHS_PW varchar(30) NOT NULL,
    WHS_NM varchar(30) NOT NULL,
    WHS_PK bigint NOT NULL,
    PRIMARY KEY (WHS_ADMIN_PK),
    KEY FK_WHS_TB_TO_WHS_ADMIN_TB_1 (WHS_PK),
    CONSTRAINT FK_WHS_TB_TO_WHS_ADMIN_TB_1 FOREIGN KEY (WHS_PK) REFERENCES WHS_TB (WHS_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- SEL_TB 테이블 생성
CREATE TABLE SEL_TB (
    SEL_PK bigint NOT NULL AUTO_INCREMENT,
    SEL_NM varchar(30) NOT NULL,
    SEL_ADR varchar(30) NOT NULL,
    LOC_PK int NOT NULL,
    PRIMARY KEY (SEL_PK),
    KEY `FK_LOC_TB_TO_SEL_TB_1` (LOC_PK),
    CONSTRAINT FK_LOC_TB_TO_SEL_TB_1 FOREIGN KEY (LOC_PK) REFERENCES LOC_TB (LOC_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- LOCATE_TB 테이블 생성
CREATE TABLE LOCATE_TB (
    LOCATE_PK bigint NOT NULL AUTO_INCREMENT,
    LOCATE_ITEM_CNT int DEFAULT NULL COMMENT '수용량 최대',
    LOCATE_LPN_CODE varchar(30) NOT NULL,
    WHS_PK bigint NOT NULL,
    PRIMARY KEY (LOCATE_PK),
    KEY FK_WHS_TB_TO_LOCATE_TB_1 (WHS_PK),
    CONSTRAINT FK_WHS_TB_TO_LOCATE_TB_1 FOREIGN KEY (WHS_PK) REFERENCES WHS_TB (WHS_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ITEM_CAT_TB 테이블 생성
CREATE TABLE ITEM_CAT_TB (
    ITEM_CAT_PK int NOT NULL AUTO_INCREMENT,
    ITEM_CAT_NM varchar(30) NOT NULL,
    PRIMARY KEY (ITEM_CAT_PK)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ITEM_TB 테이블 생성
CREATE TABLE ITEM_TB (
    ITEM_PK bigint NOT NULL AUTO_INCREMENT,
    ITEM_NM varchar(30) NOT NULL,
    ITEM_VOL int NOT NULL,
    ITEM_CAT_PK int NOT NULL,
    ITEM_CNT int DEFAULT NULL,
    PRIMARY KEY (ITEM_PK),
    KEY FK_ITEM_CAT_TB_TO_ITEM_TB_1 (ITEM_CAT_PK),
    CONSTRAINT FK_ITEM_CAT_TB_TO_ITEM_TB_1 FOREIGN KEY (ITEM_CAT_PK) REFERENCES ITEM_CAT_TB (ITEM_CAT_PK)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- FAC_ITEM_TB 테이블 생성
CREATE TABLE FAC_ITEM_TB (
    FAC_ITEM_PK bigint NOT NULL AUTO_INCREMENT,
    FAC_ITEM_SELLER_NM varchar(30) DEFAULT NULL,
    FAC_ITEM_CAT varchar(30) DEFAULT NULL,
    FAC_ITEM_NM varchar(30) DEFAULT NULL,
    FAC_ITEM_VOL int DEFAULT NULL,
    FAC_ITEM_EXPIRATION_DT date DEFAULT NULL,
    FAC_ITEM_PRICE int DEFAULT NULL,
    FAC_ITEM_PROD_CNT int DEFAULT NULL,
    Field varchar(255) DEFAULT NULL,
    PRIMARY KEY (FAC_ITEM_PK)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- FAC_ITEM_DETAIL_TB 테이블 생성
CREATE TABLE FAC_ITEM_DETAIL_TB (
    ITEM_DETAIL_SERIAL_NUM bigint NOT NULL AUTO_INCREMENT,
    ITEM_STATE smallint DEFAULT NULL,
    FAC_ITEM_PK bigint NOT NULL,
    PRIMARY KEY (ITEM_DETAIL_SERIAL_NUM),
    KEY FK_FAC_ITEM_TB_TO_FAC_ITEM_DETAIL_TB_1 (FAC_ITEM_PK),
    CONSTRAINT FK_FAC_ITEM_TB_TO_FAC_ITEM_DETAIL_TB_1 FOREIGN KEY (FAC_ITEM_PK) REFERENCES FAC_ITEM_TB (FAC_ITEM_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- INVOICE_TB 테이블 생성
CREATE TABLE INVOICE_TB (
    INVOICE_NUM bigint NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (INVOICE_NUM)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- GBG_DETAIL_TB 테이블 생성
CREATE TABLE GBG_DETAIL_TB (
    GBG_DETAIL_PK bigint NOT NULL AUTO_INCREMENT,
    ITEM_PK bigint NOT NULL COMMENT '화장품 개별이 아닌 화장품 묶음(재고)의 ID',
    ITEM_STATE smallint NOT NULL,
    GBG_SERIAL_NUM varchar(30) NOT NULL,
    PRIMARY KEY (GBG_DETAIL_PK),
    KEY FK_ITEM_TB_TO_GBG_DETAIL_TB_1 (ITEM_PK),
    CONSTRAINT FK_ITEM_TB_TO_GBG_DETAIL_TB_1 FOREIGN KEY (ITEM_PK) REFERENCES ITEM_TB (ITEM_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- FAC_TB 테이블 생성
CREATE TABLE FAC_TB (
    FAC_PK bigint NOT NULL AUTO_INCREMENT,
    FAC_NM varchar(30) NOT NULL,
    LOC_PK int NOT NULL,
    PRIMARY KEY (FAC_PK),
    KEY FK_LOC_TB_TO_FAC_TB_1 (LOC_PK),
    CONSTRAINT FK_LOC_TB_TO_FAC_TB_1 FOREIGN KEY (LOC_PK) REFERENCES LOC_TB (LOC_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- INB_TB 테이블 생성
CREATE TABLE INB_TB (
    INB_PK bigint NOT NULL AUTO_INCREMENT,
    SEL_PK bigint NOT NULL,
    FAC_PK bigint NOT NULL,
    WHS_PK bigint NOT NULL,
    INB_ITEM_PK bigint NOT NULL,
    INB_ITEM_CAT_PK int NOT NULL,
    INB_ITEM_NM varchar(30) NOT NULL,
    INB_ITEM_VOL int NOT NULL,
    INB_ITEM_PRICE int NOT NULL,
    INB_ITEM_CNT int NOT NULL,
    INB_ITEM_EXPIRATION_DT date NOT NULL,
    PRIMARY KEY (INB_PK),
    KEY FK_SEL_TB_TO_INB_TB_1 (SEL_PK),
    KEY FK_FAC_TB_TO_INB_TB_1 (FAC_PK),
    KEY FK_WHS_TB_TO_INB_TB_1 (WHS_PK),
    KEY FK_ITEM_TB_TO_INB_TB_1 (INB_ITEM_PK),
    KEY FK_ITEM_CAT_TB_TO_INB_TB_1 (INB_ITEM_CAT_PK),
    CONSTRAINT FK_FAC_TB_TO_INB_TB_1 FOREIGN KEY (FAC_PK) REFERENCES FAC_TB (FAC_PK),
    CONSTRAINT FK_ITEM_CAT_TB_TO_INB_TB_1 FOREIGN KEY (INB_ITEM_CAT_PK) REFERENCES ITEM_CAT_TB (ITEM_CAT_PK),
    CONSTRAINT FK_ITEM_TB_TO_INB_TB_1 FOREIGN KEY (INB_ITEM_PK) REFERENCES ITEM_TB (ITEM_PK),
    CONSTRAINT FK_SEL_TB_TO_INB_TB_1 FOREIGN KEY (SEL_PK) REFERENCES SEL_TB (SEL_PK),
    CONSTRAINT FK_WHS_TB_TO_INB_TB_1 FOREIGN KEY (WHS_PK) REFERENCES WHS_TB (WHS_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- CUS_TB 테이블 생성
CREATE TABLE CUS_TB (
    CUS_PK bigint NOT NULL AUTO_INCREMENT,
    CUS_NM varchar(30) NOT NULL,
    CUS_ADR varchar(30) NOT NULL,
    PRIMARY KEY (CUS_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- SEL_ORDER_TB 테이블 생성
CREATE TABLE SEL_ORDER_TB (
    SEL_ORDER_PK bigint NOT NULL AUTO_INCREMENT,
    SEL_ORDER_DATE_DT date NOT NULL,
    CUS_PK bigint NOT NULL,
    PRIMARY KEY (SEL_ORDER_PK),
    KEY FK_CUS_TB_TO_SEL_ORDER_TB_1 (CUS_PK),
    CONSTRAINT FK_CUS_TB_TO_SEL_ORDER_TB_1 FOREIGN KEY (CUS_PK) REFERENCES CUS_TB (CUS_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- SEL_ORDER_DETAIL_TB 테이블 생성
CREATE TABLE SEL_ORDER_DETAIL_TB (
    SEL_ORDER_DETAIL_PK bigint NOT NULL AUTO_INCREMENT,
    SEL_ORDER_FK bigint NOT NULL,
    SEL_ORDER_PROD_NM varchar(30) NOT NULL,
    SEL_ORDER_DETAIL_VOL int NOT NULL,
    SEL_ORDER_DETAIL_CNT int NOT NULL,
    PRIMARY KEY (SEL_ORDER_DETAIL_PK),
    KEY FK_SEL_ORDER_TB_TO_SEL_ORDER_DETAIL_TB_1 (SEL_ORDER_FK),
    CONSTRAINT FK_SEL_ORDER_TB_TO_SEL_ORDER_DETAIL_TB_1 FOREIGN KEY (SEL_ORDER_FK) REFERENCES SEL_ORDER_TB (SEL_ORDER_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ITEM_DETAIL_TB 테이블 생성
CREATE TABLE ITEM_DETAIL_TB (
    ITEM_DETAIL_PK bigint NOT NULL AUTO_INCREMENT,
    ITEM_DETAIL_SERIAL_NUM varchar(30) NOT NULL,
    ITEM_PK bigint NOT NULL,
    ITEM_DETAIL_STATUS smallint NOT NULL,
    LOCATE_PK bigint NOT NULL,
    ITEM_DETAIL_EXPIRATION_DT date NOT NULL,
    PRIMARY KEY (ITEM_DETAIL_PK,ITEM_DETAIL_SERIAL_NUM),
    KEY FK_ITEM_TB_TO_ITEM_DETAIL_TB_1 (ITEM_PK),
    KEY FK_LOCATE_TB_TO_ITEM_DETAIL_TB_1 (LOCATE_PK),
    CONSTRAINT FK_ITEM_TB_TO_ITEM_DETAIL_TB_1 FOREIGN KEY (ITEM_PK) REFERENCES ITEM_TB (ITEM_PK),
    CONSTRAINT FK_LOCATE_TB_TO_ITEM_DETAIL_TB_1 FOREIGN KEY (LOCATE_PK) REFERENCES LOCATE_TB (LOCATE_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- OUTB_TB 테이블 생성
CREATE TABLE OUTB_TB (
    OUTB_PK bigint NOT NULL AUTO_INCREMENT,
    CUS_FK bigint NOT NULL,
    ORDER_DT date NOT NULL,
    PRIMARY KEY (OUTB_PK),
    KEY FK_CUS_TB_TO_OUTB_TB_1 (CUS_FK),
    CONSTRAINT FK_CUS_TB_TO_OUTB_TB_1 FOREIGN KEY (CUS_FK) REFERENCES CUS_TB (CUS_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- OUTB_DETAIL_TB 테이블 생성
CREATE TABLE OUTB_DETAIL_TB (
    OUTB_DETAIL_PK bigint NOT NULL AUTO_INCREMENT,
    OUTB_FK bigint NOT NULL,
    ITEM_NM varchar(30) NOT NULL,
    OUTB_PICKING_CNT int NOT NULL COMMENT '픽킹해야할 수량',
    PRIMARY KEY (OUTB_DETAIL_PK),
    KEY FK_OUTB_TB_TO_OUTB_DETAIL_TB_1 (OUTB_FK),
    CONSTRAINT FK_OUTB_TB_TO_OUTB_DETAIL_TB_1 FOREIGN KEY (OUTB_FK) REFERENCES OUTB_TB (OUTB_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- OUT_CART_TB 테이블 생성
CREATE TABLE OUT_CART_TB (
    OUT_CART_PK bigint NOT NULL AUTO_INCREMENT,
    OUTB_DETAIL_FK bigint NOT NULL,
    OUTB_CART_CNT smallint NOT NULL COMMENT 'LIMIT 50',
    PRIMARY KEY (OUT_CART_PK),
    KEY FK_OUTB_DETAIL_TB_TO_OUT_CART_TB_1 (OUTB_DETAIL_FK),
    CONSTRAINT FK_OUTB_DETAIL_TB_TO_OUT_CART_TB_1 FOREIGN KEY (OUTB_DETAIL_FK) REFERENCES OUTB_DETAIL_TB (OUTB_DETAIL_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- OUT_ITEM_DETAIL_TB 테이블 생성
CREATE TABLE OUT_ITEM_DETAIL_TB (
    OUT_ITEM_DETAIL_PK bigint NOT NULL AUTO_INCREMENT,
    ITEM_STATE smallint NOT NULL,
    ITEM_PK bigint NOT NULL,
    ITEM_EXPIRATION_DT date NOT NULL,
    ITEM_DETAIL_SERIAL_NUM varchar(30) DEFAULT NULL,
    OUT_CART_PK bigint NOT NULL,
    PRIMARY KEY (OUT_ITEM_DETAIL_PK),
    KEY FK_ITEM_TB_TO_OUT_ITEM_DETAIL_TB_1 (ITEM_PK),
    KEY FK_OUT_CART_TB_TO_OUT_ITEM_DETAIL_TB_1 (OUT_CART_PK),
    CONSTRAINT FK_ITEM_TB_TO_OUT_ITEM_DETAIL_TB_1 FOREIGN KEY (ITEM_PK) REFERENCES ITEM_TB (ITEM_PK),
    CONSTRAINT FK_OUT_CART_TB_TO_OUT_ITEM_DETAIL_TB_1 FOREIGN KEY (OUT_CART_PK) REFERENCES OUT_CART_TB (OUT_CART_PK)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 데이터 삽입
-- LOC_TB 더미 데이터 삽입
INSERT INTO
    LOC_TB (LOC_NM)
VALUES
    ('서울'),
    ('경기'),
    ('인천');

-- SEL_TB 더미 데이터 삽입
INSERT INTO
    SEL_TB (SEL_NM, SEL_ADR, LOC_PK)
VALUES
    ('쿠팡', '서울', 1),
    ('쿠팡', '경기', 2),
    ('쿠팡', '인천', 3),
    ('11번가', '서울', 1),
    ('11번가', '경기', 2),
    ('네이처리퍼블릭', '서울', 1),
    ('네이처리퍼블릭', '경기', 2),
    ('네이처리퍼블릭', '인천', 3),
    ('배민', '서울', 1),
    ('배민', '경기', 2),
    ('배민', '인천', 3),
    ('이마트', '서울', 1),
    ('이마트', '경기', 2),
    ('이마트', '인천', 3),
    ('현대백화점', '서울', 1),
    ('현대백화점', '경기', 2),
    ('현대백화점', '인천', 3);

-- CUS_TB 더미 데이터 삽입
INSERT INTO
    CUS_TB (CUS_NM, CUS_ADR)
VALUES
    ('유재석', '서울시 관악구 관악로 123'),
    ('정준하', '서울시 강서구 화곡로 456'),
    ('하하', '경기도 용인시 수지구 이종무로 157'),
    ('박명수', '경기도 수원시 장안구 정자로 41'),
    ('정형돈', '인천광역시 서구 서곶로 277');

-- FAC_TB 더미 데이터 삽입
INSERT INTO
    FAC_TB (FAC_NM, LOC_PK)
VALUES
    ('(주)아몰레퍼시픽', 1),
    ('(주)아몰레퍼시픽', 2),
    ('(주)아몰레퍼시픽', 3),
    ('(주)티디에스팜', 1),
    ('(주)티디에스팜', 2),
    ('(주)티디에스팜', 3),
    ('(주)텐텐화장품', 1),
    ('(주)텐텐화장품', 2),
    ('(주)텐텐화장품', 3);

-- WHS_TB 더미 데이터 삽입
INSERT INTO
    WHS_TB (WHS_NM, LOC_PK, WHS_CAPACITY)
VALUES
    ('서울 허브', 1, 100),
    ('경기 허브', 2, 200),
    ('인천 허브', 3, 350);

-- SEL_ORDER_TB 더미 데이터 삽입
INSERT INTO
    SEL_ORDER_TB (SEL_ORDER_DATE_DT, CUS_PK)
VALUES
    ('2024-05-18', 1),
    ('2024-05-19', 1),
    ('2024-05-19', 2),
    ('2024-05-19', 3);

-- SEL_ORDER_DETAIL_TB 더미 데이터 삽입
INSERT INTO
    SEL_ORDER_DETAIL_TB (SEL_ORDER_FK, SEL_ORDER_PROD_NM, SEL_ORDER_DETAIL_VOL, SEL_ORDER_DETAIL_CNT)
VALUES
    (1, '화산송이 클렌징폼', 150, 5),
    (1, '톤업 노세범 선스크린', 60, 2),
    (2, '물광 로션', 500, 10),
    (3, '텐텐 시그니쳐 퍼퓸', 50, 5),
    (3, '시카 바디워시', 340, 5);

-- OUTB_TB 더미 데이터 삽입
INSERT INTO
    OUTB_TB (CUS_FK, ORDER_DT)
VALUES
     (1, '2024-05-19'),
     (2, '2024-05-20'),
     (3, '2024-05-21');

-- OUTB_DETAIL_TB 더미 데이터 삽입
INSERT INTO
    OUTB_DETAIL_TB (OUTB_FK, ITEM_NM, OUTB_PICKING_CNT)
VALUES
    (1, '화산송이 클렌징폼', 5),
    (2, '톤업 노세범 선스크린', 2),
    (3, '물광 로션', 10),
    (4, '물광 로션', 5),
    (5, '시카 바디워시', 5);

-- OUT_CART_TB 더미 데이터 삽입
INSERT INTO
    OUT_CART_TB (OUTB_DETAIL_FK, OUTB_CART_CNT)
VALUES
    (1, 10),
    (2, 10),
    (3, 10),
    (4, 10),
    (5, 10),
    (6, 10);

-- INVOICE_TB 더미 데이터 삽입
INSERT INTO
    INVOICE_TB (INVOICE_NUM)
VALUES
    (00100100010519),
    (00200100020520),
    (00200200030521);

-- ITEM_CAT_TB 더미 데이터
-- ITEM_CAT_TB 더미 데이터 삽입
INSERT INTO
    ITEM_CAT_TB (ITEM_CAT_NM)
VALUES
    ('스킨케어'),
    ('선케어'),
    ('클렌징'),
    ('마스크팩'),
    ('향수'),
    ('헤어케어'),
    ('바디케어'),
    ('팩'),
    ('메이크업');

-- ITEM_TB 더미 데이터 삽입
INSERT INTO
    ITEM_TB (ITEM_NM, ITEM_VOL, ITEM_CAT_PK, ITEM_CNT)
VALUES
    ('화산송이 클렌징폼', 150, 3, 5),
    ('톤업 노세범 선스크린', 60, 2),
    ('물광 로션', 500, 1, 3),
    ('에센셜 마스크팩', 24, 4, 0),
    ('텐텐 시그니쳐 퍼퓸', 50, 5, 1),
    ('두피 클리닉 샴푸', 300, 6, 4),
    ('시카 바디워시', 340, 7, 0),
    ('히알루로산 마스크팩', 27, 8, 5),
    ('피스 아이섀도우', 2, 9, 2),
    ('무스 리퀴드 아이라이너', 1, 9, 3);

-- INB_TB 데이터 삽입
INSERT INTO
    INB_TB (SEL_PK, FAC_PK, WHS_PK, INB_ITEM_PK, INB_ITEM_CAT_PK, INB_ITEM_NM, INB_ITEM_VOL, INB_ITEM_PRICE, INB_ITEM_CNT, INB_ITEM_EXPIRATION_DT)
VALUES
    (1, 1, 1, 1, 3, '화산송이 클렌징폼', 150, 6500, 5, '2024-09-14'),
    (4, 2, 2, 2, 2, '톤업 노세범 선스크린', 60, 24000, 3, '2024-05-25'),
    (18, 3, 3, 3, 1, '물광 로션', 500, 23500, 10, '2026-05-16'),
    (8, 4, 1, 4, 4, '에센셜 마스크팩', 24, 3500, 5, '2026-05-17'),
    (9, 4, 1, 5, 5, '텐텐 시그니쳐 퍼퓸', 50, 60000, 3, '2026-06-01'),
    (12, 5, 2, 6, 6, '두피 클리닉 샴푸', 300, 18000, 5, '2026-05-01'),
    (12, 7, 1, 7, 7, '시카 바디워시', 340, 12000, 10, '2025-07-06'),
    (14, 8, 2, 8, 8, '히알루론산 마스크팩', 27, 2500, 5, '2024-10-12'),
    (15, 9, 3, 9, 9, '피스 아이섀도우', 2, 28000, 10, '2026-11-04'),
    (16, 4, 1, 10, 9, '무스 리퀴드 아이라이너', 1, 12600, 20, '2026-07-05');

-- GBG_DETAIL_TB 데이터 삽입
INSERT INTO
    GBG_DETAIL_TB (ITEM_PK, ITEM_STATE, GBG_SERIAL_NUM)
VALUES
    (1, 2, '화산송이 클렌징폼-1-001-250101-0005'),
    (3, 3, '물광 로션-1-002-240510-0002');

-- WHS_ADMIN_TB 데이터 삽입
INSERT INTO
    WHS_ADMIN_TB (WHS_EMAIL, WHS_PW, WHS_NM, WHS_PK)
VALUES
    ('kdh99@naver.com', 'kdh1234', '김도현', 1),
    ('njy00@naver.com', '01njy34', '나지영', 2),
    ('psb99@gmail.com', '99psb', '박수빈', 3);

-- LOCATE_TB 데이터 삽입
INSERT INTO
    LOCATE_TB (LOCATE_ITEM_CNT, LOCATE_LPN_CODE, WHS_PK)
VALUES
    (5, 'LOC0001', 1), (25, 'LOC0001', 2), (10, 'LOC0001', 3),
    (3, 'LOC0002', 1), (50, 'LOC0002', 2), (50, 'LOC0002', 3),
    (7, 'LOC0003', 1), (7, 'LOC0003', 2), (7, 'LOC0003', 3),
    (10, 'LOC0004', 1), (10, 'LOC0004', 2), (10, 'LOC0004', 3),
    (20, 'LOC0005', 1), (20, 'LOC0005', 2), (20, 'LOC0005', 3),
    (30, 'LOC0006', 1), (30, 'LOC0006', 2), (30, 'LOC0006', 3),
    (40, 'LOC0007', 1), (40, 'LOC0007', 2), (40, 'LOC0007', 3),
    (50, 'LOC0008', 1), (50, 'LOC0008', 2), (50, 'LOC0008', 3),
    (1, 'LOC0009', 1), (1, 'LOC0009', 2), (1, 'LOC0009', 3),
    (2, 'LOC0010', 1), (2, 'LOC0010', 2), (2, 'LOC0010', 3),
    (3, 'LOC0011', 1), (3, 'LOC0011', 2), (3, 'LOC0011', 3),
    (4, 'LOC0012', 1), (4, 'LOC0012', 2), (4, 'LOC0012', 3),
    (7, 'LOC0013', 1), (7, 'LOC0013', 2), (7, 'LOC0013', 3),
    (5, 'LOC0014', 1), (5, 'LOC0014', 2), (5, 'LOC0014', 3),
    (44, 'LOC0015', 1), (44, 'LOC0015', 2), (44, 'LOC0015', 3),
    (35, 'LOC0016', 1), (35, 'LOC0016', 2), (35, 'LOC0016', 3),
    (47, 'LOC0017', 1), (47, 'LOC0017', 2), (47, 'LOC0017', 3),
    (25, 'LOC0018', 1), (25, 'LOC0018', 2), (25, 'LOC0018', 3),
    (36, 'LOC0019', 1), (36, 'LOC0019', 2), (36, 'LOC0019', 3),
    (29, 'LOC0020', 1), (29, 'LOC0020', 2), (29, 'LOC0020', 3),
    (17, 'LOC0021', 1), (17, 'LOC0021', 2), (17, 'LOC0021', 3),
    (49, 'LOC0022', 1), (49, 'LOC0022', 2), (49, 'LOC0022', 3),
    (3, 'LOC0023', 1), (3, 'LOC0023', 2), (3, 'LOC0023', 3),
    (5, 'LOC0024', 1), (5, 'LOC0024', 2), (5, 'LOC0024', 3),
    (10, 'LOC0025', 1), (10, 'LOC0025', 2), (10, 'LOC0025', 3),
    (5, 'LOC0026', 1), (5, 'LOC0026', 2), (5, 'LOC0026', 3),
    (45, 'LOC0027', 1), (45, 'LOC0027', 2), (45, 'LOC0027', 3),
    (23, 'LOC0028', 1), (23, 'LOC0028', 2), (23, 'LOC0028', 3),
    (16, 'LOC0029', 1), (16, 'LOC0029', 2), (16, 'LOC0029', 3),
    (4, 'LOC0030', 1), (4, 'LOC0030', 2), (4, 'LOC0030', 3),
    (5, 'LOC0031', 1), (5, 'LOC0031', 2), (5, 'LOC0031', 3),
    (23, 'LOC0032', 1), (23, 'LOC0032', 2), (23, 'LOC0032', 3),
    (12, 'LOC0033', 1), (12, 'LOC0033', 2), (12, 'LOC0033', 3),
    (30, 'LOC0034', 1), (30, 'LOC0034', 2), (30, 'LOC0034', 3),
    (33, 'LOC0035', 1), (33, 'LOC0035', 2), (33, 'LOC0035', 3),
    (22, 'LOC0036', 1), (22, 'LOC0036', 2), (22, 'LOC0036', 3),
    (46, 'LOC0037', 1), (46, 'LOC0037', 2), (46, 'LOC0037', 3),
    (5, 'LOC0038', 1), (5, 'LOC0038', 2), (5, 'LOC0038', 3),
    (1, 'LOC0039', 1), (1, 'LOC0039', 2), (1, 'LOC0039', 3),
    (0, 'LOC0040', 1), (0, 'LOC0040', 2), (0, 'LOC0040', 3),
    (0, 'LOC0041', 1), (0, 'LOC0041', 2), (0, 'LOC0041', 3),
    (0, 'LOC0042', 1), (0, 'LOC0042', 2), (0, 'LOC0042', 3),
    (0, 'LOC0043', 1), (0, 'LOC0043', 2), (0, 'LOC0043', 3),
    (1, 'LOC0044', 1), (1, 'LOC0044', 2), (1, 'LOC0044', 3),
    (0, 'LOC0045', 1), (0, 'LOC0045', 2), (0, 'LOC0045', 3),
    (5, 'LOC0046', 1), (5, 'LOC0046', 2), (5, 'LOC0046', 3),
    (1, 'LOC0047', 1), (1, 'LOC0047', 2), (1, 'LOC0047', 3),
    (7, 'LOC0048', 1), (7, 'LOC0048', 2), (7, 'LOC0048', 3),
    (5, 'LOC0049', 1), (5, 'LOC0049', 2), (5, 'LOC0049', 3),
    (9, 'LOC0050', 1), (9, 'LOC0050', 2), (9, 'LOC0050', 3),
    (8, 'LOC0051', 1), (8, 'LOC0051', 2), (8, 'LOC0051', 3),
    (24, 'LOC0052',1), (24, 'LOC0052',2), (24, 'LOC0052',3),
    (9, 'LOC0053', 1), (9, 'LOC0053', 2), (9, 'LOC0053', 3),
    (0, 'LOC0054', 1), (0, 'LOC0054', 2), (0, 'LOC0054', 3),
    (0, 'LOC0055', 1), (0, 'LOC0055', 2), (0, 'LOC0055', 3),
    (41, 'LOC0056', 1), (41, 'LOC0056', 2), (41, 'LOC0056', 3),
    (12, 'LOC0057', 1), (12, 'LOC0057', 2), (12, 'LOC0057', 3),
    (0, 'LOC0058', 1), (0, 'LOC0058', 2), (0, 'LOC0058', 3),
    (4, 'LOC0059', 1), (4, 'LOC0059', 2), (4, 'LOC0059', 3),
    (6, 'LOC0060', 1), (6, 'LOC0060', 2), (6, 'LOC0060', 3),
    (0, 'LOC0061', 1), (0, 'LOC0061', 2), (0, 'LOC0061', 3),
    (7, 'LOC0062', 1), (7, 'LOC0062', 2), (7, 'LOC0062', 3),
    (23, 'LOC0063', 1), (23, 'LOC0063', 2), (23, 'LOC0063', 3),
    (22, 'LOC0064', 1), (22, 'LOC0064', 2), (22, 'LOC0064', 3),
    (0, 'LOC0065', 1), (0, 'LOC0065', 2), (0, 'LOC0065', 3),
    (45, 'LOC0066', 1), (45, 'LOC0066', 2), (45, 'LOC0066', 3),
    (11, 'LOC0067', 1), (11, 'LOC0067', 2), (11, 'LOC0067', 3),
    (19, 'LOC0068', 1), (19, 'LOC0068', 2), (19, 'LOC0068', 3),
    (8, 'LOC0069', 1), (8, 'LOC0069', 2), (8, 'LOC0069', 3),
    (0, 'LOC0070', 1), (0, 'LOC0070', 2), (0, 'LOC0070', 3),
    (3, 'LOC0071', 1), (3, 'LOC0071', 2), (3, 'LOC0071', 3),
    (5, 'LOC0072', 1), (5, 'LOC0072', 2), (5, 'LOC0072', 3),
    (0, 'LOC0073', 1), (0, 'LOC0073', 2), (0, 'LOC0073', 3),
    (0, 'LOC0074', 1), (0, 'LOC0074', 2), (0, 'LOC0074', 3),
    (0, 'LOC0075', 1), (0, 'LOC0075', 2), (0, 'LOC0075', 3),
    (0, 'LOC0076', 1), (0, 'LOC0076', 2), (0, 'LOC0076', 3),
    (0, 'LOC0077', 1), (0, 'LOC0077', 2), (0, 'LOC0077', 3),
    (4, 'LOC0078', 1), (4, 'LOC0078', 2), (4, 'LOC0078', 3),
    (3, 'LOC0079', 1), (3, 'LOC0079', 2), (3, 'LOC0079', 3),
    (7, 'LOC0080', 1), (7, 'LOC0080', 2), (7, 'LOC0080', 3),
    (5, 'LOC0081', 1), (5, 'LOC0081', 2), (5, 'LOC0081', 3),
    (6, 'LOC0082', 1), (6, 'LOC0082', 2), (6, 'LOC0082', 3),
    (8, 'LOC0083', 1), (8, 'LOC0083', 2), (8, 'LOC0083', 3),
    (11, 'LOC0084', 1), (11, 'LOC0084', 2), (11, 'LOC0084', 3),
    (42, 'LOC0085', 1), (42, 'LOC0085', 2), (42, 'LOC0085', 3),
    (3, 'LOC0086', 1), (3, 'LOC0086', 2), (3, 'LOC0086', 3),
    (33, 'LOC0087', 1), (33, 'LOC0087', 2), (33, 'LOC0087', 3),
    (2, 'LOC0088', 1), (2, 'LOC0088', 2), (2, 'LOC0088', 3),
    (0, 'LOC0089', 1), (0, 'LOC0089', 2), (5, 'LOC0089', 3),
    (0, 'LOC0090', 1), (0, 'LOC0090', 2), (0, 'LOC0090', 3),
    (0, 'LOC0091', 1), (0, 'LOC0091', 2), (0, 'LOC0091', 3),
    (0, 'LOC0092', 1), (0, 'LOC0092', 2), (0, 'LOC0092', 3),
    (1, 'LOC0093', 1), (1, 'LOC0093', 2), (1, 'LOC0093', 3),
    (0, 'LOC0094', 1), (0, 'LOC0094', 2), (0, 'LOC0094', 3),
    (0, 'LOC0095', 1), (0, 'LOC0095', 2), (0, 'LOC0095', 3),
    (0, 'LOC0096', 1), (0, 'LOC0096', 2), (0, 'LOC0096', 3),
    (0, 'LOC0097', 1), (0, 'LOC0097', 2), (0, 'LOC0097', 3),
    (0, 'LOC0098', 1), (0, 'LOC0098', 2), (0, 'LOC0098', 3),
    (0, 'LOC0099', 1), (0, 'LOC0099', 2), (0, 'LOC0099', 3),
    (0, 'LOC0100', 1), (0, 'LOC0100', 2), (0, 'LOC0100', 3),
    (25, 'LOC0101', 1), (25, 'LOC0101', 2), (25, 'LOC0101', 3),
    (50, 'LOC0102', 1), (50, 'LOC0102', 2), (50, 'LOC0102', 3),
    (7, 'LOC0103', 1), (7, 'LOC0103', 2), (7, 'LOC0103', 3),
    (0, 'LOC0104', 1), (0, 'LOC0104', 2), (0, 'LOC0104', 3),
    (0, 'LOC0105', 1), (0, 'LOC0105', 2), (0, 'LOC0105', 3),
    (0, 'LOC0106', 1), (0, 'LOC0106', 2), (0, 'LOC0106', 3),
    (0, 'LOC0107', 1), (0, 'LOC0107', 2), (0, 'LOC0107', 3),
    (0, 'LOC0108', 1), (0, 'LOC0108', 2), (0, 'LOC0108', 3),
    (0, 'LOC0109', 1), (0, 'LOC0109', 2), (0, 'LOC0109', 3),
    (5, 'LOC0110', 1), (5, 'LOC0110', 2), (5, 'LOC0110', 3),
    (0, 'LOC0111', 1), (10, 'LOC0111', 2), (0, 'LOC0111', 3),
    (0, 'LOC0112', 1), (0, 'LOC0112', 2), (0, 'LOC0112', 3),
    (0, 'LOC0113', 1), (0, 'LOC0113', 2), (0, 'LOC0113', 3),
    (22, 'LOC0114', 1), (22, 'LOC0114', 2), (22, 'LOC0114', 3),
    (0, 'LOC0115', 1), (0, 'LOC0115', 2), (0, 'LOC0115', 3),
    (0, 'LOC0116', 1), (0, 'LOC0116', 2), (0, 'LOC0116', 3),
    (0, 'LOC0117', 1), (0, 'LOC0117', 2), (0, 'LOC0117', 3),
    (0, 'LOC0118', 1), (0, 'LOC0118', 2), (0, 'LOC0118', 3),
    (0, 'LOC0119', 1), (0, 'LOC0119', 2), (0, 'LOC0119', 3),
    (0, 'LOC0120', 1), (0, 'LOC0120', 2), (0, 'LOC0120', 3),
    (0, 'LOC0121', 1), (0, 'LOC0121', 2), (0, 'LOC0121', 3),
    (0, 'LOC0122', 1), (5, 'LOC0122', 2), (10, 'LOC0122', 3),
    (0, 'LOC0123', 1), (5, 'LOC0123', 2), (0, 'LOC0123', 3),
    (0, 'LOC0124', 1), (0, 'LOC0124', 2), (0, 'LOC0124', 3),
    (0, 'LOC0125', 1), (0, 'LOC0125', 2), (0, 'LOC0125', 3),
    (6, 'LOC0126', 1), (6, 'LOC0126', 2), (6, 'LOC0126', 3),
    (0, 'LOC0127', 1), (0, 'LOC0127', 2), (0, 'LOC0127', 3),
    (0, 'LOC0128', 1), (0, 'LOC0128', 2), (0, 'LOC0128', 3),
    (0, 'LOC0129', 1), (0, 'LOC0129', 2), (0, 'LOC0129', 3),
    (0, 'LOC0130', 1), (0, 'LOC0130', 2), (0, 'LOC0130', 3),
    (0, 'LOC0131', 1), (0, 'LOC0131', 2), (0, 'LOC0131', 3),
    (0, 'LOC0132', 1), (0, 'LOC0132', 2), (0, 'LOC0132', 3),
    (0, 'LOC0133', 1), (0, 'LOC0133', 2), (0, 'LOC0133', 3),
    (0, 'LOC0134', 1), (0, 'LOC0134', 2), (0, 'LOC0134', 3),
    (0, 'LOC0135', 1), (0, 'LOC0135', 2), (0, 'LOC0135', 3),
    (0, 'LOC0136', 1), (0, 'LOC0136', 2), (0, 'LOC0136', 3),
    (0, 'LOC0137', 1), (0, 'LOC0137', 2), (0, 'LOC0137', 3),
    (0, 'LOC0138', 1), (0, 'LOC0138', 2), (0, 'LOC0138', 3),
    (0, 'LOC0139', 1), (0, 'LOC0139', 2), (0, 'LOC0139', 3),
    (0, 'LOC0140', 1), (0, 'LOC0140', 2), (0, 'LOC0140', 3),
    (0, 'LOC0141', 1), (0, 'LOC0141', 2), (0, 'LOC0141', 3),
    (0, 'LOC0142', 1), (0, 'LOC0142', 2), (0, 'LOC0142', 3),
    (0, 'LOC0143', 1), (0, 'LOC0143', 2), (0, 'LOC0143', 3),
    (0, 'LOC0144', 1), (0, 'LOC0144', 2), (0, 'LOC0144', 3),
    (0, 'LOC0145', 1), (0, 'LOC0145', 2), (0, 'LOC0145', 3),
    (0, 'LOC0146', 1), (0, 'LOC0146', 2), (0, 'LOC0146', 3),
    (0, 'LOC0147', 1), (0, 'LOC0147', 2), (0, 'LOC0147', 3),
    (0, 'LOC0148', 1), (0, 'LOC0148', 2), (0, 'LOC0148', 3),
    (0, 'LOC0149', 1), (0, 'LOC0149', 2), (0, 'LOC0149', 3),
    (0, 'LOC0150', 1), (0, 'LOC0150', 2), (0, 'LOC0150', 3),
    (0, 'LOC0151', 1), (0, 'LOC0151', 2), (0, 'LOC0151', 3),
    (0, 'LOC0152', 1), (0, 'LOC0152', 2), (0, 'LOC0152', 3),
    (0, 'LOC0153', 1), (0, 'LOC0153', 2), (0, 'LOC0153', 3),
    (0, 'LOC0154', 1), (0, 'LOC0154', 2), (0, 'LOC0154', 3),
    (0, 'LOC0155', 1), (0, 'LOC0155', 2), (0, 'LOC0155', 3),
    (0, 'LOC0156', 1), (0, 'LOC0156', 2), (0, 'LOC0156', 3),
    (0, 'LOC0157', 1), (3, 'LOC0157', 2), (0, 'LOC0157', 3),
    (0, 'LOC0158', 1), (0, 'LOC0158', 2), (0, 'LOC0158', 3),
    (0, 'LOC0159', 1), (0, 'LOC0159', 2), (0, 'LOC0159', 3),
    (0, 'LOC0160', 1), (0, 'LOC0160', 2), (0, 'LOC0160', 3),
    (0, 'LOC0161', 1), (0, 'LOC0161', 2), (0, 'LOC0161', 3),
    (0, 'LOC0162', 1), (0, 'LOC0162', 2), (0, 'LOC0162', 3),
    (0, 'LOC0163', 1), (0, 'LOC0163', 2), (0, 'LOC0163', 3),
    (0, 'LOC0164', 1), (0, 'LOC0164', 2), (0, 'LOC0164', 3),
    (0, 'LOC0165', 1), (0, 'LOC0165', 2), (0, 'LOC0165', 3),
    (0, 'LOC0166', 1), (0, 'LOC0166', 2), (0, 'LOC0166', 3),
    (0, 'LOC0167', 1), (0, 'LOC0167', 2), (0, 'LOC0167', 3),
    (0, 'LOC0168', 1), (0, 'LOC0168', 2), (0, 'LOC0168', 3),
    (0, 'LOC0169', 1), (0, 'LOC0169', 2), (0, 'LOC0169', 3),
    (0, 'LOC0170', 1), (0, 'LOC0170', 2), (0, 'LOC0170', 3),
    (0, 'LOC0171', 1), (0, 'LOC0171', 2), (0, 'LOC0171', 3),
    (0, 'LOC0172', 1), (0, 'LOC0172', 2), (0, 'LOC0172', 3),
    (0, 'LOC0173', 1), (0, 'LOC0173', 2), (0, 'LOC0173', 3),
    (0, 'LOC0174', 1), (0, 'LOC0174', 2), (0, 'LOC0174', 3),
    (0, 'LOC0175', 1), (0, 'LOC0175', 2), (0, 'LOC0175', 3),
    (0, 'LOC0176', 1), (0, 'LOC0176', 2), (0, 'LOC0176', 3),
    (0, 'LOC0177', 1), (0, 'LOC0177', 2), (0, 'LOC0177', 3),
    (0, 'LOC0178', 1), (0, 'LOC0178', 2), (0, 'LOC0178', 3),
    (0, 'LOC0179', 1), (0, 'LOC0179', 2), (0, 'LOC0179', 3),
    (0, 'LOC0180', 1), (0, 'LOC0180', 2), (0, 'LOC0180', 3),
    (0, 'LOC0181', 1), (0, 'LOC0181', 2), (0, 'LOC0181', 3),
    (0, 'LOC0182', 1), (0, 'LOC0182', 2), (0, 'LOC0182', 3),
    (0, 'LOC0183', 1), (0, 'LOC0183', 2), (0, 'LOC0183', 3),
    (0, 'LOC0184', 1), (0, 'LOC0184', 2), (0, 'LOC0184', 3),
    (0, 'LOC0185', 1), (0, 'LOC0185', 2), (0, 'LOC0185', 3),
    (0, 'LOC0186', 1), (0, 'LOC0186', 2), (0, 'LOC0186', 3),
    (0, 'LOC0187', 1), (0, 'LOC0187', 2), (0, 'LOC0187', 3),
    (0, 'LOC0188', 1), (0, 'LOC0188', 2), (0, 'LOC0188', 3),
    (0, 'LOC0189', 1), (0, 'LOC0189', 2), (0, 'LOC0189', 3),
    (0, 'LOC0190', 1), (0, 'LOC0190', 2), (0, 'LOC0190', 3),
    (0, 'LOC0191', 1), (0, 'LOC0191', 2), (0, 'LOC0191', 3),
    (0, 'LOC0192', 1), (0, 'LOC0192', 2), (0, 'LOC0192', 3),
    (0, 'LOC0193', 1), (0, 'LOC0193', 2), (0, 'LOC0193', 3),
    (0, 'LOC0194', 1), (0, 'LOC0194', 2), (0, 'LOC0194', 3),
    (0, 'LOC0195', 1), (0, 'LOC0195', 2), (0, 'LOC0195', 3),
    (0, 'LOC0196', 1), (0, 'LOC0196', 2), (0, 'LOC0196', 3),
    (0, 'LOC0197', 1), (0, 'LOC0197', 2), (0, 'LOC0197', 3),
    (0, 'LOC0198', 1), (0, 'LOC0198', 2), (0, 'LOC0198', 3),
    (0, 'LOC0199', 1), (0, 'LOC0199', 2), (0, 'LOC0199', 3),
    (0, 'LOC0200', 1), (0, 'LOC0200', 2), (0, 'LOC0200', 3);

-- OUT_ITEM_DETAIL_TB 데이터 삽입
INSERT INTO
    OUT_ITEM_DETAIL_TB (ITEM_STATE, ITEM_PK, ITEM_EXPIRATION_DT, ITEM_DETAIL_SERIAL_NUM, OUT_CART_PK)
VALUES
    (1, 1, '2025-05-14', '화산송이 클렌징폼-1-LOC0001-250514-0004', 1),
    (3, 2, '2024-04-10', '톤업 노세범 선스크린-1-LOC0002-240401-0009', 2),
    (3, 3, '2024-05-30', '물광 로션-1-LOC0004-240730-0001', 3),
    (2, 4, '2024-12-31', '에센셜 마스크팩-2-LOC0122-241231-0824', 4),
    (3, 5, '2025-07-07', '텐텐 시그니쳐 퍼퓸-2-LOC0157-250707-0504', 5),
    (1, 6, '2025-02-05', '두피 클리닉 샴푸-2-LOC0123-250205-0021', 6);

-- ITEM_DETAIL_TB 데이터 삽입
INSERT INTO
    ITEM_DETAIL_TB (ITEM_DETAIL_SERIAL_NUM, ITEM_PK, ITEM_DETAIL_STATUS, LOCATE_PK, ITEM_DETAIL_EXPIRATION_DT)
VALUES
    ('화산송이 클렌징폼-1-LOC0001-250514-0005', 1, 1, 1, '2025-05-14'),
    ('화산송이 클렌징폼-1-LOC0001-250514-0006', 1, 1, 1, '2025-05-14'),
    ('화산송이 클렌징폼-1-LOC0001-250514-0007', 1, 2, 1, '2025-05-14'),
    ('화산송이 클렌징폼-1-LOC0001-250514-0008', 1, 2, 1, '2025-05-14'),
    ('화산송이 클렌징폼-1-LOC0001-250514-0009', 1, 2, 1, '2025-05-14'),
    ('톤업 노세범 선스크린-1-LOC0002-240401-0010', 2, 3, 1, '2024-04-10'),
    ('톤업 노세범 선스크린-1-LOC0002-240401-0011', 2, 3, 1, '2024-04-10'),
    ('톤업 노세범 선스크린-1-LOC0002-240401-0012', 2, 3, 1, '2024-04-10'),
    ('물광 로션-1-LOC0004-240530-0002', 3, 3, 1, '2024-05-30'),
    ('물광 로션-1-LOC0004-240530-0003', 3, 3, 1, '2024-05-30'),
    ('물광 로션-1-LOC0004-240530-0004', 3, 3, 1, '2024-05-30'),
    ('물광 로션-1-LOC0004-240730-0005', 3, 2, 1, '2024-07-30'),
    ('물광 로션-1-LOC0004-240730-0006', 3, 2, 1, '2024-07-30'),
    ('물광 로션-1-LOC0004-240730-0007', 3, 2, 1, '2024-07-30'),
    ('물광 로션-1-LOC0004-240730-0008', 3, 1, 1, '2024-07-30'),
    ('물광 로션-1-LOC0004-240730-0009', 3, 1, 1, '2024-07-30'),
    ('물광 로션-1-LOC0004-240730-0010', 3, 1, 1, '2024-07-30'),
    ('물광 로션-1-LOC0004-240730-0011', 3, 1, 1, '2024-07-30'),
    ('에센셜 마스크팩-2-LOC0122-241231-0825', 4, 1, 2, '2024-12-31'),
    ('에센셜 마스크팩-2-LOC0122-241231-0826', 4, 1, 2, '2024-12-31'),
    ('에센셜 마스크팩-2-LOC0122-241231-0827', 4, 1, 2, '2024-12-31'),
    ('에센셜 마스크팩-2-LOC0122-241231-0828', 4, 1, 2, '2024-12-31'),
    ('에센셜 마스크팩-2-LOC0122-241231-0829', 4, 1, 2, '2024-12-31'),
    ('텐텐 시그니쳐 퍼퓸-2-LOC0157-250707-0505', 5, 2, 2, '2025-07-07'),
    ('텐텐 시그니쳐 퍼퓸-2-LOC0157-250707-0506', 5, 2, 2, '2025-07-07'),
    ('텐텐 시그니쳐 퍼퓸-2-LOC0157-250707-0507', 5, 2, 2, '2025-07-07'),
    ('두피 클리닉 샴푸-2-LOC0123-250205-0022', 6, 1, 2, '2025-02-05'),
    ('두피 클리닉 샴푸-2-LOC0123-250205-0023', 6, 1, 2, '2025-02-05'),
    ('두피 클리닉 샴푸-2-LOC0123-250205-0024', 6, 1, 2, '2025-02-05'),
    ('두피 클리닉 샴푸-2-LOC0123-250205-0025', 6, 1, 2, '2025-02-05'),
    ('두피 클리닉 샴푸-2-LOC0123-250205-0026', 6, 1, 2, '2025-02-05'),
    ('시카 바디워시-2-LOC0111-250319-0070', 7, 1, 2, '2025-03-19'),
    ('시카 바디워시-2-LOC0111-250319-0071', 7, 1, 2, '2025-03-19'),
    ('시카 바디워시-2-LOC0111-250319-0072', 7, 1, 2, '2025-03-19'),
    ('시카 바디워시-2-LOC0111-250319-0073', 7, 1, 2, '2025-03-19'),
    ('시카 바디워시-2-LOC0111-250319-0074', 7, 1, 2, '2025-03-19'),
    ('시카 바디워시-2-LOC0111-250319-0075', 7, 1, 2, '2025-03-19'),
    ('시카 바디워시-2-LOC0111-250319-0076', 7, 1, 2, '2025-03-19'),
    ('시카 바디워시-2-LOC0111-250319-0077', 7, 1, 2, '2025-03-19'),
    ('시카 바디워시-2-LOC0111-250319-0078', 7, 1, 2, '2025-03-19'),
    ('시카 바디워시-2-LOC0111-250319-0079', 7, 1, 2, '2025-03-19'),
    ('히알루론산 마스크팩-3-LOC0089-240607-0158', 8, 3, 3, '2024-06-07'),
    ('히알루론산 마스크팩-3-LOC0089-240607-0159', 8, 3, 3, '2024-06-07'),
    ('히알루론산 마스크팩-3-LOC0089-241007-0160', 8, 1, 3, '2024-10-07'),
    ('히알루론산 마스크팩-3-LOC0089-241007-0161', 8, 1, 3, '2024-10-07'),
    ('히알루론산 마스크팩-3-LOC0089-241007-0162', 8, 1, 3, '2024-10-07'),
    ('피스 아이섀도우-3-LOC0122-241231-0158', 9, 1, 3, '2024-12-31'),
    ('피스 아이섀도우-3-LOC0122-241231-0159', 9, 1, 3, '2024-12-31'),
    ('피스 아이섀도우-3-LOC0122-241231-0160', 9, 1, 3, '2024-12-31'),
    ('피스 아이섀도우-3-LOC0122-241231-0161', 9, 1, 3, '2024-12-31'),
    ('피스 아이섀도우-3-LOC0122-241231-0162', 9, 1, 3, '2024-12-31'),
    ('피스 아이섀도우-3-LOC0122-241231-0163', 9, 1, 3, '2024-12-31'),
    ('피스 아이섀도우-3-LOC0122-241231-0164', 9, 1, 3, '2024-12-31'),
    ('피스 아이섀도우-3-LOC0122-241231-0165', 9, 1, 3, '2024-12-31'),
    ('피스 아이섀도우-3-LOC0122-241231-0166', 9, 1, 3, '2024-12-31'),
    ('피스 아이섀도우-3-LOC0122-241231-0167', 9, 1, 3, '2024-12-31'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0001', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0002', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0003', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0004', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0005', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0006', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0007', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0008', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0009', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0010', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0011', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0012', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0013', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0014', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0015', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0016', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0017', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0018', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0019', 10, 1, 3, '2024-05-20'),
    ('무스 리퀴드 아이라이너-3-LOC0001-240520-0020', 10, 1, 3, '2024-05-20');

