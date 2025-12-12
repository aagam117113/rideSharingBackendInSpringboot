package com.student.tripmate.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {
    public static String currentUsername() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (a == null) return null;
        Object p = a.getPrincipal();
        if (p instanceof String) return (String) p;
        return a.getName();
    }
}
