package com.sh.model.dao;

import com.sh.model.dto.GbgDetailDto;
import com.sh.model.dto.InboundDto;

public interface InboundMapper {

    // 입고 승인
    int findEmptyLocate(); // 빈 창고가 있는지 확인
    int insertInbToINB(InboundDto inboundDto); // JSON 데이터를 dto로 바꿔서 INB_TB에 넣기
    int insertInbToItemTb(InboundDto inboundDto); // state가 1인 정상 제품 ITEM_TB에 insert
    int insertInbToItemDetailTb(InboundDto inboundDto); // state가 1인 정상 제품 ITEM_DETAIL_TB에 insert

    // 입고 정보 조회 (입고 승인과 입고 확정 때 쓰인다)
    InboundDto findByInbId(int inbIdPk); // INB_TB의 PK로 한 데이터의 모든 정보를 조회

    // 입고 검수
    int insertInbToGbgDetail(GbgDetailDto gbgDetailDto); // GBG_DETAIL_TB에 state가 2,3인 불량 제품을 insert
    int deleteItemDetial(); // GBG_DETAIL_TB에 insert한 불량품은 ITEM_DETAIL_TB에서 delete

    // 재고에 있는 상품이 또 들어오면 재고 수량 더하기 update
    int getItemCnt(int inbItemPk); // INB_TB에서 수량 가져오기
    void updateItemCntWithSum(int inbItemPk, int inbItemCnt); // ITEM_TB의 수량에 위의 값 더하기

    // ITEM_DETIAL_TB에서 state가 2,3인 불량품이 들어오면 재고 수량 빼기 update
    int getItemDetailState(); // ITEM_DETAIL_TB에서 상태가 불량품만 count
    void updateItemCntWithMinus(int itemDetailPk, int faultyItemCnt); // ITEM_DETAIL_TB의 pk와 같은 ITEM_TB의 수량에 위의 값 빼기
}
