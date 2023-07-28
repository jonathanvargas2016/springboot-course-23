package com.jonathan.service.item.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Product product;
    private Integer amount;

    public Double getTotal(){
        return  product.getPrice().doubleValue() * amount.doubleValue();
    }
}
