package com.sh.model.service;

import com.sh.model.dao.LocateMapper;
import com.sh.model.dao.SupervisionMapper;
import com.sh.model.dto.*;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class SupervisionService {
//    public static void main(String[] args) {
//        SupervisionService supervisionService = new SupervisionService();
//        supervisionService.searchSameItemLpn()
//    }
    // 화장품 정보 전체 조회
    public List<SearchItemDto> searchItemInfo() {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        List<SearchItemDto> list = superMapper.searchItemInfo();
        sqlSession.close();
        return list;
    }

    // 특정 화장품의 총 개수 조회
    public int searchItemCnt(int itemId) {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        int itemCnt = superMapper.searchItemCnt(itemId);
        sqlSession.close();
        return itemCnt;
    }

    // 특정 화장품의 lpn 조회
    public List<LocateDto> searchItemLpn(long itemPk) {
        SqlSession sqlSession = getSqlSession();
        LocateMapper locateMapper = sqlSession.getMapper(LocateMapper.class);
        List<LocateDto> list = locateMapper.searchItemLpn(itemPk);
        sqlSession.close();
        return list;
    }
    public LocateDto searchItemDetailLpn(long itemDetailPk) {
        SqlSession sqlSession = getSqlSession();
        LocateMapper locateMapper = sqlSession.getMapper(LocateMapper.class);
        LocateDto lpn = locateMapper.searchItemDetailLpn(itemDetailPk);
        SupervisionMapper SupervisionMapper = sqlSession.getMapper(SupervisionMapper.class);

        sqlSession.close();
        return lpn;
    }


    // 로케이션 빈공간 조회
    public List<LocateDto> searchLpn(long whsPK) {
        SqlSession sqlSession = getSqlSession();
        LocateMapper locateMapper = sqlSession.getMapper(LocateMapper.class);
        List<LocateDto> list = locateMapper.searchLpn(whsPK);
        sqlSession.close();
        return list;
    }

    public List<ItemDto> searchItemIdNNm() {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        List<ItemDto> list = superMapper.searchItemIdNNm();
        sqlSession.close();
        return list;
    }

    // 화장품 품목명이 일치하는 항목이 있는지 체크
    public int searchItemCat(String catNm) {
//        System.out.println("ser");
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        int existenceVal = superMapper.searchItemCat(catNm);
//        System.out.println(existenceVal);
        sqlSession.close();
        return existenceVal;
    }

    public int insertCatItem(List<ItemCatDto> itemCatList) {
//        System.out.println("ser");
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        try {
            System.out.println(itemCatList);
            int result = superMapper.insertCatItem(itemCatList);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException();
            // To-do 에러처리
        } finally {
            sqlSession.close();
        }
    }

    public int insertItem(List<ItemDto> itemList) {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        try {
            int result = superMapper.insertItem(itemList);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            sqlSession.close();
        }
    }
    public Long getWhsPk(String adminId) {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        try {
            long pk = superMapper.getWhsPk(adminId);
            sqlSession.commit();
            return pk;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            sqlSession.close();
        }
    }
    public String getWhsNm(long adminId) {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        try {
            String whsNm = superMapper.getWhsNm(adminId);
            sqlSession.commit();
            return whsNm;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            sqlSession.close();
        }
    }

    public void insertDetailItem(ItemDetailDto itemDetailDto) {
            SqlSession sqlSession = getSqlSession();
            SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
            try {
                int result = superMapper.insertDetailItem(itemDetailDto);
                sqlSession.commit();

            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                throw new RuntimeException();
            } finally {
                sqlSession.close();
            }


    }

    // item_detail_tb에서 itemNm으로 itemId 조회
    public Long searchItemId(String itemNm) {

        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        Long itemId = superMapper.searchItemId(itemNm);
        sqlSession.close();

        return itemId;
    }

    public List<LocateDto> searchSameItemLpn(long itemId) {
        SqlSession sqlSession = getSqlSession();
        System.out.println(itemId + " 아이템아이디 서비스");
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        List<LocateDto> locateList = superMapper.searchSameItemLpn(itemId);
        sqlSession.close();
        return locateList;
    }

    public int searchCheckItemNm(String itemNm) {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        int result = superMapper.searchCheckItemNm(itemNm);
//        System.out.println("result : " + result);
        sqlSession.close();
        return result;
    }

    public int deleteItem() {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        try {
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
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public int updareLocateCnt(int itemCnt, String locateLpnCode) {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        try {
            int result = superMapper.updareLocateCnt(itemCnt, locateLpnCode);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException();
        } finally {
            sqlSession.close();
        }
    }
    public int updareItemCnt(int itemCnt, long itemPk) {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        try {
            int result = superMapper.updareItemCnt(itemCnt, itemPk);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException();
        } finally {
            sqlSession.close();
        }
    }

    public int searchWhsLoc(String facLoc) {
        SqlSession sqlSession = getSqlSession();
        SupervisionMapper superMapper = sqlSession.getMapper(SupervisionMapper.class);
        int whsId = superMapper.searchWhsLoc(facLoc);
        sqlSession.close();
        return whsId;
    }

}
