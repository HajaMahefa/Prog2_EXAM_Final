package com.hajamodel.modealice.Model;

import lombok.*;


@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class User {
    private int id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
}
