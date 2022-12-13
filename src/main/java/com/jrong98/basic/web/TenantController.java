package com.jrong98.basic.web;

import com.jrong98.basic.common.annotation.ResponseBodyWrapper;
import com.jrong98.basic.domain.Tenant;
import com.jrong98.basic.service.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jrong98
 * @date 2022/6/8
 */
@RestController
@RequestMapping(value = "tenant")
public class TenantController {

    @Autowired
    private ITenantService tenantService;

    @PostMapping
    public String save(@RequestBody Tenant model) {
        tenantService.save(model);
        return "success";
    }

    @GetMapping(value = "{tenantId}")
    @ResponseBodyWrapper
    public Tenant find(@PathVariable(value = "tenantId")String tenantId) {
        Tenant tenant = tenantService.getById(tenantId);
        return tenant;
    }
}
