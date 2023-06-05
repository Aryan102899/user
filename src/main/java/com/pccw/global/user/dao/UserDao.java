package com.pccw.global.user.dao;

import com.pccw.global.user.domain.entity.QUserEntity;
import com.pccw.global.user.domain.entity.UserEntity;
import com.pccw.global.user.domain.request.QueryUserListRequest;
import com.pccw.global.user.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDao {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private UserRepository userRepository;

    private final QUserEntity qUserEntity = QUserEntity.userEntity;

    public Long countUserByName(String name) {
        return userRepository.countUserByName(name);
    }

    public UserEntity findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public UserEntity findUserByNameAndPassword(String name, String password) {
        return userRepository.findUserByNameAndPassword(name, password);
    }

    @Transactional(rollbackFor = Exception.class)
    public long updateUserStatus(Byte status, Long id) {
        return jpaQueryFactory.update(qUserEntity).set(qUserEntity.status, status).where(qUserEntity.id.eq(id)).execute();
    }

    @Transactional(rollbackFor = Exception.class)
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public List<UserEntity> queryUserEntityListByConditions(QueryUserListRequest queryUserListRequest) {
        BooleanBuilder booleanBuilder = builder(queryUserListRequest);
        JPAQuery<UserEntity> query = jpaQueryFactory.selectFrom(qUserEntity).where(booleanBuilder).orderBy(qUserEntity.id.desc());
        return query.offset(queryUserListRequest.getOffset()).limit(queryUserListRequest.getCount()).fetch();
    }

    public Long countUserEntityListByConditions(QueryUserListRequest queryUserListRequest) {
        BooleanBuilder booleanBuilder = builder(queryUserListRequest);
        return jpaQueryFactory.select(qUserEntity.count()).from(qUserEntity).where(booleanBuilder).fetchOne();
    }

    private BooleanBuilder builder(QueryUserListRequest queryUserListRequest) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StringUtils.isNotEmpty(queryUserListRequest.getName())) {
            booleanBuilder.and(qUserEntity.name.eq(queryUserListRequest.getName()));
        }
        if (StringUtils.isNotEmpty(queryUserListRequest.getMobile())) {
            booleanBuilder.and(qUserEntity.mobile.eq(queryUserListRequest.getMobile()));
        }
        if (StringUtils.isNotEmpty(queryUserListRequest.getEmail())) {
            booleanBuilder.and(qUserEntity.email.eq(queryUserListRequest.getEmail()));
        }
        if (queryUserListRequest.getStatus() != null) {
            booleanBuilder.and(qUserEntity.status.eq(queryUserListRequest.getStatus()));
        }
        return booleanBuilder;
    }
}
