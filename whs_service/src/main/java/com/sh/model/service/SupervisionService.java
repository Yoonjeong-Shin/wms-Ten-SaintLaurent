package com.sh.model.service;

import com.sh.model.dao.LocateMapper;
import com.sh.model.dao.SupervisionMapper;
import com.sh.model.dto.ItemDto;
import com.sh.model.dto.LocateDto;
import com.sh.model.dto.SupervisionDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class SupervisionService {

    // 조회 시작
    // 화장품 정보 전체 조회
    public List<ItemDto> searchItemInfo() {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        List<ItemDto> list = superMapper.searchItemInfo();
        sqlSession.close();
        return list;
    }

    // 특정 화장품의 총 개수 조회
    public int searchItemCnt(String itemName) {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        int itemCnt = superMapper.searchItemCnt(itemName);
        sqlSession.close();
        return itemCnt;
    }

    // 특정 화장품의 lpn 조회
    public LocateDto searchItemLpn(String itemName) {
        SqlSession sqlSession = getSqlSession();
        LocateMapper locateMapper = sqlSession.getMapper(LocateMapper.class);
        LocateDto locateDto = locateMapper.searchItemLpn(itemName);
        sqlSession.close();
        return locateDto;
    }

    // 로케이션 빈공간 조회
    public List<LocateDto> searchLpn() {
        SqlSession sqlSession = getSqlSession();
        LocateMapper locateMapper = sqlSession.getMapper(LocateMapper.class);
        List<LocateDto> list = locateMapper.searchLpn();
        sqlSession.close();
        return list;
    }
    // 조회 끝
}
