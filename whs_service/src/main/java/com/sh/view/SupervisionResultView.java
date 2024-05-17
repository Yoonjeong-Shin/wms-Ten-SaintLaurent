package com.sh.view;

import com.sh.model.dto.ItemDto;

import java.util.List;

public class SupervisionResultView {
    public static void displayItemIdNNm(List<ItemDto> list) {
        for(ItemDto item : list) {
            System.out.print(item.getItemIdPk() + ". " + item.getItemNm() + "\n");
        }
        System.out.println();
    }
}
