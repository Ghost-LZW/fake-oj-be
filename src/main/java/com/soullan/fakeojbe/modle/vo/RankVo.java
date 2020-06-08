package com.soullan.fakeojbe.modle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private Integer solvedCount;
    private Integer submitCount;
    private Integer problemCount;
    private Boolean locked;
    private Boolean canAdd;
}