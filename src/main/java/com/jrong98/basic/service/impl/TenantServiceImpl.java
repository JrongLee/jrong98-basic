package com.jrong98.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jrong98.basic.dao.TenantMapper;
import com.jrong98.basic.domain.Tenant;
import com.jrong98.basic.service.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 租户信息表 Service Impl
 * @author jrong98
 * @date 2022/6/8
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public Tenant getById(Serializable id) {
        Tenant model = (Tenant) redisTemplate.opsForValue().get(id);
        if (model == null) {
            model = super.getById(id);
            redisTemplate.opsForValue().set(id.toString(), model);
            redisTemplate.expire(id.toString(), 8, TimeUnit.HOURS);
        }
        return model;
    }
}

