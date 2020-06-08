package com.soullan.fakeojbe.modle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@TableName("fake_permission")
@Data
public class PermissionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String ID = "per_id";
    public static final String MODEL = "model_name";
    public static final String PERMISSION = "permission";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer permissionID;

    @NotNull()
    @TableField(MODEL)
    private String modelName;

    @NotNull
    @TableField(PERMISSION)
    private String permission;
}
