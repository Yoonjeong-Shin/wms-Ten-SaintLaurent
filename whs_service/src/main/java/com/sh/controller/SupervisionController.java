package com.sh.controller;

import com.sh.common.InboundRepository;
import com.sh.model.dto.*;
import com.sh.model.dto.json.InbDetailJsonDto;
import com.sh.model.dto.json.InbJsonDto;
import com.sh.model.service.SupervisionService;
import com.sh.view.SupervisionResultView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SupervisionController {
    private SupervisionService superService = new SupervisionService();

    // json 파일 가져오기
    InboundRepository inboundRepository = new InboundRepository();
    List<InbJsonDto> list = inboundRepository.readInb();
    List<InbDetailJsonDto> detailList = inboundRepository.readInbDetail();

    //
    public void insertCatItem(List<ItemCatDto> itemCatList) {
//        System.out.println(".");
        // test
        for(InbDetailJsonDto itemDetail : detailList) {
            System.out.println(itemDetail.getItemSerialNum());
        }
        int result = superService.insertCatItem(itemCatList);
        System.out.println(result);
    }

    // 화장품의 모든 정보
    public void searchItemInfo() {
        List<SearchItemDto> list = superService.searchItemInfo();
        System.out.println(list);
    }

    // 찾고자 하는 화장품의 개수
    public void searchItemCnt(int itemPk) {
        int itemcnt = superService.searchItemCnt(itemPk);
        System.out.println(itemcnt);
    }

    // 화장품이 저장된 공간 체크
    public void searchItemLpn(int itemPk) {
        List<LocateDto> list = superService.searchItemLpn(itemPk);
        System.out.println(list);
    }

    // 로케이션 빈공간 체크
    public boolean searchLpn() {
        List<LocateDto> list = superService.searchLpn();
        if(!list.isEmpty()) {
            System.out.println(list);
            return true;
        }
        return false;
    }

    // 화장품 ID와 화장품 이름 조회 후 보여주기
    public void searchItemIdNNm() {
        List<ItemDto> list = superService.searchItemIdNNm();
        SupervisionResultView.displayItemIdNNm(list);
    }

    public List<ItemCatDto> searchItemCat() {
        // json 파일 받아와서 전달할 값에 저장
        // ITEM_CAT_TB(화장품 품목)에 저장해야하는 값 받아서 넣기
//        System.out.println("Con");
        List<ItemCatDto> itemCatList = new ArrayList<>();
        for(InbJsonDto item : list) {
            String itemCatNm = item.getCat();
//            System.out.println(itemCatNm);
            int existenceVal = superService.searchItemCat(itemCatNm); // 품목 이름과 일치하는 것이 있으면 추가 안함
            if(existenceVal == 0) {
                ItemCatDto itemCatDto = new ItemCatDto(0, item.getCat());
                itemCatList.add(itemCatDto);
            } else {
                continue;
            }
        }
        if(!itemCatList.isEmpty()) {
            return itemCatList;
        } else return null;
    }

    public void insertItem() {
        Set<ItemDto> itemSet = new HashSet<>();
        for (InbJsonDto item : list) {
            // 화장품 이름이 중복되서 들어갈 수 없음
            // 화장품 이름이 이미 들어가있는지 먼저 체크해야함
            String itemNm = item.getItemName();
            int checkItemNm = superService.searchCheckItemNm(itemNm);
//            System.out.println(checkItemNm);

            // 없을 때
            if (checkItemNm == 0) {
                String itemCatNm = item.getCat(); // list에서 가져온 카테고리명
                int itemCatPk = superService.searchItemCat(itemCatNm); // 아이템 카테고리이름으로 pk값 찾기
                ItemDto itemDto = new ItemDto(0, item.getItemName(), item.getVol(), itemCatPk);
                itemSet.add(itemDto);
            }
        }

        List<ItemDto> itemList = itemSet.stream().collect(Collectors.toList());
        if(!itemList.isEmpty()) {
            System.out.println("itemList : " + itemList);
            int result = superService.insertItem(itemList);
            System.out.println("result : " + result);
        }
    }

    // 같은 화장품의 적재 위치와 수량 조회
    public boolean searchSameItemLpn() {
        List<LocateDto> locateList = new ArrayList<>();
        for(InbJsonDto item : list) {
            String itemNm = item.getItemName();
//            System.out.println(itemNm);
            // itemNm으로 ItemId값 조회
            long itemId = searchItemId(itemNm);
            System.out.println("itemId: " + itemId);
            locateList = superService.searchSameItemLpn(itemId);
        }
        if(!locateList.isEmpty()) {
            for(LocateDto locateDto : locateList) {
                String lpnCode = locateDto.getLocateLpnCode();
                int itemCnt = locateDto.getLocateItemCnt();
                System.out.println("lenCode: " + lpnCode + "itemCnt: " + itemCnt);
            }
            return true;
        }
        return false;
    }

    private long searchItemId(String itemNm) {
        return superService.searchItemId(itemNm);
    }

    public void deleteItem() {
        int result = superService.deleteItem();
        System.out.println(result);
    }

//    public void insertItemDetail() {
//        List<String> itemSerialList = new ArrayList<>();
//        List<Long> itemIdList = new ArrayList<>();
//        List<Integer> itemStatusList = new ArrayList<>();
//        // serialNum 할당 메소드
////        List<String> itemLpnList = assigningLpn(itemIdList);
//        List<LocalDate> itemExpirationList = new ArrayList<>();
//        for(InbJsonDto item : list) {
//            itemIdList.add(searchItemId(item.getItemName()));
//            itemExpirationList.add(item.getExpirationDate());
//        }
//        for(InbDetailJsonDto item : detailList) {
//            itemSerialList.add(item.getItemSerialNum());
//            itemStatusList.add(item.getState());
//        }
//        assigningSerialNum(itemSerialList);
//        for(int i = 0; i < itemIdList.size(); i++) {
//            ItemDetailDto itemDetailDto = new ItemDetailDto(0, itemSerialList.get(i), itemIdList.get(i), itemStatusList.get(i), itemLpnList.get(i), itemExpirationList.get(i));
//        }
//    }

    // serialNum 할당 메소드
//    private void assigningSerialNum(List<String> itemSerialList) {
//        //
//        String serialNum;
//        for(InbJsonDto item : list) {
//            serialNum = item.getItemName() + "_" + ;
//        }
//    }

//    private void assigningLpn(List<Long> itemIdList) {
//        List<LocateDto> locateList = new ArrayList<>();
//        for(Long itemId : itemIdList) {
//            locateList = superService.searchItemLpn(itemId);
//        }
//
//        for(LocateDto locate : locateList) {
//            String lpn = locate.getLocateLpnCode();
//            lpn.substring()
//        }
//    }
}
