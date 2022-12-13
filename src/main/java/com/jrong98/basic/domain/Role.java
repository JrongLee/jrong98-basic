package com.jrong98.basic.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jrong98.basic.common.entity.TenantEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色
 * @author jrong98
 * @date 2022/6/21
 */
@Getter
@Setter
@TableName(value = "t_s_roles")
public class Role extends TenantEntity {


    /**
     * 角色键
     * @mock member
     */
    @TableField(value = "role_key")
    private String roleKey;

    /**
     * 角色名称
     * @mock 会员
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 用户初始范围
     * 0:无访问
     * 1:游客
     * 2:普通用户
     * 4:管理员
     * @mock 0
     */
    @TableField(value = "user_initial_range")
    private Integer userInitialRange;

}
