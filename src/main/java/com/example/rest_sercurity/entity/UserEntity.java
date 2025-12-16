package com.example.rest_sercurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserEntity {

    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    private String role;

    private String extraInfo;

}
