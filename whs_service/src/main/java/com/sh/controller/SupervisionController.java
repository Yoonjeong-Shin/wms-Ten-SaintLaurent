package com.sh.controller;

import com.sh.common.InboundRepository;
import com.sh.model.dto.*;
import com.sh.model.dto.json.InbDetailJsonDto;
import com.sh.model.dto.json.InbJsonDto;
import com.sh.model.service.SupervisionService;
import com.sh.view.SupervisionResultView;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SupervisionController {
    private SupervisionService superService = new SupervisionService();

    // json 파일 가져오기
    InboundRepository inboundRepository = new InboundRepository();
    List<InbJsonDto> list = inboundRepository.readInb();
    List<InbDetailJsonDto> detailList = inboundRepository.readInbDetail();

    //
    public void insertCatItem() {
        List<ItemCatDto> itemCatList = searchItemCat();
        System.out.println("searchItemCat: " + itemCatList);
        if(itemCatList != null) {
            int result = superService.insertCatItem(itemCatList);
            System.out.println("insertCatItem result : " + result);
        }
    }

    // 화장품의 모든 정보
    public void searchItemInfo() {
        List<SearchItemDto> list = superService.searchItemInfo();
        System.out.println("searchItemInfo: " + list);
    }

    // 찾고자 하는 화장품의 개수
    public void searchItemCnt(int itemPk) {
        int itemcnt = superService.searchItemCnt(itemPk);
        System.out.println("searchItemCnt: " + itemcnt);
    }

    // 화장품이 저장된 공간 체크
    public void searchItemLpn(int itemPk) {
        List<LocateDto> list = superService.searchItemLpn(itemPk);
        System.out.println("searchItemLpn" + list);
    }

    // 로케이션 빈공간 체크
    public List<LocateDto> searchLpn() {
        List<LocateDto> list = superService.searchLpn();
        return list;
    }

    // 화장품 ID와 화장품 이름 조회 후 보여주기
    public void searchItemIdNNm() {
        List<ItemDto> list = superService.searchItemIdNNm();
        SupervisionResultView.displayItemIdNNm(list);
    }

    public List<ItemCatDto> searchItemCat() {
        // json 파일 받아와서 전달할 값에 저장
        // ITEM_CAT_TB(화장품 품목)에 저장해야하는 값 받아서 넣기
        List<ItemCatDto> itemCatList = new ArrayList<>();
        for(InbJsonDto item : list) {
            String itemCatNm = item.getCat();
            int existenceVal = superService.searchItemCat(itemCatNm);

            // 품목 이름과 일치하는 것이 있으면 추가 안함
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
    public List<LocateDto> searchSameItemLpn(String itemNm) {
        List<LocateDto> locateList = new ArrayList<>();
//        for(InbJsonDto item : list) {
//            String itemNm = item.getItemName();
            long itemId = searchItemId(itemNm); // itemNm으로 ItemId값 조회
            locateList = superService.searchSameItemLpn(itemId);
//        }
        List<String> lpnCodeNCnt = new ArrayList<>();
//        if(!locateList.isEmpty()) {
//            for(LocateDto locateDto : locateList) {
//                String lpnCode = locateDto.getLocateLpnCode();
//                String itemCnt = Integer.toString(locateDto.getLocateItemCnt());
//                System.out.println("lenCode: " + lpnCode + "itemCnt: " + itemCnt);
//                lpnCodeNCnt.add(lpnCode);
//                lpnCodeNCnt.add(itemCnt);
//            }
//        }
        return locateList;
    }

    private long searchItemId(String itemNm) {
        return superService.searchItemId(itemNm);
    }

    public void deleteItem() {
        int result = superService.deleteItem();
        System.out.println(result);
    }

    public void insertDetailItem() {
        // 적재해야하는 화장품 정보
        List<String> itemSerialList = new ArrayList<>();
        List<Long> itemIdList = new ArrayList<>();
        List<Integer> itemStatusList = new ArrayList<>();
        List<String> itemLpnList = new ArrayList<>();
        List<LocalDate> itemExpirationList = new ArrayList<>();
        Map<Long, String> lpnInfo = new HashMap<>();
        String facLoc = null;

        // json에서 가져온 정보를 재가공(itemNm, itemPk, lpn, expiration), 로케이트 적재 수량 업데이트
        for(InbJsonDto item : list) {
            String itemNm = item.getItemName();
            long itemPk = searchItemId(item.getItemName());
            itemIdList.add(itemPk);
            itemExpirationList.add(item.getExpirationDate());
            int itemCnt = item.getItemCount();
            facLoc = item.getFactoryLoc();

            // 같은 화장품이 적재된 공간이 있는가?
            List<LocateDto> locateInfo = searchSameItemLpn(itemNm);
            // 로케이트 빈공간 체크
            List<LocateDto> locateList = searchLpn();

            if(!locateInfo.isEmpty()) { // 같은 화장품이 적재된 공간이 있음
                for(LocateDto locate : locateInfo) { // 같은 아이템의 로케이트 저장 정보
                    int cnt = locate.getLocateItemCnt(); // 로케이트에 적재된 수량
                    if(cnt < 50) { // 로케이트에 적재 가능
                        if(itemCnt <= 50 - cnt) { // 남은 공간보다 적재할 양이 적은가
                            lpnInfo.put(itemPk, locate.getLocateLpnCode());
                            updareLocateCnt(itemCnt, locate.getLocateLpnCode()); // 로케이트 적재 수량 업데이트
                        }else {
                            lpnInfo.put(itemPk, locate.getLocateLpnCode());
                            itemCnt -= (50 - cnt);
                            updareLocateCnt(50 - cnt, locate.getLocateLpnCode()); // 로케이트 적재 수량 업데이트
                        }
                    }else { // 로케이트에 적재 불가능
                        continue;
                    }
                }
                // 같은 곳에 적재하고 남은 재고가 있다면 비어있는 곳에 적재
                while(itemCnt > 0) {
                    for(LocateDto locate : locateList) {
                        if(itemCnt > 50) {
                            lpnInfo.put(itemPk, locate.getLocateLpnCode());
                            itemCnt -= 50;
                            updareLocateCnt(50, locate.getLocateLpnCode()); // 로케이트 적재 수량 업데이트
                        }else {
                            lpnInfo.put(itemPk, locate.getLocateLpnCode());
                            updareLocateCnt(itemCnt, locate.getLocateLpnCode()); // 로케이트 적재 수량 업데이트
                            itemCnt = 0;
                        }
                    }
                }
            }else {
                // 없다 > 빈 로케이션 조회
                for(LocateDto locate : locateList) {
                    if(itemCnt > 50) {
                        lpnInfo.put(itemPk, locate.getLocateLpnCode());
                        itemCnt -= 50;
                    }else {
                        lpnInfo.put(itemPk, locate.getLocateLpnCode());
                        itemCnt = 0;
                    }
                }
            }
        }
        for(InbDetailJsonDto item : detailList) {
            itemSerialList.add(item.getItemSerialNum());
            itemStatusList.add(item.getState());
        }

        // 시리얼 번호: 제품명-창고ID-lpn-유통기한-제조번호 ex) 화산송이 클렌징-1-001-231102-0001
        // 창고ID 조회
        int whsId = superService.searchWhsLoc(facLoc);

        // lpnInfo 정보
        System.out.println(lpnInfo);

//        for(String serialNum : itemSerialList) {
//            String[] ret = serialNum.split("-");
//            ret[0] + "-" +  + "-" +  + ret[3] + "-" + ret[4];
//        }

        for(int i = 0; i < itemIdList.size(); i++) {
            System.out.printf("itemDetailDto: 0, %s, %d, %d, %s, %tF",itemSerialList.get(i), itemIdList.get(i), itemStatusList.get(i), itemLpnList.get(i), itemExpirationList.get(i));
//            ItemDetailDto itemDetailDto = new ItemDetailDto(0, itemSerialList.get(i), itemIdList.get(i), itemStatusList.get(i), itemLpnList.get(i), itemExpirationList.get(i));
        }
    }

    private void updareLocateCnt(int itemCnt, String locateLpnCode) {
        int result = superService.updareLocateCnt(itemCnt, locateLpnCode); // 로케이트 적재 수량 업데이트
        System.out.println("updareLocateCnt result : " + result);
    }

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
