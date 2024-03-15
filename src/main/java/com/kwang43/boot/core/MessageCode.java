package com.kwang43.boot.core;

public interface MessageCode {
    class Account {
        private Account() {
        }

        public static final String SERVER_ERROR = "COM0000";
        public static final String ACCOUNT_NOT_EXIST = "COM0001";
        public static final String ACCOUNT_IS_INACTIVE = "COM0002";
        public static final String ACCOUNT_IS_BLOCKED = "COM0003";
        public static final String PASSWORD_ERROR = "COM0004";
        public static final String CAPTCHA_ERROR = "COM0005";
        public static final String CAPTCHA_ERROR_OR_EXPIRE = "COM0006";
    }
}
