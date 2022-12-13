package com.jrong98.basic.common.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * 租户基础实体
 * @author jrong98
 * @date 2022/6/20
 */
@Getter
@Setter
public class TenantEntity extends BaseEntity {

    /**
     * 租户id
     */
    @JsonIgnore
    @TableField(value = "tenant_id", updateStrategy = FieldStrategy.NEVER)
    private String tenantId;
}
