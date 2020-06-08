package com.soullan.fakeojbe.modle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private String userEmail;
    private Integer submitCount;
    private Integer solvedCount;
    private Boolean isAdmin;
    private Boolean canAdd;
}
