package com.sh.controller;

import com.sh.model.dao.FactoryMapper;
import com.sh.model.dto.FactoryDto;
import com.sh.model.service.FactoryService;
import com.sh.view.FactoryView;

import java.util.List;

public class FactoryController {
    private FactoryService factoryService = new FactoryService();

    public void searchFactory() {
        try {
            List<FactoryDto> list = factoryService.searchFactory();
            FactoryView.displayOrderList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}