package com.sh.model.dao;

import com.sh.model.dto.ItemCatDto;
import com.sh.model.dto.ItemDetailDto;
import com.sh.model.dto.ItemDto;
import com.sh.model.dto.SearchItemDto;

import java.util.List;

public interface SupervisionMapper {
    List<SearchItemDto> searchItemInfo();

    int searchItemCnt(String itemName);

    int insertItem(ItemDto itemDto);

    int insertDetailItem(ItemDetailDto itemDetailDto);

    // 카테고리 테이블에 추가하기 전 같은 품목이 있는지 체크하는 메소드
    int searchItemCat(String itemCatNm);

    // 화장품 카테고리에 새로 추가
    int insertCatItem(ItemCatDto itemCatDto);
}
