package com.sh.model.dao;

import com.sh.model.dto.ItemDto;
import com.sh.model.dto.SupervisionDto;

import java.util.List;

public interface SupervisionMapper {
    List<ItemDto> searchItemInfo();

    int searchItemCnt(String itemName);
}
