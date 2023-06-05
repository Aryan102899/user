package com.pccw.global.user.repository;

import com.pccw.global.user.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select count(*) from pccw_user where name = ?1", nativeQuery = true)
    long countUserByName(String name);

    @Query(value = "select * from pccw_user where id = ?1", nativeQuery = true)
    UserEntity findUserById(Long id);

    @Query(value = "select * from pccw_user where name = ?1 and password = ?2", nativeQuery = true)
    UserEntity findUserByNameAndPassword(String name, String password);
}
