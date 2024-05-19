package com.sh.model.service;

import com.sh.common.MyBatisTemplate;
import com.sh.model.SelInboundOrder;
import com.sh.model.SelOutboundOrder;
import com.sh.model.dao.SelInboundOrderMapper;
import com.sh.model.dao.SelOutboundOrderMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        SqlSession sqlSession;
        SelInboundOrderMapper selOutboundOrderMapper;
        sqlSession = MyBatisTemplate.getSqlSession();
        selOutboundOrderMapper = sqlSession.getMapper(SelInboundOrderMapper.class);
        List<SelInboundOrder> locateDto = new ArrayList<>();
        locateDto= selOutboundOrderMapper.findAllInboundOrders();
        System.out.println(locateDto);

    }
}
