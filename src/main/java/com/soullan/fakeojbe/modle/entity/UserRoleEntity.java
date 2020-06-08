package com.soullan.fakeojbe.modle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@TableName("user_role")
@Data
public class UserRoleEntity {
    private static final long serialVersionUID = 1L;
    public static final String ID = "ID";
    public static final String UID = "uid";
    public static final String RID = "rid";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull
    @TableField(UID)
    private Integer uid;

    @NotNull
    @TableField(RID)
    private Integer rid;

}
