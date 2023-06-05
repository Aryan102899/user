package com.pccw.global.user.listener;

import com.pccw.global.user.domain.entity.UserEntity;
import com.pccw.global.user.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CreateUserListener {

    @Value("${email.smtp.host:smtp.office365.com}")
    private String smtpHost;

    @Value("${email.from.address:pccw_global@pccw.com}")
    private String fromAddress;

    @Async
    @EventListener(condition = "#userEntity.id != null")
    public void handleEvent(UserEntity userEntity) {
        EmailUtils.sendEmail(userEntity.getName(), userEntity.getEmail(), fromAddress, smtpHost);
        System.out.println("send email success!");
    }
}
