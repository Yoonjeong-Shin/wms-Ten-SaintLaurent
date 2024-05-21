package com.sh.model.service;

import com.sh.SelOutResponseClient;
import com.sh.model.dao.InboundMapper;
import com.sh.model.dao.SupervisionMapper;
import com.sh.model.dto.GbgDetailDto;
import com.sh.model.dto.InboundDto;
import com.sh.SelInbResponseClient;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.net.Socket;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InboundService {
    private SqlSession sqlSession = null;
    // 입고 승인
    // LOCATE_TB에서 LOCATE_ITEM_CNT 찾기
    public int findEmptyLocate() {

        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        int result = inboundMapper.findEmptyLocate();
        sqlSession.close();
        return result;
    }

        public void get_SqlSession() {
            if (sqlSession == null) {
                sqlSession = getSqlSession();
            }
        }
    public void setSqlSessionCommit(){
        try {
            sqlSession.commit();
            SelInbResponseClient clientTask2 = new SelInbResponseClient( new Socket("localhost", 8891),true);
            clientTask2.run();
        }catch (Exception E){
            sqlSession.rollback();
            SelInbResponseClient clientTask2 = null;
            try {
                clientTask2 = new SelInbResponseClient( new Socket("localhost", 8891),false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            clientTask2.run();


        }
        finally {
            sqlSession.close();
        }

    }


    public int chckSeller(String selNm){

        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        // dao 메세지 전달
            int cnt = inboundMapper.chckSeller(selNm);
            return cnt;

    }

    public void insertSeller(String selNm){

        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);

            // dao 메세지 전달
            if(chckSeller(selNm) == 0)
                inboundMapper.insertSeller(selNm);

    }
    public void insertFac(String facNm){
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
            int check = inboundMapper.chackFac(facNm);
            if(check == 0){
                inboundMapper.insertFac(facNm);
            }
    }
    public boolean insertItem(InboundDto inboundDto){
        SupervisionMapper supervisionMapper = sqlSession.getMapper(SupervisionMapper.class);
            // dao 메세지 전달
            insertSeller(inboundDto.getSelNm());
            insertFac(inboundDto.getFacNm());
            if(supervisionMapper.searchCheckItemNm(inboundDto.getItemNm()) == 0) {
                if (supervisionMapper.searchCheckItemCat(inboundDto.getItemCatNm()) == 0) {
                    supervisionMapper.insertCatItemOne(inboundDto.getItemCatNm());
                }
                supervisionMapper.insertItemOne(inboundDto.getItemNm(),inboundDto.getInbItemVol(), inboundDto.getItemCatNm());
            }
            return true;
    }
    // 입고 승인 - JSON 데이터를 dto로 바꿔서 INB_TB에 넣기
    public boolean insertInbToINB(InboundDto inboundDto) {
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
            // dao 메세지 전달
            int result = inboundMapper.insertInbToINB(inboundDto);
            return true;
    }
    // JSON에서 얻은 state가 1인 정상 제품은 ITEM_TB와 ITEM_DETAIL_TB에 insert
    public int insertInbToItemTb(InboundDto inboundDto) {
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
            // dao 메세지 전달
            int result = inboundMapper.insertInbToItemTb(inboundDto);
            return result;
    }
    public int insertInbToItemDetailTb(InboundDto inboundDto) {
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
            // dao 메세지 전달
            int result = inboundMapper.insertInbToItemDetailTb(inboundDto);
            return result;
     }

    // 입고 정보 조회
    // INB_TB의 PK로 한 데이터의 모든 정보를 조회, 입고 승인과 입고 확정 때 쓰인다
    public InboundDto findByInbId(int inbIdPk) {
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        InboundDto inboundDto = inboundMapper.findByInbId(inbIdPk);
        return inboundDto;
    }

    // 입고 검수
    // JSON에서 얻은 state가 2,3인 불량품은 GBG_DETAIL_TB에 insert
    public int insertInbToGbgDetail(GbgDetailDto gbgDetailDto) {

        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
            // dao 메세지 전달
            int result = inboundMapper.insertInbToGbgDetail(gbgDetailDto);
            return result;
    }
    // GBG_DETAIL_TB에 insert한 불량품은 item_detail_tb에서 delete
    public int deleteItemDetail() {
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
            // dao 메세지 전달
            int result = inboundMapper.deleteItemDetial();
            return result;
    }

    // 재고에 있는 상품이 또 들어오면 재고 수량 더하기 update
    // INB_TB에서 수량 가져오기
    public void updateCntWithSum(int inbItemPk) {
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
            // INB_TB에서 수량 가져오기
            int result = inboundMapper.getItemCnt(inbItemPk);
            // ITEM_TB의 수량에 위의 값 더하기
            inboundMapper.updateItemCntWithSum(inbItemPk, result);
    }

    // ITEM_DETIAL_TB에서 state가 2,3인 불량품이 들어오면 재고 수량 빼기 update
    public void updateCntWithMinus(int itemDetailPk) {
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
           // ITEM_DETAIL_TB에서 상태가 불량품만 count
            int result = inboundMapper.getItemDetailState();
            // ITEM_DETAIL_TB의 pk와 같은 ITEM_TB의 수량에 위의 값 빼기
            inboundMapper.updateItemCntWithMinus(itemDetailPk, result);
    }
}
