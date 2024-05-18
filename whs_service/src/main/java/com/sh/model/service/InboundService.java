package com.sh.model.service;

import com.sh.model.dao.InboundMapper;
import com.sh.model.dto.GbgDetailDto;
import com.sh.model.dto.InboundDto;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InboundService {

    // 입고 승인
    // LOCATE_TB에서 LOCATE_ITEM_CNT 찾기
    public int findEmptyLocate() {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        int result = inboundMapper.findEmptyLocate();
        sqlSession.close();
        return result;
    }

    /* 지영 작업 시작 */

    // 입고 승인
    // INB_TB에 JSON 데이터 넣기
    public int insertInbToINB(InboundDto inboundDto) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.insertInbToINB(inboundDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    // 입고 정보 조회 (입고 승인과 입고 확정 때 쓰인다)
    // INB_TB의 한 데이터의 모든 정보를 INB_ID_PK로 조회
    public InboundDto findByInbId(int inbIdPk) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        InboundDto inboundDto = inboundMapper.findByInbId(inbIdPk);
        sqlSession.close();
        return inboundDto;
    }

    // 입고 검수
    public int insertInbToGbgDetail(GbgDetailDto gbgDetailDto) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.insertInbToGbgDetail(gbgDetailDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public int updateInbCnt(InboundDto inboundDto) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.updateInbCnt(inboundDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    /* 지영 작업 끝 */
}
