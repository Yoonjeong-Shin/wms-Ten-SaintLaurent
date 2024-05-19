package sh.model.dao;

import com.sh.model.dao.SelInboundOrderMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

class selInboundMapper {
    SqlSession sqlSession;
    SelInboundOrderMapper superMapper;

    @BeforeEach
    void setUp() {
        this.sqlSession = getSqlSession();
        this.superMapper = this.sqlSession.getMapper(SelInboundOrderMapper.class);
    }

    @AfterEach
    void tearDown() {
        this.sqlSession.commit();
//        this.sqlSession.rollback();
        this.sqlSession.close();
    }


    @DisplayName("화장품의 모든 정보를 조회한다.")
    @Test
    void searchItemInfo() {
        // given
        // when
        // then
    }

}