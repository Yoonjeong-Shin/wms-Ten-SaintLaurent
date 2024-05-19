package com.sh.model.service;

import com.sh.model.dao.OutboundMapper;
import com.sh.model.dto.*;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class OutboundService {
    public static List<OrderDto> searchOutbound() {
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        List<OrderDto> list = outboundMapper.searchOutbound();
        sqlSession.close();
        return list;
    }
    public List<PurchaseListDto> printOutBoundReport() {
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        List<PurchaseListDto> list = outboundMapper.printOutBoundReport();
        sqlSession.close();
        return list;
    }

    //== assignCart 여기서부터 ==//
    public List<OutBoundCartDto> assignCart() {
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            List<OutBoundCartDto> outBoundCartDto = outboundMapper.assignCart();
            return outBoundCartDto;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }


    public List<OutBoundPickDto> outboundPicking() {
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        List<OutBoundPickDto> list = outboundMapper.OutboundPicking();
        sqlSession.close();
        return list;
    }
    // 출고 검수
    public int checkOutbound(OutboundCheckDto outBoundPickDto) {
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            // dao 메세지 전달
            int pick = outboundMapper.checkOutbound(outBoundPickDto);
            sqlSession.commit();
            return pick;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public int confirmOutbound(LocateDto locateDto) {
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            // dao 메세지 전달
            int update = outboundMapper.confirmOutbound(locateDto);
            sqlSession.commit();
            return update;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
}

