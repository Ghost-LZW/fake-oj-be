package com.soullan.fakeojbe.modle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@TableName("fake_problems")
@Data
public class ProblemEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ID = "id";
    private static final String SUBMIT = "submitCount";
    private static final String SOLVED = "solvedCount";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String HINT = "hint";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull
    @TableField(SUBMIT)
    private Integer submitCount;

    @NotNull
    @TableField(SOLVED)
    private Integer solvedCount;

    @NotNull
    @TableField(TITLE)
    private String title;

    @NotNull
    @TableField(CONTENT)
    private String content;

    @NotNull
    @TableField(HINT)
    private String hint;
}
