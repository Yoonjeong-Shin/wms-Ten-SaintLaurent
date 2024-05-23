package com.sh.model.service;

import com.sh.SelOutResponseClient;
import com.sh.model.dao.LocateMapper;
import com.sh.model.dao.OutboundMapper;
import com.sh.model.dao.SupervisionMapper;
import com.sh.model.dto.*;
import com.sh.SelInbResponseClient;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class OutboundService {
    private SqlSession sqlSession = null;
    public void get_SqlSession() {
        if (sqlSession == null) {
            sqlSession = getSqlSession();
        }
    }
    public void setSqlSessionRollback(){
        sqlSession.rollback();
    }
    public void setSqlSessionClose(){
        sqlSession.close();
    }
    public void setSqlSessionCommit(){
        try {
            sqlSession.commit();
            SelOutResponseClient clientTask2 = new SelOutResponseClient( new Socket("localhost", 8891),true);
            clientTask2.run();
        }catch (Exception E){
            sqlSession.rollback();
            SelOutResponseClient clientTask2 = null;
            try {
                clientTask2 = new SelOutResponseClient( new Socket("localhost", 8891),false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            clientTask2.run();
        }
        finally {
            sqlSession.close();
        }

    }

    public boolean createOutBItemDetailTB(String itemNM, int itemCNT, long cartPk){
        List<OutItemDetailDto> outItemDetailDto = new ArrayList<>();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);

            outboundMapper.createOutbItemDetailTB(itemNM,itemCNT,cartPk);
            return true;

    }
    public Long searchItemId(String itemNm) {


        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        Long itemId = superMapper.searchItemId(itemNm);


        return itemId;
    }
    public int updareItemCnt(int itemCnt, long itemPk) {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);

        int result = superMapper.updareItemCnt(itemCnt, itemPk);

        return result;

    }
    public LocateDto searchItemDetailLpn(long itemDetailPk) {

        LocateMapper locateMapper = sqlSession.getMapper(LocateMapper.class);
        LocateDto lpn = locateMapper.searchItemDetailLpn(itemDetailPk);
        SupervisionMapper SupervisionMapper = sqlSession.getMapper(SupervisionMapper.class);

        return lpn;
    }
    public int updareLocateCnt(int itemCnt, String locateLpnCode) {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);

        int result = superMapper.updareLocateCnt(itemCnt, locateLpnCode);

        return result;

    }

    public  long selectForDeleteItemDetail(String itemNM){

        List<OutItemDetailDto> outItemDetailDto = new ArrayList<>();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);

            // dao 메세지 전달
            long LocPk = outboundMapper.selectForDeleteItemDetail(itemNM);

            return LocPk;

    }
    public boolean deleteOutBItemDetailTB(long itemDetailPk){
        List<OutItemDetailDto> outItemDetailDto = new ArrayList<>();
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
            // dao 메세지 전달
            outboundMapper.deleteCNTItemDetailTB(itemDetailPk);
            return true;

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
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
            // dao 메세지 전달
            String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long outBPk = outboundMapper.createOutbTB(cusNM,formattedDate);
            return outBPk;

    }

    public long checkItemCount(String itemNM){
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
            long ItemCount = outboundMapper.checkItemCount(itemNM);
            return ItemCount;

    }
    public long createOutbDetailTB(long outbPk,String itemNM,int itemCNT){
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
             long outDetailBPk = outboundMapper.createOutbDetailTB(outbPk,itemNM,itemCNT);
            return outDetailBPk;

    }
    public long createOutbCartTB(long outDetailPk,int itemCNT){
        OutboundMapper outboundMapper = sqlSession.getMapper(OutboundMapper.class);
            long outbCartPk = outboundMapper.createOutbCartTB(outDetailPk,itemCNT);
            return outbCartPk;

    }
}

