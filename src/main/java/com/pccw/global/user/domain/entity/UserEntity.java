package com.pccw.global.user.domain.entity;

import com.pccw.global.user.constant.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PCCW_USER")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String mobile;
    private String email;
    private Byte status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    public UserEntity create() {
        this.status = UserStatus.ENABLE.getCode();
        this.createTime = new Date();
        this.updateTime = new Date();
        return this;
    }

    public UserEntity update() {
        this.updateTime = new Date();
        return this;
    }
}
