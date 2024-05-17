package com.sh.model.dao;

import com.sh.model.dto.OutboundDto;

import java.util.List;

public interface OutboundMapper {
    List<OutboundDto> searchOutbound(int userId);
    boolean writeOutBoundReport(int userId);
    int assignCart();
    List<OutboundDto> OutboundPicking();
    boolean checkOutbound();
    boolean confirmOutbound();
    boolean rejectPicking();
    List<OutboundDto> rejectCheck();

}
