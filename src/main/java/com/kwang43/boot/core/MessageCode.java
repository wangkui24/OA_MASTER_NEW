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

        public static final String ACCOUNT_NOT_EXIST = "ACCOUNT_NOT_EXIST";
        public static final String ACCOUNT_IS_INACTIVE = "ACCOUNT_IS_INACTIVE";
        public static final String ACCOUNT_IS_BLOCKED = "ACCOUNT_IS_BLOCKED";
        public static final String PASSWORD_ERROR = "PASSWORD_ERROR";
        public static final String USERNAME_NOT_EXIST = "USERNAME_NOT_EXIST";
        public static final String EMAIL_NOT_MATCH = "EMAIL_NOT_MATCH";
        public static final String SEND_EMAIL_FAILED = "SEND_EMAIL_FAILED";
        public static final String CREATE_OA_ACCOUNT_FAILED = "CREATE_OA_ACCOUNT_FAILED";
        public static final String OA_ACCOUNT_NOT_EXIST = "OA_ACCOUNT_NOT_EXIST";
        public static final String EMIAL_IS_EXISTED = "EMIAL_IS_EXISTED";
    }

    class Captcha {
        private Captcha() {
        }

        public static final String CAPTCHA_ERROR = "CAPTCHA_ERROR";
        public static final String CAPTCHA_ERROR_OR_EXPIRE = "CAPTCHA_ERROR_OR_EXPIRE";
        public static final String CREATE_CAPTCHA_FAILED = "CREATE_CAPTCHA_FAILED";
    }

    class Employee {
        private Employee() {
        }

        public static final String NOT_FOUND_EMPLOYEE = "NOT_FOUND_EMPLOYEE";
        public static final String MOBILE_NUMBER_HAS_EXISTED = "MOBILE_NUMBER_HAS_EXISTED";
        public static final String EMAIL_HAS_EXISTED = "EMAIL_HAS_EXISTED";
        public static final String EXPORT_EMPLOYEE_FAILED = "EXPORT_EMPLOYEE_FAILED";
        public static final String EMPLOYEE_HAS_RESIGNED = "EMPLOYEE_HAS_RESIGNED";
        public static final String EMPLOYEE_HAS_NOT_IN_SERVICE = "EMPLOYEE_HAS_NOT_IN_SERVICE";

    }

    class RequestLeave {
        private RequestLeave(){
        }

        public static final String REQUEST_LEAVE_FAILED = "REQUEST_LEAVE_FAILED";
    }
}
