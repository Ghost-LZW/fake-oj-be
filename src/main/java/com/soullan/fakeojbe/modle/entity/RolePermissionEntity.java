package com.soullan.fakeojbe.modle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@TableName("role_permission")
@Data
public class RolePermissionEntity {
    private static final long serialVersionUID = 1L;
    public static final String ID = "ID";
    public static final String RID = "rid";
    public static final String PID = "pid";

    @TableId(value = ID, type = IdType.AUTO)
    @NotNull
    private Integer id;

    @NotNull
    @TableField(RID)
    private Integer rid;

    @NotNull
    @TableField(PID)
    private Integer pid;
}
