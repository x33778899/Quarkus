package org.example.interceptor;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.*;

@Inherited
@InterceptorBinding
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
//@interface 自定義注解
public @interface Loggable {
}
