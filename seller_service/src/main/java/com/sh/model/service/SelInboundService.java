package com.sh.model.service;
import com.sh.common.MyBatisTemplate;
import com.sh.model.SelInboundOrder;
import com.sh.model.dao.SelInboundOrderMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class SelInboundService {
    public List<SelInboundOrder> findAllInboundOrders() {
        SqlSession sqlSession = MyBatisTemplate.getSqlSession();
        SelInboundOrderMapper selInboundOrderMapper = sqlSession.getMapper(SelInboundOrderMapper.class);
        List<SelInboundOrder> selInboundOrderList;
        try {
            selInboundOrderList = selInboundOrderMapper.findAllInboundOrders();
        } finally {
            sqlSession.close();
        }
        return selInboundOrderList;
    }
}