package com.soullan.fakeojbe.modle.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer pid;

    private String author;

    private Integer status;

    private Integer answerLength;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date submitTime;
}
