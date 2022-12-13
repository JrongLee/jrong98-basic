package com.jrong98.basic.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrong98.basic.domain.User;
import com.jrong98.basic.module.jackson.annotation.EnumOutput;
import com.jrong98.basic.module.jackson.ser.std.EnumOutputSerializer;
import com.jrong98.basic.module.jackson.ser.std.MoneySerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 用户信息 DTO
 * @author jrong98
 * @date 2022/6/22
 */
@Getter
@Setter
@JsonIgnoreProperties(value = {"createdBy", "updatedAt", "updatedBy"})
public class UserDTO extends User {

    /**
     * 用户总佣金
     * @mock 6000.00
     */
    @TableField(value = "total_commission_amount")
    @JsonSerialize(using = MoneySerializer.class)
    private Integer totalCommissionAmount;

    /**
     * 用户总下注金额
     * @mock 5500.00
     */
    @TableField(value = "total_play_amount")
    @JsonSerialize(using = MoneySerializer.class)
    private Integer totalPlayAmount;

    /**
     * 用户总返将金额
     * @mock 11000.00
     */
    @TableField(value = "total_win_amount")
    @JsonSerialize(using = MoneySerializer.class)
    private Integer totalWinAmount;

    /**
     * 中奖率
     * @mock 50
     * @return
     */
    @JsonProperty(value = "wonRate")
    public Integer getWonRate() {
        return 0;
    }

    @Override
    @JsonProperty(value = "status")
    @EnumOutput(value = { "active", "disabled" })
    @JsonSerialize(using = EnumOutputSerializer.class)
    public Integer getStatus() {
        return super.getStatus();
    }

    @Override
    @JsonIgnore
    public Integer getRoles() {
        return super.getRoles();
    }

    @JsonProperty(value = "role")
    public Set<String> getRole() {
        String[] roles = {"unknown", "guest", "member", "admin"};
        Map<Integer, String> roleBitMapping = new HashMap<>(4, 1);
        for (int i = 0; i < roles.length; i++) {
            roleBitMapping.put(1 << i >> 1, roles[i]);
        }
        Set<String> role = new HashSet<>(4, 1);
        for (Integer bit : roleBitMapping.keySet()) {
            if ((getRoles() & bit) > 0) {
                role.add(roleBitMapping.get(bit));
            }
        }
        if (getRoles() == 0) {
            role.add(roles[0]);
        }
        return role;
    }
}
