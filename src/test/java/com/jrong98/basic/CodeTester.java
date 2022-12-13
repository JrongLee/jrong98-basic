package com.jrong98.basic;

import com.jrong98.basic.common.util.JwtUtils;
import com.jrong98.basic.domain.User;
import io.jsonwebtoken.Claims;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * @author jrong98
 * @date 2022/6/21
 */
public class CodeTester {

    @org.junit.Test
    public void jwtTest() {
        User user = new User();
        user.setId(6944985214800105472L);
        user.setNickname("卡卡罗特");
        user.setEmail("kakarotto@gmail.com");
        user.setTenantId("000000");
        user.setAvatar("/user/path/1443487849.png");
        String token = JwtUtils.generateToken(user);
        System.out.println(token);
        Assert.notNull(token, "token is null.");
    }

    @Test
    public void jwtTest2() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJUZW5hbnQiOiIwMDAwMDAiLCJVc2VybmFtZSI6Imtha2Fyb3R0b0BnbWFpbC5jb20iLCJJZCI6Njk0NDk4NTIxNDgwMDEwNTQ3MiwiZXhwIjoxNjU2NDE5NDY2LCJpYXQiOjE2NTU4MTQ2NjYsIkF2YXRhciI6Ii91c2VyL3BhdGgvMTQ0MzQ4Nzg0OS5wbmciLCJOaWNrbmFtZSI6IuWNoeWNoee9l-eJuSJ9.8l_KSBHjHA3M-5BHgqKnldq-XLwGMf4JYrvJHWuISS0";
        Claims claims = JwtUtils.parseToken(token);
        System.out.println(claims);
    }

    @Test
    public void password() {
        String md5Hex = DigestUtils.md5Hex("kakarotto123");
        System.out.println(md5Hex);
        String sha256Hex = DigestUtils.sha256Hex(md5Hex);
        System.out.println(sha256Hex.toUpperCase());
    }

}
