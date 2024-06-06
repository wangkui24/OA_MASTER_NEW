package com.kwang43.boot.core;

public interface MessageCode {
    class System {
        private System() {
        }

        public static final String SERVER_ERROR = "COM0000";
    }

    class Account {
        private Account() {
        }

        public static final String ACCOUNT_NOT_EXIST = "COM0001";
        public static final String ACCOUNT_IS_INACTIVE = "COM0002";
        public static final String ACCOUNT_IS_BLOCKED = "COM0003";
        public static final String PASSWORD_ERROR = "COM0004";
        public static final String USERNAME_NOT_EXIST = "COM0011";
        public static final String EMAIL_NOT_MATCH = "COM0012";
        public static final String SEND_EMAIL_FAILED = "COM0013";
    }

    class Captcha {
        private Captcha() {
        }

        public static final String CAPTCHA_ERROR = "COM0005";
        public static final String CAPTCHA_ERROR_OR_EXPIRE = "COM0006";
        public static final String CREATE_CAPTCHA_FAILED = "COM0007";
    }

    class Employee {
        private Employee() {
        }

        public static final String NOT_FOUND_EMPLOYEE = "COM0008";
        public static final String MOBILE_NUMBER_HAS_EXISTED = "COM0009";
        public static final String EMAIL_HAS_EXISTED = "COM0010";
    }

    class RequestLeave {
        private RequestLeave(){
        }

        public static final String REQUEST_LEAVE_FAILED = "COM0020";
    }
}
