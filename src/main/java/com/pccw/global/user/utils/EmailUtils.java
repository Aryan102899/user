package com.pccw.global.user.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {

    /**
     * 发送邮件
     *
     * @param name 收件人姓名
     * @param to   收件人电子邮箱
     * @param from 发件人电子邮箱
     * @param host 指定发送邮件的主机
     */
    public static void sendEmail(String name, String to, String from, String host) {
        Properties properties = System.getProperties();
        properties.setProperty("email.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Welcome to register for pccw user!");
            message.setText(String.format("hi,dear :%s,registered pccw user successfully!", name));
            Transport.send(message);
            System.out.println("Send message successfully....");
        } catch (MessagingException mex) {
            System.out.println("please waiting....");
        }
    }
}
