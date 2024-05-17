package com.sh.model.service;

import com.sh.model.dao.InboundMapper;
import com.sh.model.dto.InboundDto;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InboundService {

    public int approveInbound(InboundDto inboundDto) {
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

    public void showInboundResult(InboundDto inboundDto) {

    }
}
