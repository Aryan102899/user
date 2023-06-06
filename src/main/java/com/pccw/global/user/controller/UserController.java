package com.pccw.global.user.controller;

import com.pccw.global.user.domain.request.CreateUserRequest;
import com.pccw.global.user.domain.request.QueryUserListRequest;
import com.pccw.global.user.domain.request.UpdateUserPasswordRequest;
import com.pccw.global.user.domain.request.UpdateUserRequest;
import com.pccw.global.user.domain.response.QueryUserResponse;
import com.pccw.global.user.service.UserService;
import com.pccw.global.user.web.PageResponse;
import com.pccw.global.user.web.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @ApiOperation("创建用户")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UserResponse create(@RequestBody @Valid CreateUserRequest createUserRequest) {
        Long userId = userService.create(createUserRequest);
        return UserResponse.createSuccessResponse(userId);
    }

    @ApiOperation("根据用户名和密码查询用户")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public UserResponse query(@NotNull String name, @NotNull String password) {
        QueryUserResponse response = userService.query(name, password);
        return UserResponse.createSuccessResponse(response);
    }

    @ApiOperation("查询用户列表")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public UserResponse search(@RequestBody @Valid QueryUserListRequest queryUserListRequest) {
        PageResponse<QueryUserResponse> response = userService.search(queryUserListRequest);
        return UserResponse.createSuccessResponse(response);
    }


    @ResponseBody
    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public UserResponse delete(@PathVariable("id") Long id) {
        Long userId = userService.delete(id);
        return UserResponse.createSuccessResponse(userId);
    }

    @ResponseBody
    @ApiOperation("更新用户")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public UserResponse update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        Long userId = userService.update(updateUserRequest);
        return UserResponse.createSuccessResponse(userId);
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    @ResponseBody
    public UserResponse updatePassword(@RequestBody UpdateUserPasswordRequest updateUserPasswordRequest) {
        Long userId = userService.updatePassword(updateUserPasswordRequest);
        return UserResponse.createSuccessResponse(userId);
    }
}
