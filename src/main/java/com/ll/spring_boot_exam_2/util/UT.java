package com.ll.spring_boot_exam_2.util;

import com.ll.spring_boot_exam_2.security.AppConfig;
import lombok.SneakyThrows;

public class UT {
    public static class str {
        public static boolean isBlank(String str) {
            return str == null || str.trim().length() == 0;
        }

        public static boolean hasLength(String str) {
            return !isBlank(str);
        }
    }

    public static class json {
        @SneakyThrows
        public static String toString(Object obj) {
            return AppConfig.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }
    }


}

