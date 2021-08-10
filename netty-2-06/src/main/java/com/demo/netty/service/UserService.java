package com.demo.netty.service;

import com.demo.netty.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 11:24
 */
public interface UserService {
    /**
     * 单条保存
     * @param user 用户实体
     */
    void save(User user);

    /**
     * 通过主键删除
     * @param id id
     */
    void deleteById(String id);

    /**
     * 通过主键查询
     * @param id id
     * @return 用户实体
     */
    User queryUserById(String id);

    /**
     * 查询全部
     * @return 用户集合
     */
    Iterable<User> queryAll();

    /**
     * 通过名称查询
     * @param name 名字
     * @param  pageable 分页参数
     * @return 用户集合
     */
    Page<User> findByName(String name, Pageable pageable);
}
