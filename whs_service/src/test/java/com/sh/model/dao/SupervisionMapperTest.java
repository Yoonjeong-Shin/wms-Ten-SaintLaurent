package com.sh.model.dao;

import com.sh.model.dto.*;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        this.sqlSession.commit();
//        this.sqlSession.rollback();
        this.sqlSession.close();
    }

    @DisplayName("item 테이블에 화장품을 추가한다.")
    @Test
    void insertItemTb() {
        // given
        String itemNm = "텐생로랑 토너6";
        int itemVol = 350;
        int itemCatPk = 1;
        ItemDto itemDto = new ItemDto(0, itemNm, itemVol, itemCatPk);

        // when
        int result = superMapper.insertItem(itemDto);

        // then
        assertThat(result).isEqualTo(1);
        long itemCode = itemDto.getItemPk();
        System.out.println(itemCode);
        assertThat(itemCode).isNotZero();
    }

    @DisplayName("itemDetail 테이블에 화장품을 추가한다.")
    @Test
    void insertDetailItemTb() {
        // given
        long itemPk = 1;
        int itemDetailStatus = 1;
        long locatePk = 4;
        String lpn = String.format("%03d",locatePk) + "001";
        LocalDate itemDetailExpirationDt = LocalDate.now();
        String date = itemDetailExpirationDt.format(DateTimeFormatter.ofPattern("yyMMdd"));
        String itemDetailSerialNum = itemPk + "_" + lpn + "-" + date + "-0001"; // 화장품 ID(숫자) + 창고 ID-LPN + 유통기한 + 제조번호
        System.out.println(itemDetailSerialNum);
        ItemDetailDto itemDetailDto = new ItemDetailDto(0, itemDetailSerialNum, itemPk, itemDetailStatus, locatePk, itemDetailExpirationDt);

        // when
        int result = superMapper.insertDetailItem(itemDetailDto);

        // then
        assertThat(result).isEqualTo(1);
        long itemDetailPk = itemDetailDto.getItemDetailPk();
        System.out.println(itemDetailPk);
        assertThat(itemDetailPk).isNotZero();
    }

    @Disabled
    @DisplayName("itemCat 테이블에 화장품을 추가한다.")
    @Test
    void insertItemCatTb() {
        // given
        String itemCatNm = "스킨케어";
        ItemCatDto itemCatDto = new ItemCatDto(0, itemCatNm);
        // when
        int cnt = superMapper.searchItemCat(itemCatNm);
//        System.out.println(cnt);
        int result;
        if(cnt == 0) {
            result = superMapper.insertCatItem(itemCatDto);
        } else {
            result = 0;
        }
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
        int itemId = 1;
        // when
        int cnt = superMapper.searchItemCnt(itemId);
        System.out.println(cnt);
        // then
        assertThat(cnt)
                .isNotZero()
                .isPositive();
    }

    @DisplayName("화장품의 불량 제품과 유통기한 임박된 제품을 조회할 수 있다")
    @Test
    void searchDefItem() {
        // given
        // when
        // 불량 조회
        List<ItemDetailDto> itemDetail = superMapper.searchDefItem();
//        System.out.println(itemDetail);

        // then
        assertThat(itemDetail)
                .isNotNull()
                .isNotEmpty()
                .anySatisfy((item) -> {
                    assertThat(item.getItemDetailStatus()).isNotZero().isBetween(2, 3);
                    assertThat(item.getItemDetailExpirationDt()).isBefore("2024-07-18");
                });
        
        // Gbg에 넣을 Item 리스트 조회
//        List<Long> list = new ArrayList<>();
//        for(ItemDetailDto item : itemDetail) {
//            list.add(item.getItemIdPk());
//        }
//        System.out.println(list);
//        List<ItemDto> itemList = superMapper.searchItemTb(list);
//        System.out.println(itemList);

        // 화장품의 불량 제품과 유통기한 임박된 제품을 폐기테이블에 추가한다
//        List<Integer> result1List = new ArrayList<>();
        List<Integer> result2List = new ArrayList<>();
//        for(ItemDto item : itemList) {
//            GbgDto gbgDto = new GbgDto(item.getItemIdPk(), item.getItemNm(), item.getItemVol(), item.getItemCat());
//            int result1 = superMapper.insertGbg(gbgDto);
//            result1List.add(result1);
//        }
        for(ItemDetailDto item : itemDetail) {
            GbgDetailDto gbgDetailDto = new GbgDetailDto(0, item.getItemPk(), item.getItemDetailStatus(), item.getItemDetailSerialNum());
            int result2 = superMapper.insertGbgDetail(gbgDetailDto);
            result2List.add(result2);
        }

        // 화장품 폐기
//        ItemDto itemDto = new ItemDto();
//        int result1 = superMapper.deleteItem(itemDto);
        List<Integer> resultList = new ArrayList<>();
        for(ItemDetailDto item : itemDetail) {
            long itemDetailPk = item.getItemDetailPk();
            int result = superMapper.deleteDetailItem(itemDetailPk);
            resultList.add(result);
        }

        assertThat(resultList)
                .allSatisfy((result) -> {
                    assertThat(result).isEqualTo(1);
                });
    }
}