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
    boolean checkOutbound();
    boolean confirmOutbound();
    boolean rejectPicking();
    List<OutboundDto> rejectCheck();

}
