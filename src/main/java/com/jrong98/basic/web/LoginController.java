package com.jrong98.basic.web;

import com.jrong98.basic.common.annotation.ResponseBodyWrapper;
import com.jrong98.basic.domain.dto.SignInResultDTO;
import com.jrong98.basic.domain.vo.SignInVO;
import com.jrong98.basic.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登入控制器
 * @author jrong98
 * @date 2022/6/21
 */
@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    /**
     * 用户登录
     * @param modelVO vo
     * @return
     */
    @PostMapping(value = "auth/sign-in")
    @ResponseBodyWrapper
    public SignInResultDTO signIn(HttpServletRequest request, @Validated @RequestBody SignInVO modelVO) {
        request.setAttribute("tenantId", modelVO.getTenantId());
        modelVO.setRequestIp(request.getRemoteHost());
        return loginService.signIn(modelVO);
    }

}
