package com.jrong98.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jrong98.basic.dao.UserMapper;
import com.jrong98.basic.domain.User;
import com.jrong98.basic.domain.dto.UserDTO;
import com.jrong98.basic.domain.vo.UserPageVO;
import com.jrong98.basic.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

/**
 * 用户信息 Service Impl
 * @author jrong98
 * @date 2022/6/21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public int updateUserLastSignIn(String lastSignInIp, Long userId) {
        return baseMapper.updateLastSignInInfo(lastSignInIp, userId);
    }

    @Override
    public Page<?> pagesDTO(UserPageVO pageVO) {
        Page<UserDTO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        Map<String, Object> params = Collections.emptyMap();
        if (StringUtils.isNotBlank(pageVO.getRole())) {
            params = Collections.singletonMap("role", pageVO.getRoleIntValue());
        }
        return baseMapper.selectPageDTO(page, params);
    }
}
