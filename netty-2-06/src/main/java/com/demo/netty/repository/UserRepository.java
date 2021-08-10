package com.demo.netty.repository;

import com.demo.netty.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 11:22
 */
public interface UserRepository extends ElasticsearchCrudRepository<User,String> {
    /**
     * 通过名称查询
     * @param name 名字
     * @param pageable 分页参数
     * @return 用户集合
     */
    Page<User> findByName(String name, Pageable pageable);
}
