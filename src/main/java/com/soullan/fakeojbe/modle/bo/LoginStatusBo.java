package com.soullan.fakeojbe.modle.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginStatusBo implements Serializable {
    private Boolean loginFlag;
    private Integer userId;
    private String userName;
    private Integer userType;
    private Integer permission;
}
