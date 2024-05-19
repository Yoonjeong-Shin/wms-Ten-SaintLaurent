package com.sh.model.dao;

import com.sh.model.dto.GbgDetailDto;
import com.sh.model.dto.InboundDto;

public interface InboundMapper {

    // 입고 승인
    int findEmptyLocate(); // 빈 창고가 있는지 확인
    int insertInbToINB(InboundDto inboundDto); // JSON 데이터를 dto로 바꿔서 INB_TB에 넣기
    int insertInbToItemTb(InboundDto inboundDto); // state가 1인 정상 제품 item_tb에 insert
    int insertInbToItemDetailTb(InboundDto inboundDto); // state가 1인 정상 제품 item_detail_tb에 insert

    // 입고 정보 조회 (입고 승인과 입고 확정 때 쓰인다)
    InboundDto findByInbId(int inbIdPk); // INB_TB의 PK로 한 데이터의 모든 정보를 조회

    // 입고 검수
    int insertInbToGbgDetail(GbgDetailDto gbgDetailDto); // GBG_DETAIL_TB에 state가 2,3인 불량 제품을 insert
    int deleteItemDetial(); // gbg_detail_tb에 insert한 불량품은 item_detail_tb에서 delete
//    int updateInbCnt(InboundDto inboundDto); // 재고 수량 update
}
