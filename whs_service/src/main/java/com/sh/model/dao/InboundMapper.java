package com.sh.model.dao;

import com.sh.model.dto.InboundDto;

import java.util.List;

public interface InboundMapper {

    List<InboundDto> insertInbound();

    boolean checkItemDefault();
    void confirmInbound();
    void rejectInbound();
}
