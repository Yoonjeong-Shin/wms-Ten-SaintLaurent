package com.sh.model.service;

import com.sh.common.MyBatisTemplate;
import com.sh.model.SelInboundOrder;
import com.sh.model.SelOutboundOrder;
import com.sh.model.dao.SelInboundOrderMapper;
import com.sh.model.dao.SelOutboundOrderMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SelOutboundService {
    public List<SelOutboundOrder> findAllOutboundOrders() {
        SqlSession sqlSession = MyBatisTemplate.getSqlSession();
        SelOutboundOrderMapper selOutboundOrderMapper = sqlSession.getMapper(SelOutboundOrderMapper.class);
        List<SelOutboundOrder> selOutboundOrderList;
        try {
            selOutboundOrderList = selOutboundOrderMapper.findAllOutboundOrders();
        } finally {
            sqlSession.close();
        }
        return selOutboundOrderList;
    }
}
