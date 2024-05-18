package com.sh.model.service;

import com.sh.model.dao.InboundMapper;
import com.sh.model.dao.OutboundMapper;
import com.sh.model.dto.OrderDto;
import com.sh.model.dto.OutboundDto;
import com.sh.model.dto.PurchaseListDto;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
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

    public int assignCart(OutboundDto outboundDto) {
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            int result = outboundMapper.assignCart(outboundDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public List<OutboundDto> outboundPicking() {
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        List<OutboundDto> list = outboundMapper.OutboundPicking();
        sqlSession.close();
        return list;
    }
// 출고 검수
    public int checkOutbound(OutboundDto outboundDto) {
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            // dao 메세지 전달
            int result = outboundMapper.checkOutbound(outboundDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public int confirmOutbound() {
        return 0;
    }
    public int updateOutCnt(OutboundDto outboundDto) {
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            // dao 메세지 전달
            int result = outboundMapper.updateOutCnt(outboundDto);
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
