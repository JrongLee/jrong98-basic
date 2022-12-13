package com.jrong98.basic.common.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 基础 Entity
 * @author jrong98
 * @date 2022/6/20
 */
@Getter
@Setter
public class RequireEntity {

    /**
     * 创建时间
     */
    @TableField(value = "created_at", updateStrategy = FieldStrategy.NEVER)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /**
     * 创建者
     */
    @TableField(value = "created_by", updateStrategy = FieldStrategy.NEVER)
    private String createdBy;

    /**
     * 更新时间
     */
    @OrderBy
    @TableField(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    /**
     * 更新日期
     */
    @TableField(value = "updated_by")
    private String updatedBy;

}
