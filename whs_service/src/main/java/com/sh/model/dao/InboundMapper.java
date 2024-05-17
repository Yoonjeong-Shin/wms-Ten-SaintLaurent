package com.sh.model.dao;

import com.sh.model.dto.InboundDto;

public interface InboundMapper {

    // 입고 정보 조회 (입고 승인과 입고 확정 때 쓰인다)
    InboundDto findByInboundID(int inboundID); // INB_TB의 한 데이터를 INB_ID_PK로 조회

    // 입고 승인
    int findEmptyLocate(); // LOCATE_TB에서 LOCATE_ITEM_CNT 찾기
    int insertInboundToINB(InboundDto inboundDto); // INB_TB에 JSON 데이터 넣기

    // 입고 검수
    int insertInboundToGBG(InboundDto inboundDto); // GBG_TB에 state가 2,3인 불량 제품을 insert
    int updateInboundCnt(InboundDto inboundDto); // INB_TB에서 불량 제품을 뺀 수량 update
}
