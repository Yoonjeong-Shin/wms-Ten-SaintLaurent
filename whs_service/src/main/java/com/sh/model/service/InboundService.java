package com.sh.model.service;

import com.sh.model.dao.InboundMapper;
import com.sh.model.dto.InboundDto;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InboundService {

    // 입고 정보 조회 (입고 승인과 입고 확정 때 쓰인다)
    // INB_TB의 한 데이터를 INB_ID_PK로 조회
    public InboundDto findByInboundID(int inboundID) {
        return null;
    }

    // 입고 승인
    // LOCATE_TB에서 LOCATE_ITEM_CNT 찾기
    public int findLocateItemCnt() {
        return 0;
    }

    // 입고 승인
    // INB_TB에 JSON 데이터 넣기
    public int insertInboundToINB(InboundDto inboundDto) {
        return 0;
    }

    // 입고 검수
    // GBG_TB에 state가 2,3인 불량 제품을 insert
    public int insertInboundToGBG(InboundDto inboundDto) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.insertInboundToGBG(inboundDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    // 입고 검수
    // INB_TB에서 불량 제품을 뺀 수량 update
    public int updateInboundCnt(InboundDto inboundDto) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.updateInboundCnt(inboundDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
}
