package com.sh.model.service;

import com.sh.model.dao.OutboundMapper;
import com.sh.model.dto.*;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class OutboundService {

    public boolean createOutBItemDetailTB(String itemNM, int itemCNT, long cartPk){
        SqlSession sqlSession = getSqlSession();
        List<OutItemDetailDto> outItemDetailDto = new ArrayList<>();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            // dao 메세지 전달
            outboundMapper.createOutbItemDetailTB(itemNM,itemCNT,cartPk);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);

        } finally {
            sqlSession.close();
        }
    }
    public  long selectForDeleteItemDetail(String itemNM){
        SqlSession sqlSession = getSqlSession();
        List<OutItemDetailDto> outItemDetailDto = new ArrayList<>();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            // dao 메세지 전달
            long LocPk = outboundMapper.selectForDeleteItemDetail(itemNM);
            sqlSession.commit();
            return LocPk;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);

        } finally {
            sqlSession.close();
        }
    }
    public boolean deleteOutBItemDetailTB(long itemDetailPk){
        SqlSession sqlSession = getSqlSession();
        List<OutItemDetailDto> outItemDetailDto = new ArrayList<>();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            // dao 메세지 전달
            outboundMapper.deleteCNTItemDetailTB(itemDetailPk);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    // 출고 검수
//    public List<OutItemDetailDto> selectItems(String itemNM) {
//        SqlSession sqlSession = getSqlSession();
//        List<OutItemDetailDto> outItemDetailDto = new ArrayList<>();
//        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
//        try {
//            // dao 메세지 전달
//            outItemDetailDto = outboundMapper.selectItems(itemNM);
//            sqlSession.commit();
//            return outItemDetailDto;
//        } catch (Exception e) {
//            sqlSession.rollback();
//            throw new RuntimeException(e);
//        } finally {
//            sqlSession.close();
//        }
//    }


    public long createOutbTB(String cusNM){
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            // dao 메세지 전달
            String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            long outBPk = outboundMapper.createOutbTB(cusNM,formattedDate);
            sqlSession.commit();
            return outBPk;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public long checkItemCount(String itemNM){
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            long ItemCount = outboundMapper.checkItemCount(itemNM);
            sqlSession.commit();
            return ItemCount;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
    public long createOutbDetailTB(long outbPk,String itemNM,int itemCNT){
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            long outDetailBPk = outboundMapper.createOutbDetailTB(outbPk,itemNM,itemCNT);
            sqlSession.commit();
            return outDetailBPk;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
    public long createOutbCartTB(long outDetailPk,int itemCNT){
        SqlSession sqlSession = getSqlSession();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
        try {
            long outbCartPk = outboundMapper.createOutbCartTB(outDetailPk,itemCNT);
            sqlSession.commit();
            return outbCartPk;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
}

