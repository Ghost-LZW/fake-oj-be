package com.soullan.fakeojbe.modle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@TableName("fake_role")
@Data
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String ID = "role_id";
    public static final String NAME = "role_name";
    public static final String DESC = "role_desc";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer roleID;

    @NotNull(message = "role name can't be empty")
    @TableField(NAME)
    private String roleName;

    @TableField(DESC)
    private String roleDescribe;
}
