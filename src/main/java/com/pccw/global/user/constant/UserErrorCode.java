package com.pccw.global.user.constant;

public enum UserErrorCode {
    SUCCESS("success"),
    UNKNOWN_ERROR("unknown_error"),
    INVALID_PARAMETER("invalid_param"),
    NAME_EXIST("user_name_already_exist"),
    ID_NOT_EXIST("user_id_not_exist"),
    PREV_PASSWORD_NOT_MATCH("prev_password_not_match"),
    SAME_PASSWORD("current_password_is_same_than_prev"),
    ;


    private String description;

    UserErrorCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
