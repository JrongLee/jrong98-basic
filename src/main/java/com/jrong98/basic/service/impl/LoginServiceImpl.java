package com.jrong98.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jrong98.basic.common.ServiceErr;
import com.jrong98.basic.common.entity.BaseEntity;
import com.jrong98.basic.common.exception.ServiceException;
import com.jrong98.basic.common.util.JwtUtils;
import com.jrong98.basic.common.util.Utils;
import com.jrong98.basic.domain.User;
import com.jrong98.basic.domain.dto.SignInResultDTO;
import com.jrong98.basic.domain.vo.SignInVO;
import com.jrong98.basic.service.ILoginService;
import com.jrong98.basic.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 登录服务 Impl
 *
 * @author jrong98
 * @date 2022/6/21
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IUserService userService;

    @Override
    public SignInResultDTO signIn(SignInVO modelVO) {
        Wrapper<User> condition = Wrappers.lambdaQuery(User.class)
                .eq(User::getEmail, modelVO.getEmail());
        User user = userService.getOne(condition);
        if (Utils.isNull(user, BaseEntity::getId)) {
            user = new User();
            user.setPassword("Hello World!");
        }
        // 1: disabled
        if (Utils.INT_1.equals(user.getStatus())) {
            throw new ServiceException(ServiceErr.SIGN_IN_ERROR__NOT_ALLOWED);
        }
        if (DigestUtils.sha256Hex(modelVO.getPassword()).equalsIgnoreCase(user.getPassword())) {
            String token = JwtUtils.generateToken(user);
            SignInResultDTO result = new SignInResultDTO();
            result.setClient(RandomStringUtils.random(22, true, true).toUpperCase());
            result.setAccessToken(token);
            result.setExpiry(JwtUtils.parseToken(token).getExpiration().getTime() / 1000L);
            result.setUid(user.getEmail());
            result.setTokenType("Bearer");

            userService.updateUserLastSignIn(modelVO.getRequestIp(), user.getId());
            return result;
        }
        throw new ServiceException(ServiceErr.SIGN_IN_ERROR__INCORRECT_ACCOUNT_OR_PASSWORD);
    }
}
