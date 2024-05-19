package com.sh.model.dao;

import com.sh.model.dto.FactoryDto;
import com.sh.model.dto.json.InbJsonDto;

import java.util.List;

public interface FactoryMapper {

    List<InbJsonDto> searchFactory();

}
