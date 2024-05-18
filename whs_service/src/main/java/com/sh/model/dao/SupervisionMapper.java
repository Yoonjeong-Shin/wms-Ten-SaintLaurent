package com.sh.model.dao;

import com.sh.model.dto.*;

import java.util.List;

public interface SupervisionMapper {
    List<SearchItemDto> searchItemInfo();

    int searchItemCnt(int itemPk);

    int insertItem(ItemDto itemDto);

    int insertDetailItem(ItemDetailDto itemDetailDto);

    // 카테고리 테이블에 추가하기 전 같은 품목이 있는지 체크하는 메소드
    int searchItemCat(String itemCatNm);

    // 화장품 카테고리에 새로 추가
    int insertCatItem(ItemCatDto itemCatDto);

    // 화장품ID와 이름 조회
    List<ItemDto> searchItemIdNNm();

    // 불량 조회
    List<ItemDetailDto> searchDefItem();

    // Gbg테이블에 추가
//    int insertGbg(GbgDto gbgDto);

    // GbgDetail에 추가
    int insertGbgDetail(GbgDetailDto gbgDetailDto);

    List<ItemDto> searchItemTb(List<Long> list);

    int deleteDetailItem(long itemDetailPk);
}
