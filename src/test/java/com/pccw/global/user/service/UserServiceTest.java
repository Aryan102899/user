package com.pccw.global.user.service;

import com.pccw.global.user.UserApplication;
import com.pccw.global.user.constant.UserStatus;
import com.pccw.global.user.domain.request.CreateUserRequest;
import com.pccw.global.user.domain.request.QueryUserListRequest;
import com.pccw.global.user.domain.request.UpdateUserPasswordRequest;
import com.pccw.global.user.domain.request.UpdateUserRequest;
import com.pccw.global.user.domain.response.QueryUserResponse;
import com.pccw.global.user.web.PageResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testQueryTest() {
        String name = "aryan";
        String password = "123456";
        QueryUserResponse queryUserResponse = userService.query(name, password);
        assertNotNull(queryUserResponse);
    }

    @Test
    public void testListTest() {
        QueryUserListRequest queryUserListRequest = new QueryUserListRequest();
        queryUserListRequest.setName("aryan");
        queryUserListRequest.setMobile("15524059989");
        queryUserListRequest.setEmail("zheng_sirshine@163.com");
        queryUserListRequest.setStatus(UserStatus.ENABLE.getCode());
        queryUserListRequest.setOffset(0L);
        queryUserListRequest.setCount(10L);
        PageResponse<QueryUserResponse> response = userService.search(queryUserListRequest);
        assertNotNull(response);
    }

    @Test
    public void createTest() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName("aryan");
        createUserRequest.setMobile("15524059989");
        createUserRequest.setPassword("123456");
        createUserRequest.setEmail("zheng_sirshine@163.com");
        Long res = userService.create(createUserRequest);
        assertNotNull(res);
    }

    @Test
    public void deleteTest() {
        Long userId = 1L;
        Long res = userService.delete(userId);
        assertNotNull(res);
    }

    @Test
    public void updateTest() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId(1L);
        updateUserRequest.setMobile("15524059989");
        updateUserRequest.setEmail("zheng_sirshine@163.com");
        Long res = userService.update(updateUserRequest);
        assertNotNull(res);
    }

    @Test
    public void updatePasswordTest() {
        UpdateUserPasswordRequest updateUserPasswordRequest = new UpdateUserPasswordRequest();
        updateUserPasswordRequest.setId(1L);
        updateUserPasswordRequest.setPrevPassword("123456");
        updateUserPasswordRequest.setCurrentPassword("654321");
        Long res = userService.updatePassword(updateUserPasswordRequest);
        assertNotNull(res);
    }
}
