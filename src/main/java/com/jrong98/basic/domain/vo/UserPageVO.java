package com.jrong98.basic.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户分页查询
 * @author jrong98
 * @date 2022/6/22
 */
@Getter
@Setter
public class UserPageVO extends PageVO {

    /**
     * 角色
     * @mock member
     */
    private String role;

    /**
     * 获取角色
     * @return
     */
    public Integer getRoleIntValue() {
        if (StringUtils.isBlank(getRole())) {
            return -1;
        }
        String[] roles = {"unknown", "guest", "member", "admin"};
        Map<String, Integer> roleBitMapping = new HashMap<>(4, 1);
        for (int i = 0; i < roles.length; i++) {
            roleBitMapping.put(roles[i], 1 << i >> 1);
        }
        return roleBitMapping.getOrDefault(getRole(), -1);
    }
}
