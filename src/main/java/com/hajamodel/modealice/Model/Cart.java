package com.hajamodel.modealice.Model;

import lombok.*;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cart {
    private int id;
    private int userId;
    private Timestamp creationDate;
    private int quantity;



}





