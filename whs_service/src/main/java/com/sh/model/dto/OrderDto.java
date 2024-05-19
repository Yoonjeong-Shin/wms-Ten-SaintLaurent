package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDto {
    private int orderId;
    private int userId;
    private String CustomerName;
    private String CustomerAdr;
    private List<OrderDetailDto> orderDetailDtoList;
    private Date orderDate;
}
