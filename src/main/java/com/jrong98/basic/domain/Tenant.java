package com.jrong98.basic.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jrong98.basic.common.entity.RequireEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 租户信息表
 * @author jrong98
 * @date 2022/6/8
 */
@Getter
@Setter
@TableName(value = "t_s_tenant")
public class Tenant extends RequireEntity implements Serializable {

    private static final long serialVersionUID = -2269515214692197865L;

    /**
     * 租户id
     */
    @TableId(value = "tenant_id", type = IdType.INPUT)
    private String tenantId;

    /**
     * 租户名称
     */
    @TableField(value = "tenant_name")
    private String tenantName;

    /**
     * 租户域名
     */
    @TableField(value = "domain")
    private String domain;

}
