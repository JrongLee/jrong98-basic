package com.jrong98.basic.common.handle;

import com.jrong98.basic.common.ServiceErr;
import com.jrong98.basic.common.entity.ResponseWrapper;
import com.jrong98.basic.common.util.JsonUtil;
import com.jrong98.basic.common.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

/**
 * 授权拦截器
 * @author jrong98
 * @date 2022/6/22
 */
public class AuthenticateHandleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(accessToken)) {
            handleNotAuthorized(request, response, handler);
            return false;
        }
        String tokenPrefix = "Bearer";
        if (accessToken.startsWith(tokenPrefix)) {
            accessToken = accessToken.substring(tokenPrefix.length()).trim();
        }
        String tenantId = "000000";
        try {
            Claims claims = JwtUtils.parseToken(accessToken);
            if (new Date().after(claims.getExpiration())) {
                handleNotAuthorized(request, response, handler);
                return false;
            }
            tenantId = Objects.toString(claims.get(JwtUtils.CLAIMS_TENANT), tenantId);
        } catch (JwtException e) {
            handleNotAuthorized(request, response, handler);
            return false;
        }
        request.isUserInRole("admin");
        request.setAttribute("tenantId", tenantId);
        return true;
    }

    protected void handleNotAuthorized(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        ResponseWrapper wrapper = new ResponseWrapper()
                .setErrcode(ServiceErr.SE4001.getCode())
                .setErrmsg("unauthorized");

        try (PrintWriter writer = response.getWriter()) {
            writer.write(JsonUtil.convertToJsonStr(wrapper));
            writer.flush();
        }
    }
}
