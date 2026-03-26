package com.example.enterprise.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginInput {
    @NotNull(message = "用户名不能为null")
    @NotEmpty(message = "用户名不能为空串")
    private String username;

    @NotNull(message = "密码不能为null")
    @NotEmpty(message = "密码不能为空串")
    private String password;
}
