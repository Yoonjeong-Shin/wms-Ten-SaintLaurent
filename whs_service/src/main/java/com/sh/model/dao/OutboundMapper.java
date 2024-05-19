package com.sh.model.dao;

import com.sh.model.dto.*;

import java.util.List;

public interface OutboundMapper {
    List<OrderDto> searchOutbound();
    List<PurchaseListDto> printOutBoundReport();
    List<OutBoundCartDto> assignCart();
    List<OutBoundPickDto> OutboundPicking();
    int checkOutbound(OutboundCheckDto outboundCheckDto);
    int confirmOutbound(LocateDto locateDto);
}
