package com.hajamodel.modealice.DTO;

import lombok.*;
import org.springframework.context.annotation.Bean;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserDto {
    private String lastName;
    private String firstName;

}
