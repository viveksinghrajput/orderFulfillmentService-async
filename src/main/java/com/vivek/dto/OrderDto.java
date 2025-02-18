package com.vivek.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private int productId;
    private String name;
    private String productType;
    private int qty;
    private double price;
    private String trackingId;
}
