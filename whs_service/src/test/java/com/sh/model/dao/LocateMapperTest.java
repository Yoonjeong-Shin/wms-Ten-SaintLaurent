package com.sh.model.dao;

import com.sh.model.dto.LocateDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

class LocateMapperTest {
    SqlSession sqlSession;
    LocateMapper locateMapper;

    @BeforeEach
    void setUp() {
        this.sqlSession = getSqlSession();
        this.locateMapper = this.sqlSession.getMapper(LocateMapper.class);
    }

    @AfterEach
    void tearDown() {
        this.sqlSession.close();
    }

    @DisplayName("로케이션의 비어있는 공간을 조회한다.")
    @Test
    void searchLpn() {
        // given
        // when
        List<LocateDto> list = locateMapper.searchLpn();
        System.out.println(list);
        // then
        assertThat(list)
                .isNotNull()
                .isNotEmpty();
    }

    @DisplayName("화장품의 lpn 정보를 조회한다.")
    @Test
    void searchItemLpn() {
        // given
        int itemPk = 1;
        // when
        LocateDto locateDto = locateMapper.searchItemLpn(itemPk);
        System.out.println(locateDto);
        // then
        assertThat(locateDto)
                .isNotNull();
    }
}