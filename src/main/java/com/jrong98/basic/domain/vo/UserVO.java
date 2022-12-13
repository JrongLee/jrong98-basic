package com.jrong98.basic.domain.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户 VO
 * @author jrong98
 * @date 2022/6/23
 */
@Getter
@Setter
public class UserVO {

    @JsonAlias(value = "name")
    public String nickname;

    /**
     * 角色
     * @mock guest|member|admin
     */
    public String role;

    /**
     * 状态
     * @mock active|disabled
     */
    public String status;

    /**
     * 用户头像
     * @mock /user/path/46876878671.png
     */
    public String images;

}
