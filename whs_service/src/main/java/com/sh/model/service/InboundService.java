package com.sh.model.service;

import com.sh.model.dao.InboundMapper;
import com.sh.model.dao.SupervisionMapper;
import com.sh.model.dto.GbgDetailDto;
import com.sh.model.dto.InboundDto;
import com.sh.model.dto.json.SelInboundOrder;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InboundService {

    // 입고 승인
    // LOCATE_TB에서 LOCATE_ITEM_CNT 찾기
    public int findEmptyLocate() {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        int result = inboundMapper.findEmptyLocate();
        sqlSession.close();
        return result;
    }

    public int chckSeller(String selNm){
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int cnt = inboundMapper.chckSeller(selNm);
            sqlSession.commit();
            return cnt;

        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();

        }
    }

    public void insertSeller(String selNm){
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            if(chckSeller(selNm) == 0)
                inboundMapper.insertSeller(selNm);
            sqlSession.commit();

        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
    public void insertFac(String facNm){
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int check = inboundMapper.chackFac(facNm);
            if(check == 0){
                inboundMapper.insertFac(facNm);
            }
            sqlSession.commit();

        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
    public void insertItem(InboundDto inboundDto){
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper supervisionMapper = sqlSession.getMapper(SupervisionMapper.class);
        try {
            // dao 메세지 전달
            insertSeller(inboundDto.getSelNm());
            insertFac(inboundDto.getFacNm());
            if(supervisionMapper.searchCheckItemNm(inboundDto.getItemNm()) == 0) {
                if (supervisionMapper.searchCheckItemCat(inboundDto.getItemCatNm()) == 0) {
                    supervisionMapper.insertCatItemOne(inboundDto.getItemCatNm());
                }
                supervisionMapper.insertItemOne(inboundDto.getItemNm(),inboundDto.getInbItemVol(), inboundDto.getItemCatNm());
            }
            sqlSession.commit();

        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
    // 입고 승인 - JSON 데이터를 dto로 바꿔서 INB_TB에 넣기
    public int insertInbToINB(InboundDto inboundDto) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.insertInbToINB(inboundDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
    // JSON에서 얻은 state가 1인 정상 제품은 ITEM_TB와 ITEM_DETAIL_TB에 insert
    public int insertInbToItemTb(InboundDto inboundDto) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.insertInbToItemTb(inboundDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
    public int insertInbToItemDetailTb(InboundDto inboundDto) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.insertInbToItemDetailTb(inboundDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    // 입고 정보 조회
    // INB_TB의 PK로 한 데이터의 모든 정보를 조회, 입고 승인과 입고 확정 때 쓰인다
    public InboundDto findByInbId(int inbIdPk) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        InboundDto inboundDto = inboundMapper.findByInbId(inbIdPk);
        sqlSession.close();
        return inboundDto;
    }

    // 입고 검수
    // JSON에서 얻은 state가 2,3인 불량품은 GBG_DETAIL_TB에 insert
    public int insertInbToGbgDetail(GbgDetailDto gbgDetailDto) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.insertInbToGbgDetail(gbgDetailDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
    // GBG_DETAIL_TB에 insert한 불량품은 item_detail_tb에서 delete
    public int deleteItemDetail() {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // dao 메세지 전달
            int result = inboundMapper.deleteItemDetial();
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    // 재고에 있는 상품이 또 들어오면 재고 수량 더하기 update
    // INB_TB에서 수량 가져오기
    public void updateCntWithSum(int inbItemPk) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // INB_TB에서 수량 가져오기
            int result = inboundMapper.getItemCnt(inbItemPk);
            // ITEM_TB의 수량에 위의 값 더하기
            inboundMapper.updateItemCntWithSum(inbItemPk, result);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    // ITEM_DETIAL_TB에서 state가 2,3인 불량품이 들어오면 재고 수량 빼기 update
    public void updateCntWithMinus(int itemDetailPk) {
        SqlSession sqlSession = getSqlSession();
        InboundMapper inboundMapper = sqlSession.getMapper(InboundMapper.class);
        try {
            // ITEM_DETAIL_TB에서 상태가 불량품만 count
            int result = inboundMapper.getItemDetailState();
            // ITEM_DETAIL_TB의 pk와 같은 ITEM_TB의 수량에 위의 값 빼기
            inboundMapper.updateItemCntWithMinus(itemDetailPk, result);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
}
