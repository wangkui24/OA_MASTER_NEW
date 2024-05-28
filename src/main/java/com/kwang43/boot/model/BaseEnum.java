package com.kwang43.boot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.kwang43.boot.core.Const;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public interface BaseEnum {
    class Employee {
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        @JsonRootName("gender")
        public enum GenderEnum {
            /**
             * FEMALE
             */
            FEMALE(0, "FEMALE"),
            /**
             * MALE
             */
            MALE(1, "MALE"),
            /**
             * UNKNOWN: No gender information
             */
            UNKNOWN(2, "UNKNOWN"),
            /**
             * GENDER_NEUTRAL
             */
            GENDER_NEUTRAL(3, "GENDER_NEUTRAL");
            private Integer value;
            private String name;
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        @JsonRootName("status")
        public enum StatusEnum {
            /**
             * IN_SERVICE
             */
            IN_SERVICE(0, Const.IN_SERVICE),
            /**
             * JOINING_IN
             */
            JOINING_IN(1, Const.JOINING_IN),
            /**
             * RESIGNING
             */
            RESIGNING(2, Const.RESIGNING),
            /**
             * RESIGNED
             */
            RESIGNED(3, Const.RESIGNED);

            private Integer value;
            private String name;
        }
    }

    class SystemUser {
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        @JsonRootName("status")
        public enum StatusEnum {
            /**
             * InActive
             */
            INACTIVE(0, Const.INACTIVE),
            /**
             * Active
             */
            ACTIVE(1, Const.ACTIVE),
            /**
             * Block
             */
            BLOCKED(2, Const.BLOCKED);

            private Integer value;
            private String name;
        }
    }

    class RequestLeave {
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        @JsonRootName("status")
        public enum StatusEnum {
            /**
             * PENDING
             */
            PENDING(0, Const.PENDING),
            /**
             * APPROVED
             */
            ACTIVE(1, Const.APPROVED),
            /**
             * REJECTED
             */
            BLOCKED(2, Const.REJECTED),
            /**
             * CANCEL
             */
            CANCEL(3, Const.CANCEL);

            private Integer value;
            private String name;
        }
    }

    public class Defalut {
        public Defalut(){

        }
        public static final String CREATE_DATE_TIME = "createDatetime";
    }
}
