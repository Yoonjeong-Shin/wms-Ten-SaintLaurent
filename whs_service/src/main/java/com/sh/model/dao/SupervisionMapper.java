package com.sh.model.dao;

import com.sh.model.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SupervisionMapper {
    // 화장품의 모든 정보 조회
    List<SearchItemDto> searchItemInfo();

    // 화장품 개수 조회
    int searchItemCnt(int itemPk);

    // 아이템 테이블에 인서트
    int insertItem(List<ItemDto> itemDto);

    // 아이템 디테일 테이블에 인서트
    int insertDetailItem(ItemDetailDto itemDetailDto);

    // 카테고리 테이블에 추가하기 전 같은 품목이 있는지 체크하는 메소드
    int searchItemCat(String itemCatNm);

    // 화장품 카테고리에 새로 추가
    int insertCatItem(List<ItemCatDto> itemCatDtoList);

    // 화장품ID와 이름 조회
    List<ItemDto> searchItemIdNNm();

    // 불량 조회
    List<ItemDetailDto> searchDefItem();

    // Gbg테이블에 추가
//    int insertGbg(GbgDto gbgDto);

    // GbgDetail에 추가
    int insertGbgDetail(GbgDetailDto gbgDetailDto);

    // 아이템 아이디로 여러개의 화장품 조회
    List<ItemDto> searchItemTb(List<Long> list);

    // 아이템 디테일 테이블에서 화장품 삭제
    int deleteDetailItem(long itemDetailPk);

    // item_detail_tb에서 itemNm으로 itemId 조회
    long searchItemId(String itemNm);

    // 같은 이름의 화장품이 어디에 적재되어 있는지 조회
    List<LocateDto> searchSameItemLpn(long itemId);

    // item_tb에서 itemNm과 일치하는 것이 몇개인지 체크(1개 이상일 수 없음)
    int searchCheckItemNm(String itemNm);

    int updareLocateCnt(@Param("itemCnt") int itemCnt, @Param("locateLpnCode") String locateLpnCode);

    int searchWhsLoc(String facLoc);
}
