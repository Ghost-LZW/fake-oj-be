package com.soullan.fakeojbe.modle.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Integer SUCCESS = 0;
    public static final Integer FAIL = 1;
    private Integer statue;
    private String code;
    private String message;
    private Object data;
}
