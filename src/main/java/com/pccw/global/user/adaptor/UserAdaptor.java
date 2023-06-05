package com.pccw.global.user.adaptor;

import com.pccw.global.user.domain.entity.UserEntity;
import com.pccw.global.user.domain.request.CreateUserRequest;
import com.pccw.global.user.domain.response.QueryUserResponse;
import com.pccw.global.user.utils.UserUtils;

public class UserAdaptor {

    public static UserEntity convertUserEntity(CreateUserRequest createUserRequest) {
        UserEntity user = new UserEntity();
        user.setName(createUserRequest.getName());
        user.setPassword(UserUtils.saltPassword(createUserRequest.getPassword()));
        user.setMobile(createUserRequest.getMobile());
        user.setEmail(createUserRequest.getEmail());
        return user;
    }

    public static QueryUserResponse convertQueryUserResponse(UserEntity userEntity) {
        QueryUserResponse queryUserResponse = new QueryUserResponse();
        queryUserResponse.setId(userEntity.getId());
        queryUserResponse.setName(userEntity.getName());
        queryUserResponse.setMobile(userEntity.getMobile());
        queryUserResponse.setEmail(userEntity.getEmail());
        queryUserResponse.setStatus(userEntity.getStatus());
        queryUserResponse.setCreateTime(userEntity.getCreateTime());
        return queryUserResponse;
    }

}
