package com.sh.model.dao;

import com.sh.model.dto.InboundDto;

public interface InboundMapper {

    // 입고 승인

    // 빈 창고가 있는지 확인
    int findEmptyLocate();
    int insertInboundToINB(InboundDto inboundDto); // INB_TB에 JSON 데이터 넣기

    /* 지영 작업 시작 */

    // 입고 정보 조회 (입고 승인과 입고 확정 때 쓰인다)
    InboundDto findByInbId(int inbIdPk); // INB_TB의 한 데이터의 모든 정보를 INB_ID_PK로 조회
    // 입고 검수
    int insertInbToGbg(InboundDto inboundDto); // GBG_TB에 state가 2,3인 불량 제품을 insert
    int insertInbToGbgDetail(InboundDto inboundDto); // GBG_DETAIL_TB에 state가 2,3인 불량 제품을 insert
    int updateInbCnt(InboundDto inboundDto); // INB_TB에서 불량 제품을 뺀 수량 update

    /* 지영 작업 끝 */
}
