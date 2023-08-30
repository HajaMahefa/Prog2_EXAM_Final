package com.hajamodel.modealice.Model;

import lombok.*;

import java.math.BigDecimal;
import java.security.Timestamp;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Order {
    private int id;
    private int userId;
    private String orderDate;
    private BigDecimal totalAmount;
}
