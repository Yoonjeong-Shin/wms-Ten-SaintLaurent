package com.sh.model.dao;

import com.sh.model.dto.ItemCatDto;
import com.sh.model.dto.ItemDetailDto;
import com.sh.model.dto.ItemDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SupervisionMapperTest {
    SqlSession sqlSession;
    SupervisionMapper superMapper;

    @BeforeEach
    void setUp() {
        this.sqlSession = getSqlSession();
        this.superMapper = this.sqlSession.getMapper(SupervisionMapper.class);
    }

    @AfterEach
    void tearDown() {
        this.sqlSession.close();
    }

    @DisplayName("item 테이블에 화장품을 추가한다.")
    @Test
    void insertItemTb() {
        // given
        String itemNm = "텐생로랑 토너";
        int itemCat = 1;
        int itemVol = 350;
        ItemDto itemDto = new ItemDto(0, itemNm, itemCat, itemVol);

        // when
        int result = superMapper.insertItem(itemDto);

        // then
        assertThat(result).isEqualTo(1);
        long itemCode = itemDto.getItemIdPk();
        System.out.println(itemCode);
        assertThat(itemCode).isNotZero();
    }

    @DisplayName("itemDetail 테이블에 화장품을 추가한다.")
    @Test
    void insertDetailItemTb() {
        // given
        long itemIdPk = 1;
        int itemDetailStatus = 1;
        long locateIdPk = 4;
        String lpn = String.format("%03d",locateIdPk) + "001";
        LocalDate itemDetailExpirationDt = LocalDate.now();
        String date = itemDetailExpirationDt.format(DateTimeFormatter.ofPattern("yyMMdd"));
        String itemDetailSerialNum = itemIdPk + "_" + lpn + "-" + date + "-0001"; // 화장품 ID(숫자) + 창고 ID-LPN + 유통기한 + 제조번호
        System.out.println(itemDetailSerialNum);
        ItemDetailDto itemDetailDto = new ItemDetailDto(0, itemIdPk, itemDetailStatus, locateIdPk, itemDetailExpirationDt, itemDetailSerialNum);

        // when
        int result = superMapper.insertDetailItem(itemDetailDto);

        // then
        assertThat(result).isEqualTo(1);
        long itemDetailPk = itemDetailDto.getItemDetailPk();
        System.out.println(itemDetailPk);
        assertThat(itemDetailPk).isNotZero();
    }

    @DisplayName("itemCat 테이블에 화장품을 추가한다.")
    @Test
    void insertItemCatTb() {
        // given
        String itemCatNm = "스킨케어";
        ItemCatDto itemCatDto = new ItemCatDto();
        itemCatDto.setItemCatNm(itemCatNm);
        // when
        int cnt = superMapper.searchItemCat(itemCatNm);
        System.out.println(cnt);
        int result;
//        if(cnt == 0) {
            result = superMapper.insertCatItem(itemCatDto);
//        } else {
//            result = 0;
//        }
        // then
        assertThat(result).isEqualTo(1);
    }

    @DisplayName("화장품의 모든 정보를 조회한다.")
    @Test
    void searchItemInfo() {
        // given
        // when
        // then
    }

    @DisplayName("한 화장품의 개수를 조회한다.")
    @Test
    void searchItemCnt() {
        // given
        String itemNm = "Lotion";
        // when
        int cnt = superMapper.searchItemCnt(itemNm);
        System.out.println(cnt);
        // then
        assertThat(cnt)
                .isNotZero()
                .isPositive();
    }
}