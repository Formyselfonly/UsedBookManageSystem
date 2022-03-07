package com.southwind.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User{

    @Id
    private Integer id;
    private String username;
    private String password;
    private String token;
    private String IfAdminstor;
}
