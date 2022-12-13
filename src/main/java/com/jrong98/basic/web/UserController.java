package com.jrong98.basic.web;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jrong98.basic.common.annotation.ResponseBodyWrapper;
import com.jrong98.basic.common.util.Utils;
import com.jrong98.basic.domain.User;
import com.jrong98.basic.domain.vo.UserPageVO;
import com.jrong98.basic.domain.vo.UserPasswdVO;
import com.jrong98.basic.domain.vo.UserVO;
import com.jrong98.basic.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 用户控制器
 *
 * @author jrong98
 * @date 2022/6/22
 */
@RestController
@RequestMapping(value = "users")
@ResponseBodyWrapper
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 分页查询
     *
     * @param pageVO 分页参数信息
     * @return
     */
    @GetMapping(value = "pages")
    public IPage<?> pages(UserPageVO pageVO) {
        return userService.pagesDTO(pageVO);
    }

    /**
     * 更新用户
     *
     * @param id      用户id
     * @param modelVO 用户信息
     * @return
     */
    @PatchMapping(value = "{id}")
    public Boolean user(@PathVariable(value = "id") Long id, @RequestBody UserVO modelVO) {
        User model = new User();
        model.setId(id);
        setToIfNotEmpty(model::setNickname, modelVO.getNickname());
        setToIfNotEmpty(model::setAvatar, modelVO.getImages());
        setToIfNotEmpty(model::setStatus, modelVO.getStatus(), e -> {
            int index = Arrays.asList("active", "disabled").indexOf(e);
            return index < 0 ? null : index;
        });
        setToIfNotEmpty(model::setRoles, modelVO.getRole(), e -> {
            String[] roles = {"unknown", "guest", "member", "admin"};
            Map<String, Integer> roleBitMapping = new HashMap<>(4, 1);
            for (int i = 0; i < roles.length; i++) {
                roleBitMapping.put(roles[i], 1 << i >> 1);
            }
            return roleBitMapping.get(e);
        });
        return userService.updateById(model);
    }

    /**
     * 设置用户密码
     * @apiNote 管理员只能设置 游客和会员 的密码。管理员不能设置其他管理员的密码
     * @param id      用户id
     * @param modelVO 用户信息
     * @return
     */
    @PatchMapping(value = "{id}/setting-password")
    public Boolean settingPasswd(@PathVariable(value = "id") Long id, @Validated @RequestBody UserPasswdVO modelVO) {
        User model = new User();
        model.setId(id);
        Wrapper<User> condition = Wrappers.update(model)
                .set("password", DigestUtils.sha256Hex(modelVO.getPassword()).toUpperCase())
                .lt("(_roles & 7)", Utils.INT_4);
        return userService.update(model, condition);
    }

    public <T, V> boolean setToIfNotEmpty(Consumer<T> consumer, T value) {
        return setToIfNotEmpty(consumer, value, Function.identity());
    }

    public <T, V> boolean setToIfNotEmpty(Consumer<T> consumer, V value, Function<V, T> cast) {
        if (ObjectUtils.isNotEmpty(value)) {
            T finalValue = cast.apply(value);
            if (ObjectUtils.isNotEmpty(finalValue)) {
                consumer.accept(finalValue);
                return true;
            }
        }
        return false;
    }


}
