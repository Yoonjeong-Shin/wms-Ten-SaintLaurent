package com.sh.model.service;

import com.sh.model.dto.OutboundDto;

import java.util.ArrayList;
import java.util.List;

public class OutboundService {
    public List<OutboundDto> searchOutbound(int userId){
        return new ArrayList<>();
    }

    public boolean writeOutBoundReport(int userId){
        return false;
    }

    public int assignCart(){
        return 0;
    }

    public List<OutboundDto> OutboundPicking(){
        return new ArrayList<>();
    }

    public boolean checkOutbound(){
        return false;
    }

    public boolean confirmOutbound(){
        return false;
    }

    public boolean rejectPicking(){
        return false;
    }

    public List<OutboundDto> rejectcheck(){
        return new ArrayList<>();
    }
}
