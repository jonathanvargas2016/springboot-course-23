package com.jonathan.service.item.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private Date createAt;
}
