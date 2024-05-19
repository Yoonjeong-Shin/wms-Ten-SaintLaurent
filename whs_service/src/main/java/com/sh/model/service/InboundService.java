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

    // 입고 승인 - JSON 데이터를 dto로 바꿔서 INB_TB에 넣기
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
    // state가 1인 정상 제품은 item_tb와 item_detail_tb에 insert
    public int insertInbToItemTb(InboundDto inboundDto) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.insertInbToItemTb(inboundDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
    public int insertInbToItemDetailTb(InboundDto inboundDto) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.insertInbToItemDetailTb(inboundDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    // 입고 정보 조회
    // INB_TB의 PK로 한 데이터의 모든 정보를 조회, 입고 승인과 입고 확정 때 쓰인다
    public InboundDto findByInbId(int inbIdPk) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        InboundDto inboundDto = inboundMapper.findByInbId(inbIdPk);
        sqlSession.close();
        return inboundDto;
    }

    // 입고 검수
    // state가 2,3인 불량품은 gbg_detail_tb에 insert
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
    // gbg_detail_tb에 insert한 불량품은 item_detail_tb에서 delete
    public int deleteItemDetail() {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.deleteItemDetial();
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

//    public int updateInbCnt(InboundDto inboundDto) {
//        SqlSession sqlSession = getSqlSession();
//        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
//        try {
//            // dao 메세지 전달
//            int result = inboundMapper.updateInbCnt(inboundDto);
//            sqlSession.commit();
//            return result;
//        } catch (Exception e) {
//            sqlSession.rollback();
//            throw new RuntimeException(e);
//        } finally {
//            sqlSession.close();
//        }
//    }
}
