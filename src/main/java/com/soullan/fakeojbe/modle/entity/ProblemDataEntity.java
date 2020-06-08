package com.soullan.fakeojbe.modle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@TableName("fake_problem_data")
public class ProblemDataEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ID = "id";
    private static final String PID = "pid";
    private static final String DATA = "data";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private String id;

    @NotNull
    @TableField(PID)
    private Integer pid;

    @NotNull
    @TableField(DATA)
    private String data;
}
