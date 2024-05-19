package com.sh.model.dao;

import com.sh.model.dto.*;

import java.util.List;

public interface OutboundMapper {
    // 주문서 조회
    List<OrderDto> searchOutbound();
    // 출고지시서 출력
    List<PurchaseListDto> printOutBoundReport();
    // 카트 할당
    List<OutBoundCartDto> assignCart();
    // 픽킹
    List<OutBoundPickDto> OutboundPicking();

    int checkOutbound(OutboundCheckDto outboundCheckDto);

    int confirmOutbound(LocateDto locateDto);
}
