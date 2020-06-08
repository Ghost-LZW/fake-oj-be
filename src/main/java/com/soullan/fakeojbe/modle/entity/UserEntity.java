package com.soullan.fakeojbe.modle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@TableName("fake_user")
@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String ID = "user_id";
    public static final String NAME = "user_name";
    public static final String PWD = "user_pwd";
    public static final String EMAIL = "user_email";
    public static final String SUBMIT = "user_submit_count";
    public static final String SOLVED = "user_solved_count";
    public static final String PROBLEM = "user_problem_count";
    public static final String LOCKED = "user_is_locked";
    public static final String ADMIN= "user_is_admin";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer userId;

    @NotBlank(message = "用户名不能为空")
    @TableField(NAME)
    private String userName;

    @NotNull(message = "密码不能为空")
    @TableField(PWD)
    private String userPwd;

    @Email(message = "邮箱格式不正确")
    @TableField(EMAIL)
    private String userEmail;

    @NotNull
    @TableField(SUBMIT)
    private Integer userSubmitCount;

    @NotNull
    @TableField(SOLVED)
    private Integer userSolvedCount;

    @NotNull
    @TableField(PROBLEM)
    private Integer userProblemCount;

    @NotNull
    @TableField(LOCKED)
    private Boolean userIsLocked;

    @NotNull
    @TableField(ADMIN)
    private Boolean userIsAdmin;
}
