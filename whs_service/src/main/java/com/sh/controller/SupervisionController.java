package com.sh.controller;

import com.sh.model.dto.ItemDto;
import com.sh.model.dto.SearchItemDto;
import com.sh.model.dto.LocateDto;
import com.sh.model.service.SupervisionService;
import com.sh.view.SupervisionResultView;

import java.util.List;

public class SupervisionController {
    private SupervisionService superService = new SupervisionService();

    // 화장품의 모든 정보
    public void searchItemInfo() {
        List<SearchItemDto> list = superService.searchItemInfo();
        System.out.println(list);
    }

    // 찾고자 하는 화장품의 개수
    public void searchItemCnt(int itemId) {
        int itemcnt = superService.searchItemCnt(itemId);
        System.out.println(itemcnt);
    }

    // 화장품이 저장된 공간 체크
    public void searchItemLpn(int itemId) {
        LocateDto locateDto = superService.searchItemLpn(itemId);
        System.out.println(locateDto);
    }

    // 로케이션 빈공간 체크
    public void searchLpn() {
        List<LocateDto> list = superService.searchLpn();
        System.out.println(list);
    }

    // 화장품 ID와 화장품 이름 조회 후 보여주기
    public void searchItemIdNNm() {
        List<ItemDto> list = superService.searchItemIdNNm();
        SupervisionResultView.displayItemIdNNm(list);
    }
}
