package com.sh.model.service;

import com.sh.model.dao.InboundMapper;
import com.sh.model.dto.InboundDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InboundService {
    public List<InboundDto> insertInbound() {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        List<InboundDto> list = inboundMapper.insertInbound();
        sqlSession.close();
        return list;
    }
}
