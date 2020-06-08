package com.soullan.fakeojbe.modle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer submitCount;
    private Integer solvedCount;
    private String title;
}
