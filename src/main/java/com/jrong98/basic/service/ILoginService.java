package com.jrong98.basic.service;

import com.jrong98.basic.domain.dto.SignInResultDTO;
import com.jrong98.basic.domain.vo.SignInVO;

/**
 * 登录服务
 * @author jrong98
 * @date 2022/6/21
 */
public interface ILoginService {

    /**
     * 后台用户登录
     * @param modelVO vo
     * @return
     */
    SignInResultDTO signIn(SignInVO modelVO);

}
