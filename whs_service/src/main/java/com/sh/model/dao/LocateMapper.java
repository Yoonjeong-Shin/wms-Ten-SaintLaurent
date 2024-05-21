package com.sh.model.dao;

import com.sh.model.dto.LocateDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LocateMapper {
    List<LocateDto> searchLpn();

    List<LocateDto> searchItemLpn(long itemPk);
    LocateDto searchItemDetailLpn(@Param("itemDetailPk") long itemDetailPk);
}
