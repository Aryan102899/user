package com.pccw.global.user.service;

import com.pccw.global.user.UserApplication;
import com.pccw.global.user.constant.UserStatus;
import com.pccw.global.user.domain.entity.UserEntity;
import com.pccw.global.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testInsert() {
        UserEntity user = new UserEntity();
        user.setName("aryan");
        user.setPassword("111");
        user.setEmail("aryan_1028@gmail.com");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(UserStatus.ENABLE.getCode());
        userRepository.save(user);
        assertNotNull(user);
    }
}
