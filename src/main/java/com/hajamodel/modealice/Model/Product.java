package com.hajamodel.modealice.Model;

import lombok.*;

import java.util.Set;

@ToString
@AllArgsConstructor
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Setter
public class Product {
    private int id;
    private String name;
    private String description;
    private Float price;
    private int categoryId;
    private String size;
    private String gender;
    private String age;
    private String imageUrl;
    private int capacity;
}
