package com.jrong98.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jrong98.basic.domain.User;
import com.jrong98.basic.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 用户信息 Mapper
 * @author jrong98
 * @date 2022/6/21
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 更新用户最后信息
     * @param lastSignInIp  最后登录的ip
     * @param userId        用户id
     * @return
     */
    int updateLastSignInInfo(@Param(value = "lastSignInIp") String lastSignInIp, @Param(value = "userId") Long userId);

    /**
     * 分页查询
     * @param pg 分页参数
     * @param ps 查询参数
     * @return
     */
    Page<UserDTO> selectPageDTO(@Param(value = "pg") IPage<?> pg, @Param(value = "ps") Map<String, Object> ps);
}
