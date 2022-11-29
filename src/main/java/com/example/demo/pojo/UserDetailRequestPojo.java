package com.example.demo.pojo;



import com.example.demo.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailRequestPojo {

    private Integer userId;
    @NotNull(message = "User name cannot be null")
    @NotBlank(message = "User name cannot be blank")
    private String userName;
    private String password;

    private UserType userType;


    private Long studentDetailId;

}