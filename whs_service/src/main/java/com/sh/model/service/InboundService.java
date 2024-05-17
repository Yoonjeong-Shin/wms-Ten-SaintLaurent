package com.sh.model.service;

import com.sh.model.dao.InboundMapper;
import com.sh.model.dto.InboundDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InboundService {

    public int approveInbound(InboundDto inboundDto) {
        /*
        // 양희윤 작업 시작
        SqlSession sqlSession = getSqlSession();
        InboundDto inboundDto = sqlSession.getMapper(inboundMapper.)

        // 양희윤 작업 끝
        */
        return 0;
    }

    public int checkInbound(int inboundID) {
        return 0;
    }

    public void showInboundResult(InboundDto inboundDto) {

    }
}


