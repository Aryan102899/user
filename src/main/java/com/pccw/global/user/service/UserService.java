package com.pccw.global.user.service;

import com.pccw.global.user.adaptor.UserAdaptor;
import com.pccw.global.user.constant.UserErrorCode;
import com.pccw.global.user.constant.UserStatus;
import com.pccw.global.user.dao.UserDao;
import com.pccw.global.user.domain.entity.UserEntity;
import com.pccw.global.user.domain.request.CreateUserRequest;
import com.pccw.global.user.domain.request.QueryUserListRequest;
import com.pccw.global.user.domain.request.UpdateUserPasswordRequest;
import com.pccw.global.user.domain.request.UpdateUserRequest;
import com.pccw.global.user.domain.response.QueryUserResponse;
import com.pccw.global.user.utils.UserUtils;
import com.pccw.global.user.web.PageResponse;
import com.pccw.global.user.web.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;


    public QueryUserResponse query(String name, String password) {
        UserEntity user = userDao.findUserByNameAndPassword(name, UserUtils.saltPassword(password));
        if (user == null) {
            return new QueryUserResponse();
        }
        return UserAdaptor.convertQueryUserResponse(user);
    }

    public PageResponse<QueryUserResponse> list(QueryUserListRequest queryUserListRequest) {
        List<UserEntity> userEntities = userDao.queryUserEntityListByConditions(queryUserListRequest);
        List<QueryUserResponse> res = userEntities.stream().map(UserAdaptor::convertQueryUserResponse).collect(Collectors.toList());
        Long total = userDao.countUserEntityListByConditions(queryUserListRequest);
        return new PageResponse<>(queryUserListRequest.getOffset(), queryUserListRequest.getCount(), total, res);
    }

    public Long create(CreateUserRequest createUserRequest) {
        long count = userDao.countUserByName(createUserRequest.getName());
        if (count > 0) {
            throw new UserException(UserErrorCode.NAME_EXIST, UserErrorCode.NAME_EXIST.getDescription());
        }
        UserEntity userEntity = UserAdaptor.convertUserEntity(createUserRequest);
        UserEntity save = userDao.save(userEntity.create());
        applicationEventPublisher.publishEvent(userEntity);
        return save.getId();
    }

    public Long delete(Long id) {
        UserEntity user = userDao.findUserById(id);
        if (user == null) {
            return -1L;
        }
        return userDao.updateUserStatus(UserStatus.DISABLE.getCode(), id);
    }

    public Long update(UpdateUserRequest updateUserRequest) {
        UserEntity user = userDao.findUserById(updateUserRequest.getId());
        if (user == null) {
            throw new UserException(UserErrorCode.ID_NOT_EXIST, UserErrorCode.ID_NOT_EXIST.getDescription());
        }
        if (updateUserRequest.getMobile() == null && updateUserRequest.getEmail() == null) {
            return -1L;
        }
        if (updateUserRequest.getMobile() != null && !user.getMobile().equals(updateUserRequest.getMobile())) {
            user.setMobile(updateUserRequest.getMobile());
        }
        if (updateUserRequest.getEmail() != null && !user.getEmail().equals(updateUserRequest.getEmail())) {
            user.setEmail(updateUserRequest.getEmail());
        }
        UserEntity update = userDao.save(user.update());
        return update.getId();
    }

    public Long updatePassword(UpdateUserPasswordRequest updateUserPasswordRequest) {
        UserEntity user = userDao.findUserById(updateUserPasswordRequest.getId());
        if (user == null) {
            throw new UserException(UserErrorCode.ID_NOT_EXIST, UserErrorCode.ID_NOT_EXIST.getDescription());
        }
        String prev = UserUtils.saltPassword(updateUserPasswordRequest.getPrevPassword());
        if (!prev.equals(user.getPassword())) {
            throw new UserException(UserErrorCode.PREV_PASSWORD_NOT_MATCH,
                    UserErrorCode.PREV_PASSWORD_NOT_MATCH.getDescription());
        }
        if (prev.equals(updateUserPasswordRequest.getCurrentPassword())) {
            throw new UserException(UserErrorCode.SAME_PASSWORD, UserErrorCode.SAME_PASSWORD.getDescription());
        }
        String current = UserUtils.saltPassword(updateUserPasswordRequest.getCurrentPassword());
        user.setPassword(current);
        UserEntity update = userDao.save(user.update());
        return update.getId();
    }
}
