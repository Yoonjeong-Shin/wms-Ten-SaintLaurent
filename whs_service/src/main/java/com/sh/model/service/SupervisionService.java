package com.sh.model.service;

import com.sh.model.dao.LocateMapper;
import com.sh.model.dao.SupervisionMapper;
import com.sh.model.dto.ItemDto;
import com.sh.model.dto.SearchItemDto;
import com.sh.model.dto.LocateDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class SupervisionService {

    // 조회 시작
    // 화장품 정보 전체 조회
    public List<SearchItemDto> searchItemInfo() {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        List<SearchItemDto> list = superMapper.searchItemInfo();
        sqlSession.close();
        return list;
    }

    // 특정 화장품의 총 개수 조회
    public int searchItemCnt(int itemId) {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        int itemCnt = superMapper.searchItemCnt(itemId);
        sqlSession.close();
        return itemCnt;
    }

    // 특정 화장품의 lpn 조회
    public LocateDto searchItemLpn(int itemPk) {
        SqlSession sqlSession = getSqlSession();
        LocateMapper locateMapper = sqlSession.getMapper(LocateMapper.class);
        LocateDto locateDto = locateMapper.searchItemLpn(itemPk);
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
    public List<ItemDto> searchItemIdNNm() {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        List<ItemDto> list = superMapper.searchItemIdNNm();
        sqlSession.close();
        return list;
    }
}
