package com.sh.controller;

import com.sh.model.dto.ItemDto;
import com.sh.model.dto.LocateDto;
import com.sh.model.dto.SupervisionDto;
import com.sh.model.service.SupervisionService;

import java.util.List;

public class SupervisionController {
    private SupervisionService superService = new SupervisionService();

    // 화장품의 모든 정보
    public void searchItemInfo() {
        List<ItemDto> list = superService.searchItemInfo();
        System.out.println(list);
    }

    // 찾고자 하는 화장품의 개수
    public void searchItemCnt(String itemName) {
        int itemcnt = superService.searchItemCnt(itemName);
        System.out.println(itemcnt);
    }

    // 화장품이 저장된 공간 체크
    public void searchItemLpn(String itemName) {
        LocateDto locateDto = superService.searchItemLpn(itemName);
        System.out.println(locateDto);
    }

    // 로케이션 빈공간 체크
    public void searchLpn() {
        List<LocateDto> list = superService.searchLpn();
        System.out.println(list);
    }
}
