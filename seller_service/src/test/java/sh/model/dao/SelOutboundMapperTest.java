package sh.model.dao;

import com.sh.common.MyBatisTemplate;
import com.sh.model.SelOutboundOrder;

import com.sh.model.dao.SelOutboundOrderMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

class SelOutboundMapperTest {
    SqlSession sqlSession;
    SelOutboundOrderMapper selOutboundOrderMapper;

    @BeforeEach
    void setUp() {
        sqlSession = MyBatisTemplate.getSqlSession();
        selOutboundOrderMapper = sqlSession.getMapper(SelOutboundOrderMapper.class);
    }

    @AfterEach
    void tearDown() {
        this.sqlSession.commit();
//        this.sqlSession.rollback();
        this.sqlSession.close();
    }
    @DisplayName("화장품의 lpn 정보를 조회한다.")
    @Test
    void searchItemLpn() {
        // given
        // when
        sqlSession = MyBatisTemplate.getSqlSession();
        selOutboundOrderMapper = sqlSession.getMapper(SelOutboundOrderMapper.class);
        List<SelOutboundOrder> locateDto = new ArrayList<>();
        locateDto = selOutboundOrderMapper.findAllOutboundOrders();
        System.out.println(locateDto);
        assertThat(locateDto).isNotNull();
        // then
    }
}