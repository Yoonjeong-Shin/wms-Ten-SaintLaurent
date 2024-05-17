package com.sh.model.service;

import com.sh.model.dao.OutboundMapper;
import com.sh.model.dto.OrderDto;
import com.sh.model.dto.OutboundDto;
import com.sh.model.dto.PurchaseListDto;
import lombok.Synchronized;
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
            // dao 메세지 전달
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

    public List<OutboundDto> OutboundPicking() {
        return new ArrayList<>();
    }

    public boolean checkOutbound() {
        return false;
    }

    public boolean confirmOutbound() {
        return false;
    }

    public boolean rejectPicking() {
        return false;
    }

    public List<OutboundDto> rejectcheck() {
        return new ArrayList<>();
    }
}
