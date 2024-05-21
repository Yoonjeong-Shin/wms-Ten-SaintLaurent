package com.sh.model.service;

import com.sh.FacResponseClient;
import com.sh.SelOutResponseClient;
import com.sh.model.dao.LocateMapper;
import com.sh.model.dao.SupervisionMapper;
import com.sh.model.dto.*;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class SupervisionService {
    private SqlSession sqlSession = null;
    public void get_SqlSession() {
        if (sqlSession == null) {
            sqlSession = getSqlSession();
        }
    }
    public void setSqlSessionCommitNotClient(){
        try {
            sqlSession.commit();
        }catch (Exception E){
            sqlSession.rollback();
            throw new RuntimeException(E);
        }
        finally {
            sqlSession.close();
        }

    }
    public void setSqlSessionCommit(){
        try {
            sqlSession.commit();
            FacResponseClient clientTask2 = new FacResponseClient( new Socket("localhost", 8890),true);
            clientTask2.run();
        }catch (Exception E){
            sqlSession.rollback();
            FacResponseClient clientTask2 = null;
            try {
                clientTask2 = new FacResponseClient( new Socket("localhost", 8890),false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            clientTask2.run();
        }
        finally {
            sqlSession.close();
        }

    }
    //    public static void main(String[] args) {
//        SupervisionService supervisionService = new SupervisionService();
//        supervisionService.searchSameItemLpn()
//    }
    // 화장품 정보 전체 조회
    public List<SearchItemDto> searchItemInfo() {
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        List<SearchItemDto> list = superMapper.searchItemInfo();

        return list;
    }

    // 특정 화장품의 총 개수 조회
    public int searchItemCnt(int itemId) {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        int itemCnt = superMapper.searchItemCnt(itemId);

        return itemCnt;
    }

    // 특정 화장품의 lpn 조회
    public List<LocateDto> searchItemLpn(long itemPk) {

        LocateMapper locateMapper = sqlSession.getMapper(LocateMapper.class);
        List<LocateDto> list = locateMapper.searchItemLpn(itemPk);

        return list;
    }
    public LocateDto searchItemDetailLpn(long itemDetailPk) {

        LocateMapper locateMapper = sqlSession.getMapper(LocateMapper.class);
        LocateDto lpn = locateMapper.searchItemDetailLpn(itemDetailPk);
        SupervisionMapper SupervisionMapper = sqlSession.getMapper(SupervisionMapper.class);

        return lpn;
    }


    // 로케이션 빈공간 조회
    public List<LocateDto> searchLpn(long whsPK) {

        LocateMapper locateMapper = sqlSession.getMapper(LocateMapper.class);
        List<LocateDto> list = locateMapper.searchLpn(whsPK);

        return list;
    }

    public List<ItemDto> searchItemIdNNm() {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        List<ItemDto> list = superMapper.searchItemIdNNm();

        return list;
    }

    // 화장품 품목명이 일치하는 항목이 있는지 체크
    public int searchItemCat(String catNm) {
//        System.out.println("ser");

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        int existenceVal = superMapper.searchItemCat(catNm);
//        System.out.println(existenceVal);

        return existenceVal;
    }

    public int insertCatItem(List<ItemCatDto> itemCatList) {
//        System.out.println("ser");

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);

            System.out.println(itemCatList);
            int result = superMapper.insertCatItem(itemCatList);
            return result;

    }

    public int insertItem(List<ItemDto> itemList) {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);

            int result = superMapper.insertItem(itemList);

            return result;

    }
    public Long getWhsPk(String adminId) {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);

            long pk = superMapper.getWhsPk(adminId);
            return pk;

    }
    public String getWhsNm(long adminId) {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);

            String whsNm = superMapper.getWhsNm(adminId);
            return whsNm;

    }

    public void insertDetailItem(ItemDetailDto itemDetailDto) {

            SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);

                int result = superMapper.insertDetailItem(itemDetailDto);




    }

    // item_detail_tb에서 itemNm으로 itemId 조회
    public Long searchItemId(String itemNm) {


        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        Long itemId = superMapper.searchItemId(itemNm);


        return itemId;
    }

    public List<LocateDto> searchSameItemLpn(long itemId) {

        System.out.println(itemId + " 아이템아이디 서비스");
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        List<LocateDto> locateList = superMapper.searchSameItemLpn(itemId);

        return locateList;
    }

    public int searchCheckItemNm(String itemNm) {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        int result = superMapper.searchCheckItemNm(itemNm);
//        System.out.println("result : " + result);

        return result;
    }

    public int deleteItem() {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);

            // 불량 조회
            List<ItemDetailDto> itemDetail = superMapper.searchDefItem();

            // 화장품의 불량 제품과 유통기한 임박된 제품을 폐기테이블에 추가한다
            for(ItemDetailDto item : itemDetail) {
                GbgDetailDto gbgDetailDto = new GbgDetailDto(0, item.getItemPk(), item.getItemDetailStatus(), item.getItemDetailSerialNum());
                int gbgInsertResult = superMapper.insertGbgDetail(gbgDetailDto);
                System.out.println(gbgInsertResult);
            }

            // 화장품 폐기
            int result = 0;
            for(ItemDetailDto item : itemDetail) {
//                long itemDetailPk = item.getItemDetailPk();
//                result = superMapper.deleteDetailItem(itemDetailPk);
            }
            return result;

    }

    public int updareLocateCnt(int itemCnt, String locateLpnCode) {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);

            int result = superMapper.updareLocateCnt(itemCnt, locateLpnCode);

            return result;

    }
    public int updareItemCnt(int itemCnt, long itemPk) {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);

            int result = superMapper.updareItemCnt(itemCnt, itemPk);

            return result;

    }

    public int searchWhsLoc(String facLoc) {

        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        int whsId = superMapper.searchWhsLoc(facLoc);

        return whsId;
    }

}
