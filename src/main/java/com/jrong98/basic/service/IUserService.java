package com.jrong98.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jrong98.basic.domain.User;
import com.jrong98.basic.domain.vo.UserPageVO;

/**
 * 用户信息 Service
 * @author jrong98
 * @date 2022/6/21
 */
public interface IUserService extends IService<User> {

    /**
     * 更新用户最后的登录信息
     * @param lastSignInIp  最后登录的 IP
     * @param userId        用户id
     * @return
     */
    int updateUserLastSignIn(String lastSignInIp, Long userId);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<?> pagesDTO(UserPageVO page);

}
