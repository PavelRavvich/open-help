package com.openhelp.apigateway.validation;

import java.util.Locale;

/**
 * @author Pavel Ravvich.
 */
public class AccessDeniedException extends RuntimeException{
   public AccessDeniedException(String pattern) {
       super(String.format("Access denied no accesses for %s", pattern.toUpperCase(Locale.ROOT)));
   }
}
