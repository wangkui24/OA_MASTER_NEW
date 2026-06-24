package com.kwang43.boot.core;

import com.kwang43.boot.utils.DataUtils;
import com.kwang43.boot.utils.StringUtils;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/16 12:55
 */
public class Const {
    private Const() {
    }

    public static  final String IN_SERVICE = "IN_SERVICE";
    public static  final String JOINING_IN = "JOINING_IN";
    public static  final String RESIGNING = "RESIGNING";
    public static  final String RESIGNED = "RESIGNED";

    public static  final String INACTIVE = "INACTIVE";
    public static  final String ACTIVE = "ACTIVE";
    public static  final String BLOCKED = "BLOCKED";

    public static  final String PENDING = "PENDING";
    public static  final String APPROVED = "APPROVED";
    public static  final String REJECTED = "REJECTED";
    public static  final String CANCEL = "CANCEL";

    public static final String LOG_MDC_ID = "trace_id";

    //for pagination
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer DEFAULT_PAGE_INDEX = 0;
    public static final Integer DEFAULT_MAX_PAGE_SIZE = 3000;

    public static final String ROLE_WEB_USER = "WEB_OPERATOR";
    public static final String ROLE_APP_USER = "APP_OPERATOR";

    public static final String ROLE_PREFIX = "ROLE_";

    public static final String CLIENT_WEB_SCOPE = "SCOPE_WEB_APP";
    public static final String CLIENT_APP_SCOPE = "SCOPE_CONSUMER_APP";

    public enum UserPrefix {
        SYSTEM_JOB("system_job@");

        private String code;

        UserPrefix(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }

        public static String removePrefix(final String value) {
            String unPrefixedValue = String.valueOf(DataUtils.nvl(value));
            for (UserPrefix userPrefix : UserPrefix.values()) {
                unPrefixedValue = unPrefixedValue.replace(userPrefix.getCode(), "");
            }

            return unPrefixedValue;
        }

        public static UserPrefix getInstanceByCode(String code) {
            final Optional<UserPrefix> userPrefixOptional = Arrays.stream(UserPrefix.values())
                    .filter(userPrefix -> userPrefix.getCode().equals(code))
                    .findAny();
            return userPrefixOptional.orElse(null);
        }
    }

    @Getter
    public enum UserCategory {
        WEB("WEB"),
        APP("APP"),
        UNDEFINED("UNDEFINED");

        private String code;

        UserCategory(String code) {
            this.code = code;
        }

        public static UserCategory getInstanceByCode(String code) {
            Optional<UserCategory> userCategory = Arrays.stream(UserCategory.values())
                    .filter(user -> user.getCode().equals(code))
                    .findAny();
            return userCategory.orElse(UserCategory.UNDEFINED);
        }
    }

    public enum TerminalType {
        //TODO refactor the scopes
        CLIENT_WEB("web", CLIENT_WEB_SCOPE, Const.ROLE_WEB_USER),
        CLIENT_APP("app", CLIENT_APP_SCOPE, Const.ROLE_APP_USER),;

        private String clientId;
        // Use spaces to separate multiple scopes
        private String scopes;
        private String[] defaultRoles;

        TerminalType(String code, String scopes, String... defaultRoles) {
            this.clientId = code;
            this.scopes = scopes;
            this.defaultRoles = defaultRoles;
        }

        public String getClientId() {
            return this.clientId;
        }

        public String getScopes() {
            return this.scopes;
        }

        public String[] getDefaultRoles() {
            return this.defaultRoles;
        }

        public static Optional<TerminalType> findByClientId(String clientId) {
            if (StringUtils.isEmpty(clientId)) {
                return Optional.empty();
            }

            return Arrays.stream(TerminalType.values())
                    .filter(t -> t.getClientId().equals(clientId))
                    .findFirst();
        }
    }
}