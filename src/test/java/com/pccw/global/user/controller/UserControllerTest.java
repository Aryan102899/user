package com.pccw.global.user.controller;

import com.pccw.global.user.UserApplication;
import com.pccw.global.user.domain.request.CreateUserRequest;
import com.pccw.global.user.domain.request.UpdateUserPasswordRequest;
import com.pccw.global.user.domain.request.UpdateUserRequest;
import com.pccw.global.user.web.UserException;
import com.pccw.global.user.web.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void createByIllegalArgTest() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName("aryan");
        createUserRequest.setMobile("15524059989aa");
        createUserRequest.setPassword("123456");
        createUserRequest.setEmail("zheng_sirshine@163.com");
        assertThrows(ConstraintViolationException.class, () -> userController.create(createUserRequest));
    }

    @Test
    public void createByRepetitionTest() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName("aryan");
        createUserRequest.setMobile("15524059989");
        createUserRequest.setPassword("123456");
        createUserRequest.setEmail("zheng_sirshine@163.com");
        UserResponse res = userController.create(createUserRequest);
        assertNotNull(res);
        CreateUserRequest createUserRequest1 = new CreateUserRequest();
        createUserRequest1.setName("aryan");
        createUserRequest1.setMobile("15524059989");
        createUserRequest1.setPassword("123456");
        createUserRequest1.setEmail("zheng_sirshine@163.com");
        assertThrows(UserException.class, () -> userController.create(createUserRequest1));
    }

    @Test
    public void updateNotFoundTest() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId(10086L);
        assertThrows(UserException.class, () -> userController.update(updateUserRequest));
    }

    @Test
    public void updatePasswordNotMatchTest() {
        UpdateUserPasswordRequest updateUserPasswordRequest = new UpdateUserPasswordRequest();
        updateUserPasswordRequest.setId(1L);
        updateUserPasswordRequest.setPrevPassword("1234567");
        updateUserPasswordRequest.setCurrentPassword("12345678");
        assertThrows(UserException.class, () -> userController.updatePassword(updateUserPasswordRequest));
    }
}
