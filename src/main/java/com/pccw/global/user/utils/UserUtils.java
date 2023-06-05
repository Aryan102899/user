package com.pccw.global.user.utils;

import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtils {
    /**
     * 电话号码的正则验证
     */
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^((13[0-9])|(14[14-8])|(15[^4,\\D])|(16[6-7])|(17[0-9])|(18[0-9])|(19[189]))\\d{8}|(98762)\\d{6}$");

    /**
     * 邮件的正则验证
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9A-Z][a-z0-9A-Z_\\-|\\.]*@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");


    /**
     * 验证是否是有效的电话号码
     *
     * @param mobile 电话号码
     * @return 是否是有效的电话号码
     */
    public static boolean validMobile(String mobile) {
        Matcher matcher = MOBILE_PATTERN.matcher(mobile);
        return matcher.matches();
    }

    /**
     * 验证是否是有效的邮件地址
     *
     * @param email
     * @return
     */
    public static boolean validEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    /**
     * 生成长三十二位的UUID（不含"—"）。
     *
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 针对密码进行加盐存储
     *
     * @param password
     * @return
     */
    public static String saltPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
