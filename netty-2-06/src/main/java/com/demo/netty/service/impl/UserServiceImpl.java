package com.demo.netty.service.impl;

import com.demo.netty.domain.User;
import com.demo.netty.repository.UserRepository;
import com.demo.netty.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 11:28
 */
@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User queryUserById(String id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.get();
    }

    @Override
    public Iterable<User> queryAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findByName(String name, Pageable pageable) {
        return userRepository.findByName(name, pageable);
    }
}
