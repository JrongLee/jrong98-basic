package com.jrong98.basic.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.JdbcType;

/**
 * 基本的实体
 * @author jrong98
 * @date 2022/6/20
 */
@Getter
@Setter
public class BaseEntity extends RequireEntity {

    /**
     * 数据id（通常是雪花算法生成的主键）
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 软删除
     */
    @JsonIgnore
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "deleted", select = false, jdbcType = JdbcType.TINYINT)
    private Boolean deleted;
}
