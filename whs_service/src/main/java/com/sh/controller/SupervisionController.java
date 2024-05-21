package com.sh.controller;

import com.sh.model.dto.*;
import com.sh.model.dto.json.InbDetailJsonDto;
import com.sh.model.dto.json.InbJsonDto;
import com.sh.model.service.SupervisionService;
import com.sh.view.SupervisionResultView;
import com.sh.whsApp;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SupervisionController {
    private SupervisionService superService = new SupervisionService();
    int max = 50;
    // json 파일 가져오기
    //
    public void insertCatItem(List<InbJsonDto> inbJsonDtoList) {
        List<ItemCatDto> itemCatList = searchItemCat(inbJsonDtoList);
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
    public List<LocateDto> searchLpn(long whsPK) {
        List<LocateDto> list = superService.searchLpn(whsPK);
        return list;
    }

    // 화장품 ID와 화장품 이름 조회 후 보여주기
    public void searchItemIdNNm() {
        List<ItemDto> list = superService.searchItemIdNNm();
        SupervisionResultView.displayItemIdNNm(list);
    }

    public List<ItemCatDto> searchItemCat(List<InbJsonDto> itemCatsList) {
        // json 파일 받아와서 전달할 값에 저장
        // ITEM_CAT_TB(화장품 품목)에 저장해야하는 값 받아서 넣기
        List<ItemCatDto> itemCatList = new ArrayList<>();
        for(InbJsonDto item : itemCatsList) {
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

    public void insertItem(List<InbJsonDto> itemsList) {
        Set<ItemDto> itemSet = new HashSet<>();
        for (InbJsonDto item : itemsList) {
            // 화장품 이름이 중복되서 들어갈 수 없음
            // 화장품 이름이 이미 들어가있는지 먼저 체크해야함
            String itemNm = item.getItemName();
            int checkItemNm = superService.searchCheckItemNm(itemNm);

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
            int result = superService.insertItem(itemList);
        }
    }

    // 같은 화장품의 적재 위치와 수량 조회
    public List<LocateDto> searchSameItemLpn(String itemNm) {
        Long itemId = searchItemId(itemNm); // itemNm으로 ItemId값 조회

        return new ArrayList<>(superService.searchSameItemLpn(itemId));
    }

    private Long searchItemId(String itemNm) {
        return superService.searchItemId(itemNm);
    }

    public void deleteItem() {
        int result = superService.deleteItem();
        System.out.println(result);
    }

    public void insertDetailItem(List<InbJsonDto> inbJsonDtos) {
        // 적재해야하는 화장품 정보
        List<Long> itemIdList = new ArrayList<>();
        List<Integer> itemStatusList = new ArrayList<>();
        List<LocalDate> itemExpirationList = new ArrayList<>();
        Map<Long, String> lpnInfo = new HashMap<>();

        for(InbJsonDto item : inbJsonDtos) {
            String itemNm = item.getItemName();
            Long itemPk;
            itemPk = searchItemId(item.getItemName());
            if(itemPk == null) {
                insertItem(inbJsonDtos);
            }

            // 같은 화장품이 적재된 공간이 있는가?
            System.out.println("📦📦📦 같은 제품이 적재된 공간 검색중입니다 📦📦📦");
            List<LocateDto> sameLocateList = searchSameItemLpn(itemNm);
            // 로케이트 빈공간 체크
            System.out.println("📦📦📦 비어있는 로케이트 공간 조회중입니다 📦📦📦");
            List<LocateDto> locateNullList = searchLpn(whsApp.whsPk);
            itemIdList.add(itemPk);
            itemExpirationList.add(item.getExpirationDate());
            int itemCnt = item.getItemCount();

            // 시리얼 넘버
            String serialNum = null;
            for(int i =0; i < item.getItemsDetail().size(); i++) {
                serialNum = item.getItemsDetail().get(i).getItemSerialNum();
                itemStatusList.add(item.getItemsDetail().get(i).getState());
            }


            List<InbDetailJsonDto> itemDetailDtoList = new ArrayList<>(item.getItemsDetail());
            if (!sameLocateList.isEmpty()) {
                System.out.println("✨✨✨ 같은 화장품이 적재된 공간을 찾았습니다! ✨✨✨");
                for (LocateDto locate : sameLocateList) {
                    System.out.println(itemNm + "이 저장된 로케이트 번호는 " +locate.getLocatePk() +"입니다. 로케이트 안의 제품 개수는 " + locate.getLocateItemCnt() + "개 입니다😃");
                    int cnt = locate.getLocateItemCnt(); // 로케이트에 적재된 수량
                    int spaceLeft = max - cnt; // 남은 공간
//                    System.out.println( locate.getLocateLpnCode());
                    if (spaceLeft <= 0) continue; // 로케이트에 공간이 없으면 다음 로케이트로 넘어감
                    int amountToLoad = 0;
                    if(spaceLeft < itemCnt) continue;
                    else amountToLoad = itemCnt;// 적재할 양은 남은 공간과 아이템 수량 중 작은 값

                    lpnInfo.put(itemPk, locate.getLocateLpnCode());
                    updareLocateCnt(amountToLoad, locate.getLocateLpnCode()); // 로케이트 적재 수량 업데이트
                    assert itemPk != null;
                    updareItemCnt(amountToLoad, itemPk);
                    for (int i = 0; i < itemCnt && !itemDetailDtoList.isEmpty(); ++i) {
                        InbDetailJsonDto dto = itemDetailDtoList.get(0);
                        insertDetailItem2(dto, itemPk,item.getItemsDetail().get(i).getState(), item.getItemName(), String.valueOf(whsApp.whsPk), locate.getLocateLpnCode(),locate.getLocatePk(), item.getExpirationDate());
                        itemDetailDtoList.remove(0);
                    }
                    itemCnt -= amountToLoad; // 적재한 만큼 아이템 수량 감소

                    if (itemCnt <= 0) break; // 더 이상 적재할 아이템이 없으면 종료
                }
            }
    //새로운 공간에 저장

            if (itemCnt > 0) {
                System.out.println("새로운 공간 저장");
                for (LocateDto locate : locateNullList) {
                    if (itemCnt <= 0) break; // 더 이상 적재할 아이템이 없으면 종료

                    int amountToLoad = Math.min(itemCnt, max); // 적재할 양은 아이템 수량과 최대 적재 가능 수량 중 작은 값

                    lpnInfo.put(itemPk, locate.getLocateLpnCode());
                    updareLocateCnt(amountToLoad, locate.getLocateLpnCode()); // 로케이트 적재 수량 업데이트
                    System.out.println( locate.getLocateLpnCode());
                    assert itemPk != null;
                    updareItemCnt(amountToLoad, itemPk);
//                    System.out.println(locate.getLocatePk()+"로케이트 피케이");
                    long locPk = locate.getLocatePk();
                    for (int i = 0; i < itemCnt && !itemDetailDtoList.isEmpty(); ++i) {
                        InbDetailJsonDto dto = itemDetailDtoList.get(0);

                        insertDetailItem2(dto, itemPk,item.getItemsDetail().get(i).getState(), item.getItemName(), String.valueOf(whsApp.whsPk), locate.getLocateLpnCode(),locate.getLocatePk(), item.getExpirationDate());
                        itemDetailDtoList.remove(0);
                    }

                    itemCnt -= amountToLoad; // 적재한 만큼 아이템 수량 감소
                }
            }
        }

    }

    public void insertDetailItem2(InbDetailJsonDto itemDetailDto,Long itemPk,int itemDetailStatus, String itemNM,String whsPk, String lpnCode,long locatePk, LocalDate expirationDate){
        System.out.println("💎💎💎 시리얼 번호 생성중입니다 💎💎💎");
            String serialNum = itemDetailDto.getItemSerialNum(); // 시리얼 번호 가져오기
            String[] ret = serialNum.split("-");
            String manufactureNum = ret[4]; // 제조번호

            String completeSerialNum = itemNM + "-" + whsPk + "-" + lpnCode + "-" + expirationDate + "-" + manufactureNum;
        System.out.println("💎💎💎 화장품 적재 중입니다 💎💎💎");
            superService.insertDetailItem(new ItemDetailDto(completeSerialNum,itemPk,itemDetailStatus,locatePk,expirationDate));


    }
    private void updareLocateCnt(int itemCnt, String locateLpnCode) {
        int result = superService.updareLocateCnt(itemCnt, locateLpnCode); // 로케이트 적재 수량 업데이트
        System.out.println("📦📦📦 로케이트 적재 수량 정보가 업데이트 됐습니다 📦📦📦");
    }
    private void updareItemCnt(int itemCnt, long itemPk) {
        int result = superService.updareItemCnt(itemCnt, itemPk); // 로케이트 적재 수량 업데이트
        System.out.println("📦📦📦 화장품 수량이 업데이트 됐습니다 📦📦📦");
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
