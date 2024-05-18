package com.sh.model.dao;

import com.sh.model.dto.LocateDto;

import java.util.List;

public interface LocateMapper {
    List<LocateDto> searchLpn();

    List<LocateDto> searchItemLpn(int itemPk);
}
