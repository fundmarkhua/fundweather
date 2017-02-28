package com.example.congbai.fundweather.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 * we create a custom scope to be used by all fragment components.
 * Additionally, a component with a specific scope cannot have a sub component with the same scope.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScoped {
}
