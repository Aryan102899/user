package com.pccw.global.user.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public enum UserStatus {
    ENABLE((byte) 0, "启用"),
    DISABLE((byte) 1, "停用");

    private final byte code;
    private final String desc;

    private static final Map<String, UserStatus> NAME_TYPE_MAP = Maps.newHashMap();
    private static final Map<Byte, UserStatus> CODE_TYPE_MAP = Maps.newHashMap();

    static {
        for (UserStatus type : values()) {
            NAME_TYPE_MAP.put(type.name().toLowerCase(), type);
            CODE_TYPE_MAP.put(type.code, type);
        }
    }

    UserStatus(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonCreator
    public static UserStatus forName(String name) {
        return StringUtils.isBlank(name) ? null : NAME_TYPE_MAP.get(name.toLowerCase());
    }

    public static UserStatus forCode(Byte code) {
        return code == null ? null : CODE_TYPE_MAP.get(code);
    }

    public byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
