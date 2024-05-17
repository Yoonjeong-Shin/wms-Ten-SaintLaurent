package com.sh.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        // when
        // then
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