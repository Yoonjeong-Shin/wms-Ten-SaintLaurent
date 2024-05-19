package com.sh.model.service;

import com.sh.model.dao.FactoryMapper;
import com.sh.model.dto.FactoryDto;
import com.sh.model.dto.json.InbJsonDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class FactoryService {
    public static void main(String[] args) {
        FactoryService factoryService = new FactoryService();
        System.out.println(factoryService.searchFactory());
    }
        public List<InbJsonDto> searchFactory() {
            SqlSession sqlSession = getSqlSession();
            FactoryMapper factoryMapper = sqlSession.getMapper(FactoryMapper.class);
            List<InbJsonDto> list = factoryMapper.searchFactory();
            System.out.println(list.get(0).getFactoryName());
            sqlSession.close();

            return list;
        }


}
