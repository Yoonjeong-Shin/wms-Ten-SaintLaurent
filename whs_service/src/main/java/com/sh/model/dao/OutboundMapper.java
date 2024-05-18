package com.sh.model.dao;

import com.sh.model.dto.OrderDto;
import com.sh.model.dto.OutboundDto;
import com.sh.model.dto.PurchaseListDto;

import java.util.List;

public interface OutboundMapper {
    List<OrderDto> searchOutbound();
    List<PurchaseListDto> printOutBoundReport();
    int assignCart(OutboundDto outboundDto);
    List<OutboundDto> OutboundPicking();
    int checkOutbound(OutboundDto outboundDto);
    int confirmOutbound();
    int updateOutCnt(OutboundDto inboundDto);

}
