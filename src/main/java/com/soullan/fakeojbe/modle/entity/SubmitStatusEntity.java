package com.soullan.fakeojbe.modle.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("fake_submit_statue")
public class SubmitStatusEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ID = "id";
    private static final String PID = "pid";
    private static final String AUTHOR = "author";
    private static final String STATUE = "status";
    private static final String CONTENT = "content";
    private static final String DATA = "submitTime";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull
    @TableField(PID)
    private Integer pid;

    @NotNull
    @TableField(AUTHOR)
    private Integer author;

    @NotNull
    @TableField(STATUE)
    private Integer status;

    @NotNull
    @TableField(CONTENT)
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField(fill= FieldFill.INSERT, value = DATA)
    private Date submitTime;
}
