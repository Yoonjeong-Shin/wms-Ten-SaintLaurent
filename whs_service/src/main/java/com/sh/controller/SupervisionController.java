package com.sh.controller;

import com.sh.model.dto.LocateDto;
import com.sh.model.service.SupervisionService;

import java.util.List;

public class SupervisionController {
    private SupervisionService superService = new SupervisionService();

    public void searchLpn() {
        List<LocateDto> list = superService.searchLpn();
    }
}
