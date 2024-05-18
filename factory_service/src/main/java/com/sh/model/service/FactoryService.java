package com.sh.model.service;

import com.sh.model.dao.FactoryMapper;
import com.sh.model.dto.FactoryDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class FactoryService {

    public List<FactoryDto> searchFactory() {
        SqlSession sqlSession = getSqlSession();
        FactoryMapper factoryMapper = sqlSession.getMapper(FactoryMapper.class);
        List<FactoryDto> list = factoryMapper.searchFactory();
        sqlSession.close();
        return list;
    }


}
