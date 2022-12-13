package com.jrong98.basic.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jrong98.basic.domain.Tenant;

/**
 * 租户信息表 Mapper
 * @author jrong98
 * @date 2022/6/8
 */
@InterceptorIgnore(tenantLine = "true")
public interface TenantMapper extends BaseMapper<Tenant> {
}
