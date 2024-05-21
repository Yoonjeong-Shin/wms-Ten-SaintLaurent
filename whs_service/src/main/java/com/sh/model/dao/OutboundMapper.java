package com.sh.model.dao;

import com.sh.model.dto.*;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface OutboundMapper {

    List<ItemDetailDto> selectItems(@Param("itemNM") String itemNM);

    int checkItemCount(@Param("itemNM") String itemNM);

    void deleteCNTItemDetailTB(@Param("itemDetailPk") long itemDetailPk);

    void createOutbItemDetailTB(@Param("itemNM") String itemNM, @Param("itemCNT") int itemCNT, @Param("cartPk") long cartPk);

    long createOutbTB(@Param("customerNM") String customerNM, @Param("NowDay") String nowDay);

    long createOutbDetailTB(@Param("outbPk") long outbPk,@Param("itemNM") String itemNM, @Param("itemCNT") int itemCNT);

    long createOutbCartTB(@Param("OutDetailPk") long outDetailPk, @Param("itemCNT") int itemCNT);
    // 슈퍼비전에 있는 아이템, 로케이트 재고 뺴는 함수 쓸것.
    long selectForDeleteItemDetail(@Param("itemNM") String itemNM);
}
