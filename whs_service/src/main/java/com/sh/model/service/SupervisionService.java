package com.sh.model.service;

import com.sh.model.dao.LocateMapper;
import com.sh.model.dto.LocateDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class SupervisionService {

    public List<LocateDto> searchLpn() {
        SqlSession sqlSession = getSqlSession();
        LocateMapper locateMapper = sqlSession.getMapper(LocateMapper.class);
        List<LocateDto> list = locateMapper.searchLpn();
        sqlSession.close();
        return list;
    }
}
