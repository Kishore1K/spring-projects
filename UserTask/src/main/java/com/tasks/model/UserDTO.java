package com.tasks.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;

}
