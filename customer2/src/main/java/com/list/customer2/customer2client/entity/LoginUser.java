package com.list.customer2.customer2client.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser implements Serializable {
    private String username;
    private String password;
}
