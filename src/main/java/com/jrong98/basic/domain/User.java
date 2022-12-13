package com.jrong98.basic.domain;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jrong98.basic.common.entity.TenantEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * 用户信息
 * @author jrong98
 * @date 2022/6/21
 */
@Getter
@Setter
@TableName(value = "t_u_user")
public class User extends TenantEntity {

    /**
     * 邮箱
     * @mock kakarotto@gmail.com
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户名称
     * @mock 卡卡罗特
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 用户状态
     * 0:激活的
     * 1:停用的
     * @mock 0
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 密码
     * @mock 1B8F6B0008D91BA52EED8A7571CFB631
     */
    @JsonIgnore
    @TableField(value = "password")
    private String password;

    /**
     * 用户头像
     * @mock /user/path/1468444648.png
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 角色
     * 0: 无角色
     * 1: 游客
     * 2: 会员
     * 4: 管理员
     * @mock 1
     */
    @TableField(value = "_roles")
    private Integer roles;

    /**
     * 最后登入时间
     * @mock 2020-01-01 08:00:00
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "_last_sign_in_at", insertStrategy = FieldStrategy.NEVER)
    private Date lastSignInAt;

    /**
     * 最后登入的ip
     * @mock 8.8.8.8
     */
    @TableField(value = "INET_NTOA(`_last_sign_in_ip`)", property = "_last_sign_in_ip", insertStrategy = FieldStrategy.NEVER, jdbcType = JdbcType.INTEGER)
    private String lastSignInIp;
}
