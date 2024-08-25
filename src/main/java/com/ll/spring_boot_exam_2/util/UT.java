package com.ll.spring_boot_exam_2.util;

public class UT {
    public static class str{
        public static boolean isBlank(String str) {
            return str == null || str.isBlank();
        }
    }

    public static class hasLength{ //이제 값이 있는지 체크할 때 널, 공백 같은 걸 체크
        public static boolean isBlank(String str) {
           return !isBlank(str);
        }
    }


}

