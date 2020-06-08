package com.soullan.fakeojbe.modle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@TableName("fake_news")
public class NewsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String ID = "ID";
    public static final String NEED = "needName";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @TableField(NEED)
    private Boolean need;

    @NotNull
    @TableField(TITLE)
    private String title;

    @NotNull
    @TableField(CONTENT)
    private String content;

}
